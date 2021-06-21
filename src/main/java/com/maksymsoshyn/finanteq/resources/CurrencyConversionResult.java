package com.maksymsoshyn.finanteq.resources;

public class CurrencyConversionResult {
    private String currencyCode;

    private double amount;

    public CurrencyConversionResult(String currencyCode){
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CurrencyConversionResult{" +
                ", currencyCode='" + currencyCode + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
