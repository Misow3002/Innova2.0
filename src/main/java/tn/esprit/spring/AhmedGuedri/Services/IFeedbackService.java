package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;

import java.util.List;

public interface IFeedbackService {
    List<Feedbacks> retrieveAllFeedbacks();
    Feedbacks addOrUpdateFeedback(Feedbacks f);
    Feedbacks getFeedback(Long IdFeedbacks);
    void removeFeedback(Long IdFeedbacks);
    List<Feedbacks> getFeedbacksByProduct(Long IdProduct);

    Feedbacks addAndAssigntoProduct(Feedbacks e, Long IdProduct);
    float getAverageFeedbacks(Long IdProduct);
    float getAverageFeedbacks5Stars();
}
