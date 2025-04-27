package BankingSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Bank Account Class
class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn ₹" + amount);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: ₹" + balance);
    }
}

// Main Bank System
public class BankingSystem {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using our Banking System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Create a new account
    private static void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        if (accounts.containsKey(accNumber)) {
            System.out.println("Account with this number already exists!");
            return;
        }

        System.out.print("Enter Initial Deposit Amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BankAccount newAccount = new BankAccount(name, accNumber, initialDeposit);
        accounts.put(accNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    // Deposit money into an account
    private static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        BankAccount account = accounts.get(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        account.deposit(amount);
    }

    // Withdraw money from an account
    private static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        BankAccount account = accounts.get(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        account.withdraw(amount);
    }

    // Check account balance
    private static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        BankAccount account = accounts.get(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        account.displayAccountDetails();
    }
}
