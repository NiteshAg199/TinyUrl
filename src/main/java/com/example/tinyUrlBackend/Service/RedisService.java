package com.example.tinyUrlBackend.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class RedisService {
	@Autowired
	private RedisTemplate redisTemplate;
	
	public void setData(String key,Object object,long ttl) throws JsonProcessingException{
		ObjectMapper obj=new ObjectMapper();
		obj.registerModule(new JavaTimeModule());
		String value=obj.writeValueAsString(object);
		redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
	}
	
	public Object getData(String key,Class entity) throws JsonProcessingException{
			try {
			ObjectMapper obj=new ObjectMapper();
			Object value = redisTemplate.opsForValue().get(key);
			if(value==null) return null;
			obj.registerModule(new JavaTimeModule());
			return obj.readValue(value.toString(), entity);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}
}
