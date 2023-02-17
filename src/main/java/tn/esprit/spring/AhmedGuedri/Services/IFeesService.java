package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Fees;

import java.util.List;

public interface IFeesService {

    //implementing the methods of the interface
    public Fees addFees(Fees fees);
    public Fees updateFees(Fees fees);
    public void deleteFees(Long fees);
    public List<Fees> retrieveAllFees();
    public Fees retrieveFees(Long idFees);

}
