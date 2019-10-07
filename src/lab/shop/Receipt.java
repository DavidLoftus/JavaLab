package lab.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {
    private List<Product> products;
    private String shopAssistantName;
    private int shopAssistantId;
    private LocalDateTime timeOfTransaction;

    public Receipt() {
        this.products = new ArrayList<Product>();
    }

    public Receipt(String shopAssistantName, int shopAssistantId, List<Product> products, LocalDateTime timeOfTransaction) {
        this.products = products;
        this.shopAssistantName = shopAssistantName;
        this.shopAssistantId = shopAssistantId;
        this.timeOfTransaction = timeOfTransaction;
    }

    public Receipt(String shopAssistantName, int shopAssistantId) {
        this(shopAssistantName, shopAssistantId, new ArrayList<Product>());
    }

    public Receipt(String shopAssistantName, int shopAssistantId, List<Product> products) {
        this(shopAssistantName, shopAssistantId, products, LocalDateTime.now());
    }

    public Receipt(Receipt other) {
        this(other.products, other.shopAssistantName, other.shopAssistantId, other.timeOfTransaction);
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public double calculateTotalCost() {
        double cost = 0.0;
        for (Product p : products) {
            cost += p.getCost();
        }
        return cost;
    }

    public List<Product> getProducts() {
        return products;
    }
}
