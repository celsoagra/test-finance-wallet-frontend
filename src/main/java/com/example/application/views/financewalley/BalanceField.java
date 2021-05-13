package com.example.application.views.financewalley;

import com.example.application.base.Wallet;
import com.example.application.client.TestFinanceClient;
import com.example.application.dto.BalanceDTO;
import com.example.application.dto.CoinbaseDTO;
import com.example.application.dto.FaucetDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class BalanceField extends CustomField<String> {

    private TextField name;
    private Button update;
    private Button faucet;
    private String value = "0.0";
    private TestFinanceClient client;
    private Wallet wallet;

    public BalanceField(TestFinanceClient client, Wallet wallet) {
        setWidthFull();
        this.client = client;
        this.wallet = wallet;
        name = new TextField();
        name.setValue("Balances: BAL " + value);
        name.setReadOnly(true);
        update = new Button("Update");
        faucet = new Button("Faucet");
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addClickListener(e -> {
            updateBalance();
        });
        
        faucet.addClickListener(e -> {
            CoinbaseDTO dto = CoinbaseDTO.newInstance(this.wallet.getPublicKeyAsString());
            FaucetDTO faucetDTO = this.client.faucet(dto).getBody();
            Notification.show("You got: " + faucetDTO.getGot());
            updateBalance();
        });
        
        HorizontalLayout layout = new HorizontalLayout(name, update, faucet);
        name.setWidth("60%");
        update.setWidth("20%");
        faucet.setWidth("20%");
        add(layout);
    }
    
    private void updateBalance() {
        CoinbaseDTO dto = CoinbaseDTO.newInstance(this.wallet.getPublicKeyAsString());
        BalanceDTO balanceDTO = this.client.balance(dto).getBody();
        this.value = balanceDTO.getBalance();
        name.setValue("Balances: BAL " + value);
        Notification.show("Updated!");
    }

    @Override
    protected String generateModelValue() {
        return null;
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
    }

}
