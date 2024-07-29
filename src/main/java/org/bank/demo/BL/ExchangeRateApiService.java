package org.bank.demo.BL;

import org.bank.demo.beans.ExchangeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${exchange.rate.api.url}")
	private String apiUrl;

	@Cacheable(value = "currencyCache", key = "#from")
	public ExchangeResult goTo3rdParty(String from) {
		ExchangeResult res = restTemplate.getForObject(apiUrl + from, ExchangeResult.class);
		return res;
	}

}
