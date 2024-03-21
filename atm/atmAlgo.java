import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Interface for bank account
interface BankAccount {
    String getAccountNumber();
    double getBalance();
    void withdraw(double amount);
    void deposit(double amount);
}

// CurrentAccount class represents a current bank account
class CurrentAccount implements BankAccount {
    private String accountNumber;
    private double balance;

    public CurrentAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

// SavingsAccount class represents a savings bank account
class SavingsAccount implements BankAccount {
    private String accountNumber;
    private double balance;

    public SavingsAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

// Bank class represents a bank containing multiple accounts
class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(String accountNumber, BankAccount account) {
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

// ATM class represents the ATM machine
public class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, Scanner scanner, String accountNumber) {
        BankAccount account = bank.getAccount(accountNumber);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        switch (choice) {
            case 1:
                checkBalance(account);
                break;
            case 2:
                withdraw(scanner, account);
                break;
            case 3:
                deposit(scanner, account);
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void checkBalance(BankAccount account) {
        System.out.println("Current Balance: " + account.getBalance());
    }

    private void withdraw(Scanner scanner, BankAccount account) {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);
    }

    private void deposit(Scanner scanner, BankAccount account) {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
    }

    private void exit() {
        System.out.println("Thank you for using the ATM.");
        System.exit(0);
    }

    public static void main(String[] args) {
        // Create a bank
        Bank bank = new Bank();

        // Create accounts and add them to the bank
        bank.addAccount("123456", new CurrentAccount("123456", 1000.0));
        bank.addAccount("789012", new SavingsAccount("789012", 500.0));

        // Create an ATM with the bank
        ATM atm = new ATM(bank);

        // Simulate ATM operations
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            atm.performTransaction(choice, scanner, accountNumber);
        }
    }
}
