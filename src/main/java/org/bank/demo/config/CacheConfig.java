package org.bank.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public Map<String,Map<String, Double>> map(){
		Map<String, Double> internal =new HashMap<>();
		return new HashMap<>();
	}
	
	
	@Bean
	public Map<String, Double> internalMap(){
		return new HashMap<>();
	}
}
