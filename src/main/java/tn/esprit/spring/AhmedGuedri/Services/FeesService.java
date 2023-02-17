package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.FeesRepository;
import tn.esprit.spring.AhmedGuedri.entities.Fees;
import java.util.List;


@Service
@AllArgsConstructor
public class FeesService implements IFeesService{
    //implementing the methods of the interface with the help of the repository
    FeesRepository feesRepository;

    @Override
    public Fees addFees(Fees fees) {
        // TODO Auto-generated method stub

        feesRepository.save(fees);
        return fees;
    }

    @Override
    public Fees updateFees(Fees fees) {
        feesRepository.save(fees);
        return fees;
    }
    @Override
    public void deleteFees(Long fees) {
        feesRepository.deleteById(fees);
    }
    @Override
    public List<Fees> retrieveAllFees() {
        List<Fees> fees = (List<Fees>) feesRepository.findAll();
        return fees;
    }
    @Override
    public Fees retrieveFees(Long idFees) {
        return feesRepository.findById(idFees).get();
    }

}
