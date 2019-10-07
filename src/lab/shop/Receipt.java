package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Product> products;

    public Receipt() {
        this.products = new ArrayList<Product>();
    }

    public Receipt(Receipt other) {
        products = new ArrayList<Product>(products);
    }

    public Receipt(List<Product> products) {
        this.products = products;
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
