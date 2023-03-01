package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.Repositories.DetailedOrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.OrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    @Autowired
    private JavaMailSender MailSender;
    DetailedOrdersRepository detailedOrdersRepository;
    UserRepository userRepository;
    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;
    private EntityManager entityManager;
    @Override
    public List<Products> retrieveAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products addOrUpdateProduct(Products p) {
        return productsRepository.save(p);
    }

    @Override
    public Products getProduct(Long IdProducts) {
        return productsRepository.findById(IdProducts).orElse(null);
    }

    @Override
    public void removeProduct(Long IdProducts) {
        productsRepository.deleteById(IdProducts);
    }

    @Transactional
    @Override
    @Scheduled(fixedRate = 60000) // run every minute
    public void addOrUpdatedetailedOrders() {
        detailedOrdersRepository.deleteAll();
        detailedOrdersRepository.resetId();
        List<Orders> orders =ordersRepository.findAll();
        for (Orders order : orders){
            DetailedOrders detailedOrders = new DetailedOrders();
            Long ordernum = order.getIdOrders();
            System.out.println("ordernum = " + ordernum);
            Long prod = order.getProductsList().get(0).getIdProducts();
            System.out.println("prod = " + prod);
            Long supp = order.getProductsList().get(0).getUserProducts().get(0).getId();
            System.out.println("supp = " + supp);
            Float price = order.getProductsList().get(0).getPrice();
            System.out.println("price = " + price);
            Date date = order.getBroughtDate();
            System.out.println("date = " + date);
            detailedOrders.setOrdernumber(ordernum);
            detailedOrders.setProduct(prod);
            detailedOrders.setSupplier(supp);
            detailedOrders.setPrice(price);
            detailedOrders.setBoughtDate(date);
            detailedOrdersRepository.save(detailedOrders);
        }
    }
    @Override
    public List<DetailedOrders> getDetailedOrdersbyDaterange(Date startdate, Date enddate, Long Supplier){
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrdersbyrange = new ArrayList<>();
        float fp=0;
        for(DetailedOrders dorder : dorders){
            if(dorder.getBoughtDate().after(startdate) && dorder.getBoughtDate().before(enddate) && (dorder.getSupplier()) == Supplier){
                fp=fp+dorder.getPrice();
                detailedOrdersbyrange.add(dorder);
            }
        }
        return detailedOrdersbyrange;
    }
    @Override
    public String getStatisticsbyDaterange(Date startdate, Date enddate, Long Supplier){
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrdersbyrange = new ArrayList<>();
        List<List<Integer>> detailedOrdersByMonth = new ArrayList<>();
        float fp=0;
        for(DetailedOrders dorder : dorders){
            if(dorder.getBoughtDate().after(startdate) && dorder.getBoughtDate().before(enddate) && (dorder.getSupplier()) == Supplier){
                fp=fp+dorder.getPrice();
                detailedOrdersbyrange.add(dorder);
            }
        }
        LocalDate startDate = startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = enddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusMonths(1)) {
            int year = date.getYear();
            int month = date.getMonthValue();

            List<Integer> ordersByMonth = new ArrayList<>();

            for (DetailedOrders dorder : dorders) {
                LocalDate orderDate = dorder.getBoughtDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (orderDate.getYear() == year && orderDate.getMonthValue() == month && dorder.getSupplier() == Supplier) {
                    ordersByMonth.add(1);
                }
            }

            detailedOrdersByMonth.add(ordersByMonth);
        }

        return ("list of orders by month" + detailedOrdersByMonth +"\n"+ "total price of orders :" + fp+ "\n"
                + "total number of orders :" + detailedOrdersbyrange.size());
    }

    public void resetId() {
        Query resetQuery = entityManager.createNativeQuery("ALTER TABLE detailed_orders AUTO_INCREMENT = 1");
        resetQuery.executeUpdate();
    }
    //@Scheduled(fixedRate = 60000) // run every minute
    @Scheduled(cron = "0 0 1 1 * *")
    public void sendMonthlyReport() {
        Date startDate = Date.from((LocalDate.now().minusMonths(1)).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<User> suppliers = userRepository.findUserByRoles(Roles.Provider);
        for (User supplier : suppliers) {
            Long supid = supplier.getId();
            List<DetailedOrders> detailedOrders = getDetailedOrdersbyDaterange(startDate, endDate, supid);
            float fp = 0;
            for (DetailedOrders dorder : detailedOrders) {
                fp = fp + dorder.getPrice();
            }
            String message = "Dear " + supplier.getFirstName() + " " + supplier.getLastName() + ",\n\n";
            message += "Here are your monthly orders for the period " + startDate + " to " + endDate + ".\n\n";
            message += "Total amount of products sold: " + fp + " TND.\n\n";
            message += "List of products sold:\n";
            List<ProductSales> salesList = new ArrayList<>();
            for (DetailedOrders dorder : detailedOrders) {
                boolean found = false;
                for (ProductSales sales : salesList) {
                    if (sales.getProductId() == dorder.getProduct()) {
                        sales.incrementQuantity();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ProductSales newSales = new ProductSales(dorder.getProduct(), dorder.getPrice(), 1);
                    salesList.add(newSales);
                }
            }

// print the sales list
            for (ProductSales sales : salesList) {
                Products prod = productsRepository.findById(sales.getProductId()).orElse(null);
                message+= "Product: " + prod.getNameProducts() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                        ", Total: "+ (sales.getPrice() * sales.getQuantity()) + " TND\n";
                System.out.println("Product: " + sales.getProductId() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                        ", Total: "+ (sales.getPrice() * sales.getQuantity()) + " TND");
            }
            message += "Best regards,\n";
            message += "The Management";
            sendEmail(MailSender, supplier.getEmail(), "Monthly Report From Ratatoskr", message);
            System.out.println(message);
            System.out.println("Email sent to " + supplier.getEmail());
        }
    }


    public void sendEmail(JavaMailSender MailSender, String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        MailSender.send(mailMessage);}
}
class ProductSales {
    private Long productId;
    private Float price;
    private int quantity;

    public ProductSales(Long productId, Float price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }
}
