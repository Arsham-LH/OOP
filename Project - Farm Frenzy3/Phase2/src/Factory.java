public class Factory {
    private int x;
    private int y;
    private int level;
    private String name;
    private String inputGoodName;
    private String outputGoodName;
    private int timeTakes;
    private int buildPrice;
    private boolean isBuilt;
    private boolean isWorking = false;
    private int workingRemainingTime = -1;

    public Factory(int level, String name, boolean isBuilt/* , int x , int y*/) {
//        this.x = x;
//        this.y = y;
        this.level = level;
        this.name = name;
        switch (name){
            case "mill":
                this.x = 0;
                this.y = 1;
                this.timeTakes = 4;
                this.buildPrice = 150;
                this.inputGoodName = "egg";
                this.outputGoodName = "flour";
                break;
            case "weavingF":
                this.x = 0;
                this.y = 3;
                this.timeTakes = 5;
                this.buildPrice = 250;
                this.inputGoodName = "feather";
                this.outputGoodName = "cloth";
                break;
            case "packingMilk":
                this.x = 0;
                this.y = 5;
                this.timeTakes = 6;
                this.buildPrice = 400;
                this.inputGoodName = "milk";
                this.outputGoodName = "p_milk";
                break;
            case "bakery":
                this.x = 7;
                this.y = 1;
                this.timeTakes = 5;
                this.buildPrice = 250;
                this.inputGoodName = "flour";
                this.outputGoodName = "bread";
                break;
            case "sewingF":
                this.x = 7;
                this.y = 3;
                this.timeTakes = 6;
                this.buildPrice = 400;
                this.inputGoodName = "cloth";
                this.outputGoodName = "shirt";
                break;
            case "iceCreamF":
                this.x = 7;
                this.y = 5;
                this.timeTakes = 7;
                this.buildPrice = 550;
                this.inputGoodName = "p_milk";
                this.outputGoodName = "iceCream";
                break;
            case "aviculture":
                this.x = 7;
                this.y = 5;
                this.timeTakes = 7;
                this.buildPrice = 300;
                this.inputGoodName = "egg";
                this.outputGoodName = "chicken";
        }
        this.isBuilt = isBuilt;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public void setBuilt(boolean built) {
        isBuilt = built;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setInputGoodName(String inputGoodName) {
        this.inputGoodName = inputGoodName;
    }

    public void setOutputGoodName(String outputGoodName) {
        this.outputGoodName = outputGoodName;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void setWorkingRemainingTime(int workingRemainingTime) {
        this.workingRemainingTime = workingRemainingTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getInputGoodName() {
        return inputGoodName;
    }

    public String getOutputGoodName() {
        return outputGoodName;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public int getWorkingRemainingTime() {
        return workingRemainingTime;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getInputGood() {
        return inputGoodName;
    }

    public String getOutputGood() {
        return outputGoodName;
    }

    public int getTimeTakes() {
        return timeTakes;
    }

    public int getBuildPrice() {
        return buildPrice;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInputGood(String inputGoodName) {
        this.inputGoodName = inputGoodName;
    }

    public void setOutputGood(String outputGoodName) {
        this.outputGoodName = outputGoodName;
    }

    public void setTimeTakes(int timeTakes) {
        this.timeTakes = timeTakes;
    }

    public void setBuildPrice(int buildPrice) {
        this.buildPrice = buildPrice;
    }

    public String getFullFactoryName(){
        switch (name){
            case "mill":
                return "mill";
            case "weavingF":
                return "weaving factory";
            case "packingMilk":
                return "milk packaging";
            case "bakery":
                return "bakery";
            case "sewingF":
                return "sewing factory";
            case "iceCreamF":
                return "ice cream production";
            case "aviculture":
                return "aviculture";
        }
        return null;
    }
}