package lab.shop;

public class Shop {

    private ShopAssistant shopAssistant;
    private CashRegister cashRegister;

    private Product bacon, biscuits, coffee, milk, newspaper;

    public Shop(int storageSize, CashRegister cashRegister, ShopAssistant sa) {
        this.shopAssistant = sa;
        this.shopAssistant.setHourlyPay(12);
        this.shopAssistant.setShop(this);

        this.cashRegister = cashRegister;

        this.bacon = new Product(Product.Type.Bacon, storageSize);
        this.biscuits = new Product(Product.Type.Biscuits, storageSize);
        this.coffee = new Product(Product.Type.Coffee, storageSize);
        this.milk = new Product(Product.Type.Milk, storageSize);
        this.newspaper = new Product(Product.Type.Newspaper, storageSize);
    }

    public Shop(int storageSize, double openingCredit, ShopAssistant sa) {
        this(storageSize, new CashRegister(openingCredit), sa);
    }

    public void walkIn(Customer c) {
        System.out.println("Serving " + c);
        shopAssistant.serve(c);
    }

    public boolean hasProduct(Product p) {
        switch (p.getType()) {
            case Coffee:
                return coffee.getQuantity() >= p.getQuantity();
            case Biscuits:
                return biscuits.getQuantity() >= p.getQuantity();
            case Milk:
                return milk.getQuantity() >= p.getQuantity();
            case Bacon:
                return bacon.getQuantity() >= p.getQuantity();
            case Newspaper:
                return newspaper.getQuantity() >= p.getQuantity();
            default:
                throw new IllegalArgumentException("Unhandled product type " + p.getType());
        }
    }

    public Product getBacon() {
        return bacon;
    }

    public Product getBiscuits() {
        return biscuits;
    }

    public Product getCoffee() {
        return coffee;
    }

    public Product getMilk() {
        return milk;
    }

    public Product getNewspaper() {
        return newspaper;
    }

    public void soldProduct(Product p) {
        if (!hasProduct(p)) {
            throw new IllegalArgumentException("Insufficient stock for " + p);
        }

        switch (p.getType()) {
            case Coffee:
                coffee.setQuantity(coffee.getQuantity() - p.getQuantity());
                break;
            case Biscuits:
                biscuits.setQuantity(biscuits.getQuantity() - p.getQuantity());
                break;
            case Milk:
                milk.setQuantity(milk.getQuantity() - p.getQuantity());
                break;
            case Bacon:
                bacon.setQuantity(bacon.getQuantity() - p.getQuantity());
                break;
            case Newspaper:
                newspaper.setQuantity(newspaper.getQuantity() - p.getQuantity());
                break;
            default:
                throw new IllegalArgumentException("Unhandled product type " + p.getType());
        }


    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    public void close() {
        shopAssistant.setHoursWorked(5);
        cashRegister.remove(shopAssistant.calculatePay());
        System.out.println("Today’s transactions");
        cashRegister.printLastTransactions(100);
        System.out.println("Current Product Numbers");
        System.out.println(bacon);
        System.out.println(biscuits);
        System.out.println(coffee);
        System.out.println(milk);
        System.out.println(newspaper);
    }

    public static void main(String[] args) {
        Shop shop = new Shop(100, 200, new ShopAssistant(1, "Frank"));
        for (int i = 0; i < 10; i++) {
            try {
                shop.walkIn(new Customer());
            } catch(ShopException e) {
                System.out.println(e.getMessage());
            }
        }
        shop.close();
    }
}

class ShopException extends RuntimeException {
    public ShopException(String s) {
        super(s);
    }
}