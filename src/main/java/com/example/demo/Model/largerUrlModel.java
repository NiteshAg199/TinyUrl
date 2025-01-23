package com.example.demo.Model;

public class largerUrlModel {
	private String actualUrl;

	public String getActualUrl() {
		return actualUrl;
	}

	public void setActualUrl(String actualUrl) {
		this.actualUrl = actualUrl;
	}

	@Override
	public String toString() {
		return "largerUrlModel [actualUrl=" + actualUrl + "]";
	}

	public largerUrlModel(String actualUrl) {
		super();
		this.actualUrl = actualUrl;
	}

	public largerUrlModel() {
		super();
	}
	
}
