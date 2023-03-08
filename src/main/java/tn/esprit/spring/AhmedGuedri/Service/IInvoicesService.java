package tn.esprit.spring.AhmedGuedri.Service;

import tn.esprit.spring.AhmedGuedri.entities.Invoices;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Payment;

public interface IInvoicesService {
    public Invoices generateinvoices(Orders orders, Payment payment) throws Exception;
}
