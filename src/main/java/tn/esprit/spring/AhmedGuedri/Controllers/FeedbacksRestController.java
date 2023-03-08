package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.FeedbackService;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/feedbacks")

public class FeedbacksRestController {

    FeedbackService feedbackService;
    @PostMapping("/add")
    Feedbacks addFeedback(@RequestBody Feedbacks feedback){
        return feedbackService.addOrUpdateFeedback(feedback);
    }
    @PostMapping("/update")
    Feedbacks updateFeedback(@RequestBody Feedbacks feedback){
        return feedbackService.addOrUpdateFeedback(feedback);
    }
    @GetMapping("/get/{id}")
    Feedbacks getFeedback(@PathVariable("id") Long Id){
        return feedbackService.getFeedback(Id);
    }
    @GetMapping("/all")
    List<Feedbacks> getAllFeedbacks(){return feedbackService.retrieveAllFeedbacks();}
    @DeleteMapping("/delete/{id}")
    void deleteInquiry(@PathVariable("id") Long Id){
        feedbackService.removeFeedback(Id);
    }

}
