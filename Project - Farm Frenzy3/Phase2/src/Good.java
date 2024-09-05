public class Good {
    private int pickupTime;
    private int spaceRequired;

    private String name;
    private int sellPrice;
    private int count;
    private int timeOfOut = -1;
    private int x;
    private int y;
    public Good(String name, int sellPrice, int count) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.count = count;
    }

    public Good(String name, int count , int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.count = count;
        switch (name){
            case "egg":
                this.sellPrice = 15;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "feather":
                this.sellPrice = 20;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "milk":
                this.sellPrice = 25;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "flour":
                this.sellPrice = 40;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "cloth":
                this.sellPrice = 50;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "p_milk":
                this.sellPrice = 60;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "bread":
                this.sellPrice = 80;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            case "shirt":
                this.sellPrice = 100;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            case "iceCream":
                this.sellPrice = 120;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            default:
                this.sellPrice = 0;
                break;
        }
    }

    public Good(String name, int count) {

        this.name = name;
        this.count = count;
        switch (name){
            case "egg":
                this.sellPrice = 15;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "feather":
                this.sellPrice = 20;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "milk":
                this.sellPrice = 25;
                this.pickupTime = 4;
                this.spaceRequired = 1;
                break;
            case "flour":
                this.sellPrice = 40;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "cloth":
                this.sellPrice = 50;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "p_milk":
                this.sellPrice = 60;
                this.pickupTime = 5;
                this.spaceRequired = 2;
                break;
            case "bread":
                this.sellPrice = 80;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            case "shirt":
                this.sellPrice = 100;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            case "iceCream":
                this.sellPrice = 120;
                this.pickupTime = 6;
                this.spaceRequired =4;
                break;
            case "bear":
                this.sellPrice = 400;
                this.pickupTime = 5;
                this.spaceRequired=15;
            case "lion":
                this.sellPrice = 300;
                this.pickupTime = 5;
                this.spaceRequired=15;
            case "tiger":
                this.sellPrice = 500;
                this.pickupTime = 5;
                this.spaceRequired=15;
            default:
                this.sellPrice = 0;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTimeOfOut() {
        return timeOfOut;
    }

    public void setTimeOfOut(int timeOfOut) {
        this.timeOfOut = timeOfOut;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public int getSpaceRequired() {
        return spaceRequired;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setSpaceRequired(int spaceRequired) {
        this.spaceRequired = spaceRequired;
    }
}