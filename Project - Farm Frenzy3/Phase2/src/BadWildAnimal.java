public class BadWildAnimal extends Animal{
    /*speed : Div/Time*/
    private int sellPrice;
    private int timeOfStart;
    private int speed;
    private int cagesRequiredCount;
    private int cagesCount = 0;
    private int lastCheck;
    public void cager (int time)
    {
        if (cagesCount<cagesRequiredCount) {
            cagesCount++;
            lastCheck = time;
        }
    }
    public void cageChecker (int time)
    {
        if (time>lastCheck+1){
            if (cagesCount >0)
                cagesCount--;
        }
    }

    public int getCagesRequiredCount() {
        return cagesRequiredCount;
    }

    public int getCagesCount() {
        return cagesCount;
    }

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
    public BadWildAnimal(String name, int timeOfStart) {
        super(name);
        this.timeOfStart = timeOfStart;
        if (name.equals("lion")){
                speed=1;
                cagesRequiredCount=4;
                sellPrice=300;
        }else if (name.equals("bear")){
                speed=1;
                cagesRequiredCount=3;
                sellPrice=400;
        }else if (name.equals("tiger")){
                speed=2;
                cagesRequiredCount=4;
                sellPrice=500;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setCagesCount(int cagesCount) {
        this.cagesCount = cagesCount;
    }
}