public class Good {
     private String name;
     private int ID;
     private int buyPrice;
     private int sellPrice;
     private int remainingCount;
     private int soldCount;
     private long soldPrice;
     private long profit;

    public Good(String name, int ID, int buyPrice, int sellPrice , int remainingCount , int soldCount) {
        this.name = name;
        this.ID = ID;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.remainingCount = remainingCount;
        this.soldCount = soldCount;
        this.soldPrice = 0;
        this.profit = 0;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getProfit() {
        return profit;
    }

    public long getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(long soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRemainingCount() {
        return remainingCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int profitPerCount(){
        return (sellPrice - buyPrice);
    }
}
