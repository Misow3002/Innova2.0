package tn.esprit.spring.AhmedGuedri.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ByteArrayResource;
@Service
public class ServiceEmail {
    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private  PDFGeneratorService pdfGeneratorService;

    @Autowired
    public ServiceEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail( String email, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }
}

