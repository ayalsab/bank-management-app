package org.bank.demo.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertResult {

	@JsonProperty("fromCurrency")
	private String from;
	@JsonProperty("toCurrency")
	private String to;
	@JsonProperty("amount")
	private double amount;
	@JsonProperty("result")
	private double result;

}