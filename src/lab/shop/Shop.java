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



}
