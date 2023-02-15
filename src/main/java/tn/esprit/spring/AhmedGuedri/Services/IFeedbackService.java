package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;

import java.util.List;

public interface IFeedbackService {
    List<Feedbacks> retrieveAllFeedbacks();
    Feedbacks addOrUpdateFeedback(Feedbacks f);
    Feedbacks getFeedback(Long IdFeedbacks);
    void removeFeedback(Long IdFeedbacks);

    //advanced
    /*Feedbacks addAndAssigntoProduct(Feedbacks e, Integer IdProduct);*/
}
