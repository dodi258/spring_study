package dodi258.core.member;

public enum Grade {

    BASIC(0, 0),
    VIP(1000, 0.1);

    private int fixedDiscountAmount;
    private double rateDiscountAmount;

    Grade(int fixedDiscountAmount, double rateDiscountAmount) {
        this.fixedDiscountAmount = fixedDiscountAmount;
        this.rateDiscountAmount = rateDiscountAmount;
    }

    public int getFixedDiscountAmount() {
        return this.fixedDiscountAmount;
    }

    public double getRateDiscountAmount() {
        return this.rateDiscountAmount;
    }

}
