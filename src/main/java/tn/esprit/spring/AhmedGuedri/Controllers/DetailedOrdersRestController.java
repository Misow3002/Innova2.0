package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.AhmedGuedri.Services.IProductService;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dtorders")
public class DetailedOrdersRestController {
    IProductService productService;
    @GetMapping("/get/{dates}/{datee}/{id}")
    List<DetailedOrders> getDetailedOrdersbyDaterange(@PathVariable("dates") Date dates, @PathVariable("datee") Date datee, @PathVariable("id") Long id){
        return productService.getDetailedOrdersbyDaterange(dates,datee,id);
    }
}
