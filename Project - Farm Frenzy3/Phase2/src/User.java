public class User {
    private String userName;
    private String password;
    private int unlockedLevel;
    private int extraCoins;

    public User(String userName, String password, int unlockedLevel, int extraCoins) {
        this.userName = userName;
        this.password = password;
        this.unlockedLevel = unlockedLevel;
        this.extraCoins = extraCoins;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUnlockedLevel() {
        return unlockedLevel;
    }

    public int getExtraCoins() {
        return extraCoins;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUnlockedLevel(int unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }

    public void setExtraCoins(int extraCoins) {
        this.extraCoins = extraCoins;
    }
}
