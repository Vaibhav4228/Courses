package com.effigo_vaibhav.springboot_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyControllerConfig {

    @Autowired
    private CurrencyConfig configurations;

    @RequestMapping("/currency-configuartion")
    public CurrencyConfig gettingAll(){
      return configurations;
    }

}
