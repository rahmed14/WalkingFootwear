package cogent.demo.security.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class StripeService {
	
	

    @Autowired
    StripeService() {
        Stripe.apiKey = "sk_test_51Hcy7IIgootA5bPlJhbQDYiHQAt4Vq8mrMWoJRWaOxa2aqoYLyclkLOiKJexbqpEbdoH5SrmqKdQRZ8mAGXCj6QZ00gMLl7Mkl";
    }

    public Charge chargeCreditCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
	
	

//    @Value("${STRIPE_SECRET_KEY}")
//    private String API_SECRET_KEY;
//
//    @Autowired
//    public StripeService() {
//        Stripe.apiKey = API_SECRET_KEY;
//    }
//
//    public Charge chargeNewCard(String token, double amount) throws Exception {
//        Map<String, Object> chargeParams = new HashMap<String, Object>();
//        chargeParams.put("amount", (int)(amount * 100));
//        chargeParams.put("currency", "USD");
//        chargeParams.put("source", token);
//        Charge charge = Charge.create(chargeParams);
//        return charge;
//    }
}