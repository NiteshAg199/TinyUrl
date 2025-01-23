package com.example.tinyUrlBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.shaded.guava.common.base.Optional;
import com.example.demo.Model.largerUrlModel;
import com.example.tinyUrlBackend.dao.UrlDAO;
import com.example.tinyUrlBackend.repo.ShortenUrlRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.seruco.encoding.base62.Base62;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.hibernate.dialect.Dialect;

@Service
public class shortenUrlService {
	@Autowired
	private RedisService redisService;
	@Autowired
	private ShortenUrlRepository ShortenUrlRepository;
	public UrlDAO generateUrl(largerUrlModel largerurlmodel) throws JsonProcessingException{
		Object redisResponse = redisService.getData(largerurlmodel.getActualUrl(), UrlDAO.class);
		if(redisResponse!=null) return (UrlDAO) redisResponse;
		java.util.Optional<UrlDAO> op=ShortenUrlRepository.findById(largerurlmodel.getActualUrl());
		if(!op.isEmpty()) {
			redisService.setData(largerurlmodel.getActualUrl(), op.get(),86400 );
			return op.get();
		}
		byte[] encode61=Base62.createInstance().encode(Uuids.timeBased().toString().getBytes());
//		System.out.println("encodedBytes " + new String(encode61).substring(0, Math.min(7, 22)));
		while(!ShortenUrlRepository.findByShortUrl(new String(encode61).substring(0, Math.min(7, 22))).isEmpty()){
			encode61=Base62.createInstance().encode(Uuids.timeBased().toString().getBytes());
		}
		UrlDAO urlDao= new UrlDAO(largerurlmodel.getActualUrl(),new String(encode61).substring(0, Math.min(7, 22)),Instant.now());
		UrlDAO returnDAO=ShortenUrlRepository.save(urlDao);
//		byte[] encode62=Base62.createInstance().encode(Uuids.timeBased().toString().getBytes());
//		System.out.println("encodedBytes " + new String(encode62).substring(0, Math.min(7, 22)) +"  "+UUID.randomUUID().toString().length());
//		System.out.println(urlDao);
		redisService.setData(largerurlmodel.getActualUrl(),returnDAO,86400 );
		return returnDAO;
	}
	public largerUrlModel getActualUrl(String url) throws JsonProcessingException {
		Object redisResponse= redisService.getData(url, UrlDAO.class);
		if(redisResponse!=null) return new largerUrlModel(((UrlDAO) redisResponse).getActualUrl());
		List<UrlDAO> op=ShortenUrlRepository.findByShortUrl(url);
		if(op.isEmpty()) return new largerUrlModel("not avaliable");
		redisService.setData(url, op.get(0),86400 );
		return new largerUrlModel(op.get(0).getActualUrl());
	}
}
