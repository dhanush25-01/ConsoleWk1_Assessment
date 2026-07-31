import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolder;
    private int accountNumber;
    private String pin;
    private BigDecimal balance;

    public Account(String accountHolder, int accountNumber, String pin, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = new BigDecimal(String.valueOf(balance));
    }

    public Account(String accountHolder, int accountNumber, String pin, BigDecimal balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean login(int accNo, String enteredPin) {
    
        return accountNumber == accNo && pin.equals(enteredPin);
    }


    public void deposit(double amount) {
        BigDecimal depositAmount = BigDecimal.valueOf(amount);

       
        if (depositAmount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(depositAmount);
            System.out.println("Deposit Successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

  
    public void withdraw(float amount) {
        BigDecimal withdrawAmount = new BigDecimal(Float.toString(amount));

 
        if (withdrawAmount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } 
      
        else if (balance.compareTo(withdrawAmount) >= 0) {
            balance = balance.subtract(withdrawAmount);
            System.out.println("Withdrawal Successful.");
        } 

        else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void checkBalance() {
        BigDecimal formattedBalance = balance.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Current Balance: ₹" + formattedBalance);
    }

    public void details() {
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
    }
}

public class Main {

    private static Account findAccount(ArrayList<Account> accounts, int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Dhanush", 101, "1234", 500.00));

        int mainChoice;

        do {
            System.out.println("\n==============================");
            System.out.println("        BANKING SYSTEM        ");
            System.out.println("==============================");
            System.out.println("1. Login to Account");
            System.out.println("2. Create New Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Exit System");
            System.out.print("Enter Choice: ");

            mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    String pin = sc.next();

                    Account currentAcc = findAccount(accounts, accNo);

                    if (currentAcc == null || !currentAcc.login(accNo, pin)) {
                        System.out.println("Invalid Account Number or PIN.");
                    } else {
                        System.out.println("\nLogin Successful!");
                        int userChoice;

                        do {
                            System.out.println("\n      ACCOUNT MENU      ");
                            System.out.println("1. Account Details");
                            System.out.println("2. Deposit (using Double input)");
                            System.out.println("3. Withdraw (using Float input)");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Logout");

                            System.out.print("Enter Choice: ");
                            userChoice = sc.nextInt();

                            switch (userChoice) {
                                case 1:
                                    currentAcc.details();
                                    break;
                                case 2:
                                    System.out.print("Enter Amount: ");
                                    double dAmount = sc.nextDouble(); 
                                    currentAcc.deposit(dAmount);
                                    break;
                                case 3:
                                    System.out.print("Enter Amount: ");
                                    float fAmount = sc.nextFloat(); 
                                    currentAcc.withdraw(fAmount);
                                    break;
                                case 4:
                                    currentAcc.checkBalance();
                                    break;
                                case 5:
                                    System.out.println("Logged out successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid Choice.");
                            }
                        } while (userChoice != 5);
                    }
                    break;

                case 2:
                    System.out.print("Enter Account Holder Name: ");
                    sc.nextLine(); 
                    String name = sc.nextLine();
                    System.out.print("Enter Desired Account Number: ");
                    int newAccNo = sc.nextInt();

                    if (findAccount(accounts, newAccNo) != null) {
                        System.out.println("Account Number already exists!");
                        break;
                    }

                    System.out.print("Set 4-digit PIN: ");
                    String newPin = sc.next();

                    System.out.print("Enter Initial Deposit Amount: ");
                    double initialBalance = sc.nextDouble();

                    BigDecimal initialBD = BigDecimal.valueOf(initialBalance);

                    accounts.add(new Account(name, newAccNo, newPin, initialBD));
                    System.out.println("Account Created Successfully!");
                    break;

                case 3:
                    System.out.print("Enter Account Number to Delete: ");
                    int delAccNo = sc.nextInt();
                    System.out.print("Enter PIN for Verification: ");
                    String delPin = sc.next();
                    Account delAcc = findAccount(accounts, delAccNo);

                    if (delAcc != null && delAcc.login(delAccNo, delPin)) {
                        accounts.remove(delAcc);
                        System.out.println("Account Deleted Successfully.");
                    } else {
                        System.out.println("Deletion Failed: Invalid Account Number or PIN.");
                    }
                    break;

                case 4:
                    System.out.println("Thank You for using Banking System!");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (mainChoice != 4);

        sc.close();
    }
}
