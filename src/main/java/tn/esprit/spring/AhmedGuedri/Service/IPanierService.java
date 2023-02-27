package tn.esprit.spring.AhmedGuedri.Service;

import tn.esprit.spring.AhmedGuedri.entities.Panier;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;

public interface IPanierService {
    List<Panier> retrieveAllPanier();

    Panier updatePanier(Panier panier);

    void deletePanier(Long id);

    Panier addPanier(Panier panier);

    Panier retrievePanier(Long id);

    Panier addProductToPanier(Long panierId, Long productId);

}
