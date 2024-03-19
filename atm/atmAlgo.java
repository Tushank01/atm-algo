import java.util.Scanner;


    // Account class represents a bank account
    class Account {
        private String accountNumber;
        private String pin;
        private double balance;

        public Account(String accountNumber, String pin, double balance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public boolean validatePin(String pin) {
            return this.pin.equals(pin);
        }

        public double getBalance() {
            return balance;
        }

        public void withdraw(double amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful. Current balance: " + balance);
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. Current balance: " + balance);
            } else {
                System.out.println("Invalid amount.");
            }
        }
    }

    // ATM class represents the ATM machine
    class ATM {
        private Account account;

        public ATM(Account account) {
            this.account = account;
        }

        public void displayMenu() {
            System.out.println("ATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
        }

        public void performTransaction(int choice, Scanner scanner) {
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public class atmAlgo {
        public static void main(String[] args) {
            // Create an account
            Account account = new Account("123456", "1234", 1000.0);

            // Create an ATM with the account
            ATM atm = new ATM(account);

            // Simulate ATM operations
            Scanner scanner = new Scanner(System.in);
            while (true) {
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                atm.performTransaction(choice, scanner);
            }
        }
    }


