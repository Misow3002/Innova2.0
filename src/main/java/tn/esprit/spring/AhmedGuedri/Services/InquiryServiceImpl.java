package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.InquiryRepository;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
@Service

public class InquiryServiceImpl implements IInquiryService {
    @Autowired
    InquiryRepository inquiryRepository;
    @Override
    public List<Inquiry> retrieveAllInquiries() {
        List<Inquiry> inquiries = (List<Inquiry>) inquiryRepository.findAll();
        return inquiries;
    }
    @Override
    public Inquiry addInquiry(Inquiry i) {
        return inquiryRepository.save(i);
    }
    @Override
    public void deleteInquiry(String id) {
        inquiryRepository.deleteById(Long.parseLong(id));
    }
    @Override
    public Inquiry updateInquiry(Inquiry i) {
        return inquiryRepository.save(i);
    }
    @Override
    public Inquiry retrieveInquiry(String id) {
        return inquiryRepository.findById(Long.parseLong(id)).get();
    }
}
