package tn.esprit.spring.AhmedGuedri.Controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Service.IOrderService;
import tn.esprit.spring.AhmedGuedri.Service.StripeService;
import tn.esprit.spring.AhmedGuedri.entities.Orders;


import java.util.List;

@RestController
@RequestMapping("/Order")

public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    StripeService stripeService;

    //http://localhost:8083/ratatoskr/Order/addOrder
    @PostMapping("/addOrder")
    @ResponseBody
    public Orders addOrder(@RequestBody Orders o){
        return orderService.addOrders(o);
    }

    //http://localhost:8083/ratatoskr/Order/updateOrders
    @PutMapping("/updateOrders")
    @ResponseBody
    public void updateOrders(@RequestBody Orders o)
    {
        orderService.updateOrders(o);
    }

    //http://localhost:8083/ratatoskr/Order/deleteOrders/1
    @DeleteMapping("/deleteOrders/{id}")
    @ResponseBody
    public void deleteOrders(@PathVariable Long id){
        orderService.deleteOrders(id);
    }

    //http://localhost:8083/ratatoskr/Order/retrieveAllOrders
    @GetMapping("/retrieveAllOrders")
    @ResponseBody
    public List<Orders> retrieveAllOrders() {

        return orderService.retrieveAllOrders();
    }


    //http://localhost:8083/ratatoskr/Order/retrieveOrders/1
    @GetMapping("/retrieveOrders/{id}")
    @ResponseBody
    public Orders retrieveOrders(@PathVariable Long id){
        return orderService.retrieveOrders(id);
    }

    //http://localhost:8083/ratatoskr/Order/AddPanierToOrders/1/1
    @PostMapping("/AddPanierToOrders/{idpanier}/{idOrders}")
    @ResponseBody
    public Orders addPanierToOrder(@PathVariable Long idpanier,@PathVariable Long idOrders ){
        return orderService.addPanierToOrder(idpanier,idOrders);
    }
    //http://localhost:8083/ratatoskr/Orders/stripe/token/1/1
    @PostMapping("/stripe/{token}/{idUser}/{idOrders}")
    @ResponseBody
    public double createCharge(@PathVariable String token, @PathVariable Long idUser, @PathVariable Long idOrders) throws StripeException  {
        return stripeService.createCharge(token,idUser,idOrders);
    }
}
