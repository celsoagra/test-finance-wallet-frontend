package com.example.application.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.base.Wallet;
import com.example.application.client.TestFinanceClient;

@Service
public class SamplePersonService {

    @Autowired
    private Wallet wallet;
    
    @Autowired
    private TestFinanceClient client;
    
    public TestFinanceClient getClient() {
        return client;
    }
    
    public Wallet getWallet() {
        return wallet;
    }

}
