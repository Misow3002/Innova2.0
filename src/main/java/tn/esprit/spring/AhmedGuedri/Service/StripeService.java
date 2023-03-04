package tn.esprit.spring.AhmedGuedri.Service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.spring.AhmedGuedri.Repository.OrderRepo;
import tn.esprit.spring.AhmedGuedri.Repository.PanierRepo;
import tn.esprit.spring.AhmedGuedri.Repository.PaymentRepository;
import tn.esprit.spring.AhmedGuedri.Repository.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Panier;
import tn.esprit.spring.AhmedGuedri.entities.Payment;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StripeService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepo orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    OrderService serviceOder;
    @Autowired
    ServiceEmail serviceEmail;

    @Value("sk_test_51Mhg25BYRBc0J1wpoNuBuucIzrAq0xcEZOqxcuVaEnF3LbbVeEDZt7z7BLUeashdvG6IOyRWp9U6YsHP6eAcs9Gy00EQtJABl5")
    String stripeKey;

    public Payment payment(long idUser, long idOrder, Payment p) throws StripeException {
        Stripe.apiKey = stripeKey;
        User user = userRepository.findById(idUser).get();
        Orders orders = orderRepository.findById(idOrder).get();
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getFirstName());
        params.put("email", user.getEmail());
        params.put("amount*100", orders.getPanier().getTotalPrice());
        params.put("created",p.getCreated());
      //  Customer customer = Customer.create(params);
        //p.setCustomerId(customer.getId());
        return p;
    }

    public double createCharge(String email,String token, Long idUser, Long idOrders) throws StripeException {
        Optional<User> user = userRepository.findById(idUser);
        Orders orders = orderRepository.findById(idOrders).get();

        String id;
        Stripe.apiKey = stripeKey;
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Math.round(serviceOder.TotalOrdersTVA(idOrders)));
        chargeParams.put("currency", "usd");
        chargeParams.put("receipt_email", email);
        chargeParams.put("description", "Charge for " + email);
        chargeParams.put("source", token); // ^ obtained with Stripe.js
        //create a charge
        Charge charge = Charge.create(chargeParams);
        id = charge.getId();
        if (id == null) {
            throw new RuntimeException("Something went wrong!");
        }
        Payment payment = new Payment(); // Create a new Payment object
// Set the properties of the Payment object as required
        payment.setCreated(1);
        payment.setUser(user.get());
        orders.setPayment(payment);
        paymentRepository.save(payment);
        serviceEmail.sendEmail(email, "Thank you for your purchase !", "payment with sucess");
        // payment successfully
        return serviceOder.TotalOrdersTVA(idOrders);
    }


    private String getReceipt(String id) throws StripeException {
        Charge charge = Charge.retrieve(id);
        String receiptUrl = charge.getReceiptEmail();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(receiptUrl, String.class);
    }


}