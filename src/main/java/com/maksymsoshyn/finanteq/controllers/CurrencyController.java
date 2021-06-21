package com.maksymsoshyn.finanteq.controllers;

import com.maksymsoshyn.finanteq.resources.Currency;
import com.maksymsoshyn.finanteq.resources.CurrencyConversionResult;
import com.maksymsoshyn.finanteq.validation.ValuesAllowed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;


@RestController
@Validated
public class CurrencyController {
    private static final String BASE_CURRENCY_CODE = "pln";

    private final double COMMISSION_PERCENT = 2.0;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${nbp.api.url}")
    private String nbpApiBasicUrl;


    @GetMapping("/currency/convert")
    CurrencyConversionResult currencyConvert(
            @RequestParam(name = "currencyCodeFrom")
            @ValuesAllowed(values = {"pln", "eur", "usd", "gbp"})
                    String currencyCodeFrom,
            @RequestParam(name = "currencyCodeTo")
            @ValuesAllowed(values = {"pln", "eur", "usd", "gbp"})
                    String currencyCodeTo,
            @RequestParam(name = "amount")
            @Positive
                    double amount){
        try{
            Currency fromCurrency = createCurrencyObject(currencyCodeFrom);
            Currency toCurrency = createCurrencyObject(currencyCodeTo);
            return currencyConvert(fromCurrency, toCurrency, amount);
        }catch(HttpClientErrorException exc){
            throw new ResponseStatusException(
                    exc.getStatusCode(),
                    exc.getResponseBodyAsString(),
                    exc
            );
        }
    }

    private CurrencyConversionResult currencyConvert(Currency from, Currency to, double amount){
        CurrencyConversionResult currencyResult = new CurrencyConversionResult(
                to.getCurrencyCode()
        );
        String fromCurrencyCode = from.getCurrencyCode();
        String toCurrencyCode = to.getCurrencyCode();
        if (!from.getCurrencyCode().equals(to.getCurrencyCode())){
            System.out.println(amount);
            if (toCurrencyCode.equals(BASE_CURRENCY_CODE) && !fromCurrencyCode.equals(BASE_CURRENCY_CODE)){
                amount = amount*from.getRates().get(0).getBuyRate();
            }else if (fromCurrencyCode.equals(BASE_CURRENCY_CODE) && !toCurrencyCode.equals(BASE_CURRENCY_CODE)){
                amount = amount/to.getRates().get(0).getSellRate();
            }else{
                amount = (amount*from.getRates().get(0).getBuyRate())/to.getRates().get(0).getSellRate();
            }
        }
        amount -= amount*COMMISSION_PERCENT/100.0;
        currencyResult.setAmount(amount);
        return currencyResult;
    }

    private Currency createCurrencyObject(String currencyCode){
        Currency currencyInstance = new Currency();
        if (currencyCode.equals(BASE_CURRENCY_CODE)){
            currencyInstance.setCurrencyCode(currencyCode);
        }else{
            currencyInstance = restTemplate.getForObject(nbpApiBasicUrl+"/exchangerates/rates/c/"+currencyCode+"?format=json",
                    Currency.class);
        }
        return currencyInstance;
    }
}
