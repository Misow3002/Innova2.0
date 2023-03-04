package tn.esprit.spring.AhmedGuedri.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Service.IPanierService;
import tn.esprit.spring.AhmedGuedri.entities.Panier;

import java.util.List;

@RestController
@RequestMapping("/Panier")

public class PanierController {
    @Autowired
    IPanierService panierService;

    //http://localhost:8083/ratatoskr/Panier/1/products/1
    @PostMapping("/{panierId}/products/{productId}")
    public Panier addToPanier(@PathVariable Long panierId, @PathVariable Long productId) {
        return panierService.addProductToPanier(panierId, productId);
    }

    //http://localhost:8083/ratatoskr/Panier/addPanier
    @PostMapping("/addPanier")
    @ResponseBody
    public Panier addPanier(@RequestBody Panier p){
        return panierService.addPanier(p);
    }

    //http://localhost:8083/ratatoskr/Panier/UpdatePanier
    @PutMapping("/UpdatePanier")
    @ResponseBody
    public void updatePanier(@RequestBody Panier p)
    {
        panierService.updatePanier(p);
    }

    //http://localhost:8083/ratatoskr/Panier/deletePanier/1
    @DeleteMapping("/deletePanier/{id}")
    @ResponseBody
    public void deletePanier(@PathVariable Long id){
        panierService.deletePanier(id);
    }

    //http://localhost:8083/ratatoskr/Panier/retrieveAllPanier
    @GetMapping("/retrieveAllPanier")
    @ResponseBody
    public List<Panier> retrieveAllPanier() {

        return panierService.retrieveAllPanier();
    }


    //http://localhost:8083/ratatoskr/Panier/retrievePanier/1
    @GetMapping("/retrievePanier/{id}")
    @ResponseBody
    public Panier retrievePanier(@PathVariable Long id){
        return panierService.retrievePanier(id);
    }

    //http://localhost:8083/ratatoskr/Panier/total-price/1
    @GetMapping("/total-price/{panierId}")
    public ResponseEntity<Float> getTotalPrice(@PathVariable Long panierId) {
        Panier panier = panierService.retrievePanier(panierId);
        if (panier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(panier.getTotalPrice());
    }
}
