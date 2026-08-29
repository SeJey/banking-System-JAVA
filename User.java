public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private double checkingBalance;
    private double savingsBalance;
    private String email;

    public User(String username, String password, String firstName, String lastName, String accountNumber, double checkingBalance, double savingsBalance, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAccountNumber() { return accountNumber; }
    public String getEmail() { return email; }

    public double getCheckingBalance() { return checkingBalance; }
    public void setCheckingBalance(double balance) { this.checkingBalance = balance; }

    public double getSavingsBalance() { return savingsBalance; }
    public void setSavingsBalance(double balance) { this.savingsBalance = balance; }
}
