package softwareEngineering.VirtualBankV1.src.main.java.com.virtualbankv1;
// 用户账户类

public class Account {
    private String username;
    private String password;
    private double balance;
    private boolean status;

    private String type;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setStatus(boolean isActive) {
        this.status = isActive;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Getter methods

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public boolean getStatus() {
        return status;
    }
    public Account(){}
    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
}
