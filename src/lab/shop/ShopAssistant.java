package lab.shop;

public class ShopAssistant {

    private int id;
    private String name;
    private double hoursWorked;
    private double hourlyPay;
    private Shop shop;

    public ShopAssistant(int id, String name) {
        this.id = id;
        this.name = name;

        this.hoursWorked = 0;
        this.hourlyPay = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("can't work negative hours");
        }
        if (hoursWorked > 10) {
            throw new IllegalArgumentException("can't work more than 10 hours");
        }
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        if (hourlyPay < 0) {
            throw new IllegalArgumentException("can't have a negative wage");
        }
        if (hourlyPay > 30 || hourlyPay < 10) {
            throw new IllegalArgumentException("wage must be between 10 and 30");
        }
        this.hourlyPay = hourlyPay;
    }

    public double calculatePay() {
        return this.hourlyPay * this.hoursWorked;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void serve(Customer customer) {

    }
}
