package lab.shop;

public class Product {
    public enum Type {
        Coffee, Biscuits, Milk, Bacon, Newspaper;

        public double cost() {
            switch (this) {
                case Coffee:
                    return 4.99;
                case Biscuits:
                    return 2.99;
                case Milk:
                    return 2;
                case Bacon:
                    return 3.79;
                case Newspaper:
                    return 1;
                default:
                    throw new IllegalArgumentException("Unhandled product " + this);
            }
        }
    }

    private Type type;
    private int quantity;

    public Product(Type type, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity can't be negative");
        }
        this.type = type;
        this.quantity = quantity;
    }

    public double getCost() {
        return type.cost() * quantity;
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.quantity + " " + this.type;
    }
}
