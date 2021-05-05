package dodi258.core.order;

public class Order {
    private long memberId;
    private String itemName;
    private int price;
    private int discountPrice;

    public Order(long memberId, String itemName, int price, int discountPrice) {
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

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
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
