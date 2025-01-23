package com.example.tinyUrlBackend.dao;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="urlTable")
public class UrlDAO {
	@Id
	private String actualUrl;
	
	private String shortUrl;
    public UrlDAO(String actualUrl, String shortUrl, Instant created_At) {
		super();
		this.actualUrl = actualUrl;
		this.shortUrl = shortUrl;
		this.created_At = created_At;
	}
	@Override
	public String toString() {
		return "UrlDAO [actualUrl=" + actualUrl + ", shortUrl=" + shortUrl + ", created_At=" + created_At + "]";
	}
	public UrlDAO() {
		super();
	}
	public String getActualUrl() {
		return actualUrl;
	}
	public void setActualUrl(String actualUrl) {
		this.actualUrl = actualUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Instant getCreated_At() {
		return created_At;
	}
	public void setCreated_At(Instant created_At) {
		this.created_At = created_At;
	}
	@CreationTimestamp
	private Instant created_At;
    
}
