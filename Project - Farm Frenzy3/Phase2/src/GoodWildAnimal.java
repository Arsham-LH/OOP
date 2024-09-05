public class GoodWildAnimal extends Animal{
    private int buyPrice;

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    public GoodWildAnimal(String name) {
        super(name);
        switch (name){
            case "cat":
            {
                buyPrice=150;
            }
            case "dog":
            {
                buyPrice=100;
            }
        }
    }
}