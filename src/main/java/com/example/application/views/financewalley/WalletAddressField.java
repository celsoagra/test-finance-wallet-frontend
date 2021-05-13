package com.example.application.views.financewalley;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class WalletAddressField extends CustomField<String> {
    
    private TextField name;
    private Button sayHello;
    
    public WalletAddressField(String address) {
        setLabel("My Address");
        setWidthFull();
        name = new TextField();
        name.setValue(address);
        name.setReadOnly(true);
        sayHello = new Button("Copy");
        sayHello.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sayHello.addClickListener(e -> {
            Notification.show("Copied");
        });
        HorizontalLayout layout = new HorizontalLayout(name, sayHello);
        name.setWidth("80%");
        sayHello.setWidth("20%");
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
