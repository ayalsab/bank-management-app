package org.bank.demo.BL;

import org.bank.demo.beans.ExchangeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Map;

@Component
@EnableScheduling
public class MyCacheManager {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private Map<String, Map<String, Double>> map;

	@Autowired
	private ExchangeRateApiService service;

	public void add(String from, Map<String, Double> values) {
		map.put(from, values);
	}

	public double getCurrency(String from, String to) {
		// Retrieve cache
		Cache cache = cacheManager.getCache("currencyCache");

		// Check if cache is available
		if (cache == null) {
			// Fetch data from third-party service and cache it
			return fetchAndCacheExchangeRate(from, to);
		}

		// Try to retrieve the cached result
		ExchangeResult exchangeResult = getCachedExchangeResult(cache, from);

		// If not found in cache or the specific rate is missing, fetch and update cache
		if (exchangeResult == null || exchangeResult.getRates().get(to) == null) {
			return fetchAndCacheExchangeRate(from, to);
		}

		// Return the cached rate
		return exchangeResult.getRates().get(to);
	}

	private double fetchAndCacheExchangeRate(String from, String to) {
		// Fetch data from third-party service

		ExchangeResult exchangeResult = service.goTo3rdParty(from);

		// Cache the result if cache is available
		Cache cache = cacheManager.getCache("currencyCache");
		if (cache != null) {
			cache.put(from, exchangeResult);
		}

		System.out.println("++++++ 0.01 USD "+exchangeResult.getRates());
		return exchangeResult.getRates().get(to);
	}

	private ExchangeResult getCachedExchangeResult(Cache cache, String from) {
		Cache.ValueWrapper valueWrapper = cache.get(from);
		return valueWrapper != null ? (ExchangeResult) valueWrapper.get() : null;
	}

//	@Scheduled(cron = "0/10 * * * * ?") // https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks
	@Scheduled(cron = "0 0 0 * * ?") // Daily at midnight
	public void clearDailyCache() {
		System.out.println("delete");
		cacheManager.getCacheNames().forEach(cacheName -> {
			cacheManager.getCache(cacheName).clear();
		});
	}

}
