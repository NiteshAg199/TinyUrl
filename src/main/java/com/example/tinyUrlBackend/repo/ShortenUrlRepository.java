package com.example.tinyUrlBackend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.datastax.oss.driver.shaded.guava.common.base.Optional;
import com.example.tinyUrlBackend.dao.UrlDAO;

public interface ShortenUrlRepository extends CrudRepository<UrlDAO,String>{
	public List<UrlDAO> findByShortUrl(String actualUrl);
}
