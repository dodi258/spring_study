package dodi258.core.order;

public class Order {
    private long memberId;
    private String itemName;
    private int price;
    private double discountPrice;

    public Order(long memberId, String itemName, int price, double discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", productName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }
}
