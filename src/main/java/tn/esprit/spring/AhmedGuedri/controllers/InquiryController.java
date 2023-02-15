 package tn.esprit.spring.AhmedGuedri.controllers;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.DeleteMapping;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.PutMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RestController;
 import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
 import tn.esprit.spring.AhmedGuedri.services.IInquiryService;
 @RestController
 @CrossOrigin(origins = "http:localhost:4200")
 public class InquiryController {
     @Autowired
     IInquiryService iis;
     @GetMapping("/retrieve-all-Inquiries")
     public List<Inquiry> getInquiries() {
         List<Inquiry> list = iis.retrieveAllInquiries();
         return list;
     }
     @PostMapping("/add-Inquiry")
     public Inquiry addInquiry(@RequestBody Inquiry i) {
         return iis.addInquiry(i);
     }
     @DeleteMapping("/remove-Inquiry/{Inquiry-id}")
     public void removeInquiry(@PathVariable("Inquiry-id") String id) {
         iis.deleteInquiry(id);
     }
     @PutMapping("/modify-Inquiry")
     public Inquiry modifyInquiry(@RequestBody Inquiry i) {
         return iis.updateInquiry(i);
     }
     @GetMapping("/retrieve-Inquiry/{Inquiry-id}")
     public Inquiry retrieveInquiry(@PathVariable("Inquiry-id") String id) {
         return iis.retrieveInquiry(id);
     }
 }

