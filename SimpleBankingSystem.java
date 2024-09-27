package SimpleBankingSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String password;

    public Account(String accountNumber, String accountHolderName, String password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0; // initial balance is 0
        this.password = password; // store password securely (for simplicity, plain text here)
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred: $" + amount + " to " + targetAccount.getAccountHolderName());
        } else if (amount > balance) {
            System.out.println("Insufficient funds for transfer.");
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }

    @Override
    public String toString() {
        return accountNumber + "," + accountHolderName + "," + balance + "," + password;
    }
}

class BankingSystem {
    private ArrayList<Account> accounts;
    private final String DATA_FILE = "accounts.txt";

    public BankingSystem() {
        accounts = new ArrayList<>();
        loadAccounts(); // Load accounts from file at startup
    }

    public void createAccount(String accountNumber, String accountHolderName, String password) {
        Account newAccount = new Account(accountNumber, accountHolderName, password);
        accounts.add(newAccount);
        saveAccounts(); // Save accounts to file after creation
        System.out.println("SimpleBankingSystem.Account created for " + accountHolderName + " with account number " + accountNumber);
    }

    public Account getAccount(String accountNumber, String password) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        System.out.println("\nAll Accounts:");
        for (Account account : accounts) {
            System.out.println("SimpleBankingSystem.Account Number: " + account.getAccountNumber() +
                    ", Holder Name: " + account.getAccountHolderName() +
                    ", Balance: $" + account.getBalance());
        }
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String accountNumber = data[0];
                    String accountHolderName = data[1];
                    double balance = Double.parseDouble(data[2]);
                    String password = data[3];
                    Account account = new Account(accountNumber, accountHolderName, password);
                    account.deposit(balance); // set initial balance
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Account account : accounts) {
                bw.write(account.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
}

public class SimpleBankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem();

        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create SimpleBankingSystem.Account");
            System.out.println("2. Login to SimpleBankingSystem.Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter SimpleBankingSystem.Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter SimpleBankingSystem.Account Holder Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    bankingSystem.createAccount(accountNumber, accountHolderName, password);
                    break;

                case 2:
                    System.out.print("Enter SimpleBankingSystem.Account Number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    password = scanner.nextLine();
                    Account loggedInAccount = bankingSystem.getAccount(accountNumber, password);
                    if (loggedInAccount != null) {
                        handleAccountMenu(loggedInAccount, bankingSystem);
                    } else {
                        System.out.println("Invalid account number or password.");
                    }
                    break;

                case 3:
                    bankingSystem.displayAllAccounts();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void handleAccountMenu(Account account, BankingSystem bankingSystem) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- SimpleBankingSystem.Account Menu for " + account.getAccountHolderName() + " ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds");
            System.out.println("4. Check Balance");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    bankingSystem.saveAccounts(); // Save after each transaction
                    break;

                case 2:
                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    bankingSystem.saveAccounts(); // Save after each transaction
                    break;

                case 3:
                    System.out.print("Enter Recipient SimpleBankingSystem.Account Number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    Account recipientAccount = bankingSystem.getAccount(recipientAccountNumber, account.getPassword()); // No password for recipient
                    if (recipientAccount != null) {
                        System.out.print("Enter Transfer Amount: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(recipientAccount, transferAmount);
                        bankingSystem.saveAccounts(); // Save after each transaction
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                    break;

                case 4:
                    System.out.println("SimpleBankingSystem.Account Balance: $" + account.getBalance());
                    break;

                case 5:
                    System.out.println("Thank you for banking with us!");
                    System.out.println("Logging out...");
                    return; // return to main menu

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
