package ex1;


public class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private int balance;

    
    public BankAccount(int accountNumber, String accountHolderName, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getBalance() {
        return balance;
    }

    
    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    
    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
    public void logAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
    
    public static void main(String[] args) {
        BankAccount account = new BankAccount(123, "John Doe", 1000);

        System.out.println("Initial:");
        account.logAccountDetails();

        account.deposit(500);
        System.out.println("\nAfter Deposit:");
        account.logAccountDetails();

        account.withdraw(300);
        System.out.println("\nAfter Withdrawal:");
        account.logAccountDetails();
    }

    
}
