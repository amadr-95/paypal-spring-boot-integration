package org.payment.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;

    public Payment createPayment(Double total, String currency, String method, String intent,
                                 String description, String cancelUrl, String successUrl)
            throws PayPalRESTException {

        //create total amount
        Amount amount = new Amount(currency,
                String.format(Locale.forLanguageTag(currency), "%.2f", total));

        //create transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        //create payer
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        //create payment
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(new ArrayList<>(
                List.of(transaction)
        ));

        //create error and success urls
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        return payment.create(apiContext);
    }
}
