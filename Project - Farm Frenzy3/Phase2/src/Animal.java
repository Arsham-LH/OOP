import java.util.Random;

public abstract class Animal {
    private String name;
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    Random random = new Random();

    public Animal(String name) {
        this.name = name;
        this.x = random.nextInt(6) + 1;
        this.y = random.nextInt(6) + 1;
        this.prevX = x;
        this.prevY = y;
    }

    public String getName() {
        return name;
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

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public Animal(String name, int x, int y, Random random) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.random = random;
    }
    public void randomMovement ()
    {
        int i = random.nextInt(5);// 1 chap 2 bala 3 rst 4 pAin
        if (i==0)
        {
            i+=random.nextInt(4)+1;
        }
        if (i==1&&(x>1))
        {
            this.prevX = x;
            this.prevY = y;
            x--;
        }
        else if (i==1&&(x==1))
        {
            randomMovement();
        }
        else if (i==2&&(y>1))
        {
            this.prevX = x;
            this.prevY = y;
            y--;
        }
        else if (i==1&&(y==1))
        {
            randomMovement();
        }
        else if (i==3&&(x<6))
        {
            this.prevX = x;
            this.prevY = y;
            x++;
        }
        else if (i==3&&(x==6))
        {
            randomMovement();
        }
        else if (i==4&&(y<6))
        {
            this.prevX = x;
            this.prevY = y;
            y++;
        }
        else if (i==4&&(y==6))
        {
            randomMovement();
        }
    }
}
