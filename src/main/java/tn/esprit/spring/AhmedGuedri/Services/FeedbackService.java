package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.FeedbacksRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@AllArgsConstructor
public class FeedbackService implements IFeedbackService{
    FeedbacksRepository feedbacksRepository;
    ProductsRepository productsRepository;
    @Override
    public List<Feedbacks> retrieveAllFeedbacks() {
        return feedbacksRepository.findAll();
    }

    @Override
    public Feedbacks addOrUpdateFeedback(Feedbacks f) {
        return feedbacksRepository.save(f);
    }

    @Override
    public Feedbacks getFeedback(Long IdFeedbacks) {
        return feedbacksRepository.findById(IdFeedbacks).orElse(null);
    }

    @Override
    public void removeFeedback(Long IdFeedbacks) {
        feedbacksRepository.deleteById(IdFeedbacks);
    }

    /*@Override
    public Feedbacks addAndAssigntoProduct(Feedbacks f, Integer IdProduct) {
        feedbacksRepository.save(f);
        Products product = productsRepository.findById(IdProduct).orElse(null);
        Set<Feedbacks> feedbacks = new HashSet<>();
        feedbacks.add(product);
        f.setFeedbacksProd(feedbacks);
        return null;
    }*/
}