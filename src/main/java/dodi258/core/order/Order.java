package dodi258.core.order;

public class Order {
    private Long memberId;
    private String itemName;
    private Long price;

    public Order(Long memberId, String itemName, Long price) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.price = price;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
