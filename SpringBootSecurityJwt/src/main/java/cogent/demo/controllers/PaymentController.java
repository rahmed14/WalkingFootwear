package cogent.demo.controllers;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Charge;

import cogent.demo.models.MailMessage;
import cogent.demo.security.services.MailService;
import cogent.demo.security.services.StripeService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
    private StripeService stripeClient;
	
	@Autowired
	RegistrationController Registration; 
	
	@Autowired
	private MailService notificationService;
	
	@Autowired
	private MailMessage message;
	
	final Logger logger =  Logger.getLogger("PaymentController"); 
	SimpleFormatter sm = new SimpleFormatter();
	FileHandler file=null;


//    @Autowired
//    PaymentController(StripeService stripeClient) {
//        this.stripeClient = stripeClient;
//    }

    @PostMapping("/charge")
    public Charge chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        Double amount = Double.parseDouble(request.getHeader("amount"));
        
        //modifying some code
        Charge charge=this.stripeClient.chargeCreditCard(token, amount);
        
        if(charge.getPaid()) {
        	Registration.send();
    		file = new FileHandler("/Users/rifath/Desktop/Cogent2/week5/SpringBootSecurityJwt/rifath.txt");
			file.setFormatter(sm);
			logger.addHandler(file);
        	logger.log(Level.INFO, charge.getFailureCode());
        }
        return charge;
        
        
//        return this.stripeClient.chargeCreditCard(token, amount);
    }
    
    
    @PostMapping("/charge1")
    public String chargeCard1(HttpServletRequest request, Model model) throws Exception {
        String token = request.getHeader("token");
        Double amount = Double.parseDouble(request.getHeader("amount"));
        
        //modifying some code
        Charge charge=this.stripeClient.chargeCreditCard(token, amount);
        
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("currancy", charge.getCurrency());
        
//        String url=charge.getReceiptEmail();
        
        message.setEmailAddress(charge.getReceiptEmail());
        message.setSubject("The WalkingFootWear Store");
        message.setBodyText("We Happy to help you, Please find your Recipt: \n");
//        message.setBodyText("We Happy to help you, Please find your Recipt: \n"+charge.getReceiptEmail());

        //    System.out.println(model);
        try {
			notificationService.sendEmail(message);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
    
    
    
    
    
    
}
