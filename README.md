#Java-Based Banking Management System
A simple, command-line-based banking system built with Java. This project simulates basic banking operations like account creation, deposits, withdrawals, transfers, and balance inquiries. It is designed for educational purposes to demonstrate object-oriented programming, file I/O, and a menu-driven interface.


**Features**

Account Management:
Create new bank accounts with a unique account number, account holder name, and password.
View all accounts (excluding sensitive information like passwords).

Authentication:
Login with an account number and password to access account-specific features.

Banking Operations:
Deposit money into the account.
Withdraw money (with balance checks).
Transfer funds between accounts.
Check account balance.

Data Persistence:
Accounts and their data are saved to a file (accounts.txt) and automatically loaded on startup, ensuring data is not lost after the program exits.

Interactive Console Menu:
A user-friendly, menu-driven interface for managing banking operations.

**How It Works**

1. Account Creation
Users create accounts by entering an account number, name, and password.
Accounts are stored in an ArrayList and persisted to the accounts.txt file.

3. Authentication
Users can login using their account number and password.
Passwords are stored in plain text in this implementation (for simplicity). This should be replaced with encryption for real-world use.

5. Banking Operations
Deposit: Add funds to your account.
Withdraw: Remove funds from your account (up to the available balance).
Transfer: Move funds to another account by providing its account number.
Check Balance: View the current account balance.

7. File I/O
Data Persistence: Account data is read from and written to a file (accounts.txt) for long-term storage.
Loading Accounts: At startup, accounts are loaded from the file, maintaining continuity across sessions.


**Prerequisites**

Java Development Kit (JDK 8 or later)
A text editor or IDE (e.g., IntelliJ IDEA, Eclipse)


**Usage**

Run the program to access the main menu.

Choose from the following options:

Create Account: Enter the required details to create an account.

Login to Account: Access account-specific features after successful authentication.

Display All Accounts: View all existing accounts (only account numbers and names are displayed for privacy).

Exit: Safely exit the program.

File Details

accounts.txt: Stores account data in the following format:

Example Session

Main Menu

--- Banking System Menu ---

1. Create Account
2. Login to Account
3. Display All Accounts
4. Exit

Choose an option:

Account Menu

--- Account Menu for John Doe ---
1. Deposit
2. Withdraw
3. Transfer Funds
4. Check Balance
5. Logout
Choose an option:

**Limitations & Future Improvements**

Password Security:
Passwords are stored in plain text. Implement encryption for secure storage.

Error Handling:
Improve error messages and input validation.

File Management:
Ensure proper handling for concurrent access or larger datasets.

**Advanced Features:**

Add account deletion, interest calculation, and transaction history.

**Contribution**

Contributions are welcome! If you'd like to improve the system, feel free to fork this repository and submit pull requests.

**License**

This project is licensed under the MIT License. See the LICENSE file for more details.
