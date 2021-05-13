package com.example.application.views.financewalley;

import com.example.application.base.Wallet;
import com.example.application.client.TestFinanceClient;
import com.example.application.dto.TransactionDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;

public class SendAddressField extends CustomField<String> {
    
    private TextField address;
    BigDecimalField value;
    private Button send;
    private TestFinanceClient client;
    private Wallet wallet;
    
    public SendAddressField(TestFinanceClient client, Wallet wallet) {
        setLabel("Send Value to Address");
        setWidthFull();
        this.client = client;
        this.wallet = wallet;
        address = new TextField();
        value = new BigDecimalField();
        send = new Button("Send");
        send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        send.addClickListener(e -> {
            if (!value.isEmpty() && !address.isEmpty()) {
                String addressField = this.address.getValue();
                double valueField = this.value.getValue().doubleValue();
                TransactionDTO sendTo = TransactionDTO.builder().sender(this.wallet.getPublicKeyAsString()).receiver(addressField).value(valueField).build();
                sendTo.setSignature( this.wallet.generateSignature(addressField, valueField) );
                String result = client.send(sendTo).getBody();
                Notification.show("sent : " + result);
            } else {
                Notification.show("fill the fields to send it");
            }
        });
        HorizontalLayout layout = new HorizontalLayout(address, value, send);
        address.setWidth("60%");
        value.setWidth("20%");
        send.setWidth("20%");
        add(layout);
    }

    @Override
    protected String generateModelValue() {
        return null;
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
    }
    
}
