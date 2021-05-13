package com.example.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application.dto.BalanceDTO;
import com.example.application.dto.CoinbaseDTO;
import com.example.application.dto.FaucetDTO;
import com.example.application.dto.TransactionDTO;

@Component
@FeignClient(name = "test-finance-client", url = "${app.client.url}")
public interface TestFinanceClient {

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> send(TransactionDTO dto);

    @PostMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceDTO> balance(CoinbaseDTO dto);

    @PostMapping(value = "/faucet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FaucetDTO> faucet(CoinbaseDTO dto);

    @GetMapping(value = "/coinbase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CoinbaseDTO> coinbase();

}