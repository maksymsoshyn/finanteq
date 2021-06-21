package com.maksymsoshyn.finanteq.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonAlias("ask")
    private double sellRate;

    @JsonAlias("bid")
    private double buyRate;

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "sellRate=" + sellRate +
                ", buyRate=" + buyRate +
                '}';
    }
}
