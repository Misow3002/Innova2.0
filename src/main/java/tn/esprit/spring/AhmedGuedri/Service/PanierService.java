package tn.esprit.spring.AhmedGuedri.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repository.PanierRepo;
import tn.esprit.spring.AhmedGuedri.Repository.ProductRepo;
import tn.esprit.spring.AhmedGuedri.entities.Panier;
import tn.esprit.spring.AhmedGuedri.entities.Products;
import java.util.List;


@Service

public class PanierService implements IPanierService{
    @Autowired
    PanierRepo panierRepo;
    @Autowired
    ProductRepo productRepo;



    @Override
    public List<Panier> retrieveAllPanier() {
        return panierRepo.findAll();
    }

    @Override
    public Panier updatePanier(Panier panier) {
        return panierRepo.save(panier);
    }

    @Override
    public void deletePanier(Long id) {
        panierRepo.deleteById(id);
    }

    @Override
    public Panier addPanier(Panier panier) {
        return panierRepo.save(panier);
    }

    @Override
    public Panier retrievePanier(Long id) {
        return panierRepo.findById(id).orElse(null);
    }

    @Override
    public Panier addProductToPanier(Long panierId, Long productId) {
        Panier panier = panierRepo.findById(panierId).get();
        Products product = productRepo.findById(productId).get();
        panier.getProducts().add(product);
        panier.setTotalPrice(panier.getTotalPrice() + product.getPrice());
        return panierRepo.save(panier);
    }

}
