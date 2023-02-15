package tn.esprit.spring.AhmedGuedri.Services;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
import java.util.List;


public interface IInquiryService {
    public List<Inquiry> retrieveAllInquiries();
    public Inquiry addInquiry(Inquiry i);
    public void deleteInquiry(String id);
    public Inquiry updateInquiry(Inquiry i);
    public Inquiry retrieveInquiry(String id);

}
