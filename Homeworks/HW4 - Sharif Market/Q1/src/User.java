public class User {
    private String userName;
    private String password;
    private int money;

    public User(String userName, String password, int money) {
        this.userName = userName;
        this.password = password;
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
