package com.maksymsoshyn.finanteq.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @JsonAlias("code")
    private String currencyCode;

    @JsonAlias("rates")
    private List<Rates> rates;


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<Rates> getRates() {
        return rates;
    }

    public void setRates(List<Rates> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                ", currencyCode='" + currencyCode + '\'' +
                ", rates=" + rates +
                '}';
    }
}
