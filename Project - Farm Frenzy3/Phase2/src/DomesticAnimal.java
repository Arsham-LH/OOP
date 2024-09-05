public class DomesticAnimal extends Animal{
    private int buyPrice;
    private Good product;
    private int life;
    private int producingTime;
    private int timeFromBirth;

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getX() {
        return super.getX();
    }

    public int getBuyPrice() {
        return buyPrice;
    }
    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }
    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Good getProduct() {
        return product;
    }

    public void setProduct(Good product) {
        this.product = product;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getProducingTime() {
        return producingTime;
    }

    public void setProducingTime(int producingTime) {
        this.producingTime = producingTime;
    }

    public int getTimeFromBirth() {
        return timeFromBirth;
    }

    public void setTimeFromBirth(int timeFromBirth) {
        this.timeFromBirth = timeFromBirth;
    }

    public boolean checkGoodTime ()
    {
        timeFromBirth++;
        if (timeFromBirth%producingTime==0)
            return true;
        return false;
    }
    DomesticAnimal(String name){
        super(name);
        timeFromBirth=0;
        switch (name){
            case "chicken":
            {
                buyPrice=100;
                product=new Good("egg",15,1);
                life=100;
                producingTime=2;
                break;
            }
            case "turkey":
            {
                buyPrice=200;
                producingTime=3;
                life=100;
                product=new Good("feather",20,1);
                break;
            }
            case "buffalo":
            {
                buyPrice=400;
                producingTime=5;
                life=100;
                product=new Good("milk",25,1);
                break;
            }
        }
    }
}
