class Order {
    int goodID;
    int goodCount;
    int orderTimeBuyPrice;
    int orderTimeSellPrice;
    String orderID; //or userID
    String Date;
    boolean isCheckedOut;

    public Order(int goodID, int goodCount, String orderID, String date,
                 boolean isCheckedOut, int orderTimeBuyPrice, int orderTimeSellPrice) {
        this.goodID = goodID;
        this.goodCount = goodCount;
        this.orderID = orderID;
        Date = date;
        this.isCheckedOut = isCheckedOut;
        this.orderTimeBuyPrice = orderTimeBuyPrice;
        this.orderTimeSellPrice = orderTimeSellPrice;
    }
}
