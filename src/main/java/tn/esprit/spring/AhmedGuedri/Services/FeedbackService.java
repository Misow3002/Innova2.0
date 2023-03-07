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
    ProductService productsService;
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

    //get feedbacks by product
    @Override
    public List<Feedbacks> getFeedbacksByProduct(Long IdProduct) {
        Products product = productsService.getProduct(IdProduct);
        return feedbacksRepository.findByFeedbacksProd(product);
    }

//add and assign to product
    @Override
    public Feedbacks addAndAssigntoProduct(Feedbacks e, Long IdProduct) {
        Products product = productsService.getProduct(IdProduct);
        e.setFeedbacksProd(product);
        return feedbacksRepository.save(e);
    }
    //get average of feedbacks on a selected product
    @Override
    public float getAverageFeedbacks(Long IdProduct) {
        List<Feedbacks> feedbacks = getFeedbacksByProduct(IdProduct);
        float sum = 0;
        for (Feedbacks f : feedbacks) {
            sum += f.getStars();
        }
        return sum / feedbacks.size();
    }
//get average of feedbacks with 5 starts on all products
    @Override
    public float getAverageFeedbacks5Stars() {
        List<Feedbacks> feedbacks = feedbacksRepository.findAll();
        float sum = 0;
        int count = 0;
        for (Feedbacks f : feedbacks) {
            if (f.getStars() == 5) {
                sum += f.getStars();
                count++;
            }
        }
        return sum / count;
    }

//get an average of feedbacks on all prodcuts
    
    public float getAverageFeedbacksAllProducts() {
        List<Products> products = productsService.retrieveAllProducts();
        float sum = 0;
        int count = 0;
        for (Products p : products) {
            sum += getAverageFeedbacks(p.getIdProducts());
            count++;
        }
        return sum / count;
    }

}

