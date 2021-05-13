package com.example.application.views.financewalley;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.base.Wallet;
import com.example.application.client.TestFinanceClient;
import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Finance Walley")
public class FinanceWalleyView extends Div {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private DatePicker dateOfBirth = new DatePicker("Birthday");
    private WalletAddressField walletAddressField;
    private SendAddressField walletSendField;
    private BalanceField balanceField;
    private TextField occupation = new TextField("Occupation");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<SamplePerson> binder = new Binder(SamplePerson.class);

    public FinanceWalleyView(SamplePersonService personService) {
        addClassName("finance-walley-view");
        TestFinanceClient client = personService.getClient();
        Wallet wallet = personService.getWallet();
        
        balanceField = new BalanceField(client, wallet);
        walletAddressField = new WalletAddressField(wallet.getPublicKeyAsString());
        walletSendField = new SendAddressField(client, wallet);
        
        Component headerTitle = createTitle();
        Component form = createFormLayout();
        Component button = createButtonLayout();
        
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(headerTitle);
        verticalLayout.add(balanceField);
        verticalLayout.add(new H3("Receber: "));
        verticalLayout.add(walletAddressField);
        verticalLayout.add(new H3("Enviar: "));
        verticalLayout.add(walletSendField);
        verticalLayout.setMargin(true);
        verticalLayout.getStyle().set("padding-left", "25%");
        verticalLayout.getStyle().set("padding-right", "25%");
        add(verticalLayout);
        

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
//            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H1("MyWallet");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, dateOfBirth, email, occupation);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
