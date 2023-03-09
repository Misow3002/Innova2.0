package tn.esprit.spring.AhmedGuedri.Services;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.Repositories.*;
import tn.esprit.spring.AhmedGuedri.entities.*;
import tn.esprit.spring.AhmedGuedri.entities.Currency;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private JavaMailSender MailSender;
    DetailedOrdersRepository detailedOrdersRepository;
    UserRepository userRepository;
    OrdersRepository ordersRepository;
    CurrencyRepository currencyRepository;
    @Autowired
    ProductsRepository productsRepository;
    private EntityManager entityManager;

    @Override
    public List<Products> retrieveAllProducts() {
        return productsRepository.findAll();
        /*for (Products prod : prods) {
            System.out.println("prod = " + prod);
        }

        return prods;*/
    }

    @Override
    public Products addOrUpdateProduct(Products p, Long Id, CurrencyType currencyType) {
        User supplier = userRepository.findById(Id).orElse(null);
        Currency cur = currencyRepository.findByCurrencyType(currencyType);
        List<Currency> currencies = new ArrayList<>();
        currencies.add(cur);
        p.setCurrencyList(currencies);
        List<User> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        p.setUserProducts(suppliers);
        return productsRepository.save(p);
    }

    @Override
    public Products getProduct(Long IdProducts) {
        return productsRepository.findById(IdProducts).orElse(null);
    }

    public List<Products> getProductsByUser(Long userId) {
        return productsRepository.findByUserId(userId);
    }

    public List<Products> getProductsbyUser(Long IdUser) {
        return productsRepository.findProductsByUserProducts(IdUser);
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
        List<Orders> orders = ordersRepository.findAll();
        for (Orders order : orders) {
            DetailedOrders detailedOrders = new DetailedOrders();
            Long ordernum = order.getIdOrders();
            System.out.println("ordernum = " + ordernum);
            Long prod = order.getProductsList().get(0).getIdProducts();
            System.out.println("prod = " + prod);
            Long supp = order.getProductsList().get(0).getUserProducts().get(0).getId();
            System.out.println("supp = " + supp);
            //Float price = order.getProductsList().get(0).getPrice();
            Float exchangerate = changepricebyrate(order.getProductsList().get(0).getUserProducts().get(0).getId());
            System.out.println("price = " + exchangerate);
            Date date = order.getBroughtDate();
            System.out.println("date = " + date);
            detailedOrders.setOrdernumber(ordernum);
            detailedOrders.setProduct(prod);
            detailedOrders.setSupplier(supp);
            detailedOrders.setPrice(exchangerate);
            detailedOrders.setBoughtDate(date);
            detailedOrdersRepository.save(detailedOrders);
        }
    }

    @Override
    public List<DetailedOrders> getDetailedOrdersbyDaterange(Date startdate, Date enddate, Long Supplier) {
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrdersbyrange = new ArrayList<>();
        float fp = 0;
        for (DetailedOrders dorder : dorders) {
            if (dorder.getBoughtDate().after(startdate) && dorder.getBoughtDate().before(enddate) && (dorder.getSupplier()) == Supplier) {
                fp = fp + dorder.getPrice();
                detailedOrdersbyrange.add(dorder);
            }
        }
        return detailedOrdersbyrange;
    }

    @Override
    public String getStatisticsbyDaterange(Date startdate, Date enddate, Long Supplier) {
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrdersbyrange = new ArrayList<>();
        List<List<Integer>> detailedOrdersByMonth = new ArrayList<>();
        float fp = 0;
        for (DetailedOrders dorder : dorders) {
            if (dorder.getBoughtDate().after(startdate) && dorder.getBoughtDate().before(enddate) && (dorder.getSupplier()) == Supplier) {
                fp = fp + dorder.getPrice();
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

        return ("list of orders by month" + detailedOrdersByMonth + "\n" + "total price of orders :" + fp + "\n"
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
        //List<User> suppliers = userRepository.findUserByRoles(Roles.Provider);
        List<User> suppliers = userRepository.All_Delivery();
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
                message += "Product: " + prod.getNameProducts() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                        ", Total: " + (sales.getPrice() * sales.getQuantity()) + " TND\n";
                System.out.println("Product: " + sales.getProductId() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                        ", Total: " + (sales.getPrice() * sales.getQuantity()) + " TND");
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
        MailSender.send(mailMessage);
    }

    @Scheduled(fixedRate = 60000) // run every minute
    public void warnwhenstockistenough() {
        List<Products> products = (List<Products>) productsRepository.findAll();
        for (Products product : products) {
            if (product.getNumberOfStock() < 10) {
                String message = "Dear " + product.getUserProducts() + ",\n\n";
                message += "The product " + product.getNameProducts() + " is running low on stock.\n\n";
                message += "Best regards,\n";
                message += "The Management";
                sendEmail(MailSender, product.getUserProducts().get(0).getEmail(), "Low Stock Warning", message);
                System.out.println(message);
                System.out.println("Email sent to " + product.getUserProducts().get(0).getEmail());
            }
        }
    }


    public String getnumberofordersbyeveryproductforasupplier(Long id) {
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrdersbyrange = new ArrayList<>();
        float fp = 0;
        for (DetailedOrders dorder : dorders) {
            if (dorder.getSupplier() == id) {
                fp = fp + dorder.getPrice();
                detailedOrdersbyrange.add(dorder);
            }
        }
        List<ProductSales> salesList = new ArrayList<>();
        for (DetailedOrders dorder : detailedOrdersbyrange) {
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
        String message = "";
        for (ProductSales sales : salesList) {
            Products prod = productsRepository.findById(sales.getProductId()).orElse(null);
            System.out.println("Product: " + prod.getNameProducts() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                    ", Total: " + (sales.getPrice() * sales.getQuantity()) + " TND");
            message += "Product: " + prod.getNameProducts() + ", Price: " + sales.getPrice() + ", Quantity: " + sales.getQuantity() +
                    ", Total: " + (sales.getPrice() * sales.getQuantity()) + " TND, Stock: " + prod.getNumberOfStock() + "Unit, Stock is supposed " +
                    "to be: " + (sales.getQuantity() + prod.getNumberOfStock()) + "\n";
        }
        message += "Total price of orders : " + fp + "\n" + "total number of orders : " + detailedOrdersbyrange.size() + "\n";
        message += "PS: The stock is supposed to be the sum of the quantity of orders and the current stock of the product.";
        return message;
    }


    public List<Products> getProductsSortedByNumOrders() {
        List<Object[]> results = productsRepository.findProductsOrderByNumOrders();
        List<Products> sortedProducts = new ArrayList<>();
        for (Object[] result : results) {
            System.out.println("siiii");
            sortedProducts.add((Products) result[0]);

        }
        return sortedProducts;
    }
    public float changepricebyrate(Long id){
        Products prod = productsRepository.findById(id).orElse(null);
        Currency currency = currencyRepository.findById(prod.getCurrencyList().get(0).getIdCurrency()).orElse(null);
        float price = prod.getPrice();
        float rate = currency.getExchangeRate();
        return price * rate;
    }

}


///////////////////////////////
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
