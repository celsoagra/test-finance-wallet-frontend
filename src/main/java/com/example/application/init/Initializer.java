package com.example.application.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.application.base.Wallet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Initializer {
    
    @Autowired
    private Wallet wallet;
    
    @PostConstruct
    private void postConstruct() {
        wallet.generateKeyPair();
    }

}
