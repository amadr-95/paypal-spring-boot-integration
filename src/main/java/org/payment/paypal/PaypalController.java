package org.payment.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    @Value("${application.paypal.success_url}")
    private String SUCCESS_URL;
    @Value("${application.paypal.cancel_url}")
    private String CANCEL_URL;
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam String method,
            @RequestParam String amount,
            @RequestParam String currency,
            @RequestParam String description

    ) {
        try {
            double parsedAmount = Double.parseDouble(amount);

            Payment payment = paypalService.createPayment(
                    parsedAmount,
                    currency,
                    method,
                    "sale",
                    description,
                    CANCEL_URL,
                    SUCCESS_URL
            );
            return getApprovalUrl(payment);
        } catch (NumberFormatException e) {
            log.error("Amount format exception: {}", amount, e);
            return new RedirectView("/payment/error");
        } catch (PayPalRESTException e) {
            log.error("Create payment exception::", e);
            return new RedirectView("/payment/error");
        }
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(payerId, paymentId);
                return payment.getState().equals("approved") ? "paymentSuccess" : "paymentError";
        } catch (PayPalRESTException e) {
            log.error("Payment execution error::", e);
            return "paymentError";
        }
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }

    private RedirectView getApprovalUrl(Payment payment) {
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url"))
                return new RedirectView(link.getHref());
        }

        return new RedirectView("/payment/error");
    }

}
