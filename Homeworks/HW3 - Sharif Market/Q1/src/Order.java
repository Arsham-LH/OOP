public class Order {
    int goodID;
    int goodCount;
    String orderID; //or userID
    String Date;
    boolean isCheckedOut;

    public Order(int goodID, int goodCount, String orderID, String date, boolean isCheckedOut) {
        this.goodID = goodID;
        this.goodCount = goodCount;
        this.orderID = orderID;
        Date = date;
        this.isCheckedOut = isCheckedOut;
    }
}
