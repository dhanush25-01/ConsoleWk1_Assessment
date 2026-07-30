import java.util.Scanner;

class Account {
    private String accountHolder;
    private int accountNumber;
    private String pin;
    private double balance;

    public Account(String accountHolder, int accountNumber, String pin, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean login(int accNo, String enteredPin) {
        return accountNumber == accNo && pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit Successful.");
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal Successful.");
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void details() {
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account acc = new Account("Dhanush", 1001, "1234", 5000);

        System.out.println("      BANKING SYSTEM      ");

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        String pin = sc.next();

        if (!acc.login(accNo, pin)) {
            System.out.println("Invalid Account Number or PIN");
            sc.close();
            return;
        }

        int choice;

        do {
            System.out.println("\n      MENU      ");
            System.out.println("1. Account Details");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    acc.details();
                    break;

                case 2:
                    System.out.print("Enter Amount: ");
                    acc.deposit(sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter Amount: ");
                    acc.withdraw(sc.nextDouble());
                    break;

                case 4:
                    acc.checkBalance();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
