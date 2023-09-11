import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ATM {
    //store account
    private ArrayList<AccountHolder> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private AccountHolder loginAcc; //record username after log in

    public void start() {
        while (true) {
            System.out.println("===Welcome to Bank of IIT==");
            System.out.println("1.Account log in");
            System.out.println("2.Account register");
            System.out.println("Please select:");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //log in
                    login();
                    break;
                case 2:
                    //create account
                    createAccount();
                    break;

                default:
                    System.out.println("Input option does not exist. Please select from options again:");
            }
        }
    }

    //show account information
    private void showLoginAccount() {
        System.out.println("===Please your account information as below===");
        System.out.println("Username: " + loginAcc.getUserName());
        System.out.println("Balance: " + loginAcc.getBalance());
        System.out.println("Interest Rate: " + loginAcc.getInterestRate());
    }

    // display after-log-in page
    private void showUserCommand() {
        while (true) {
            System.out.println("Hi " + loginAcc.getUserName() + ", You can select from the following options===");
            System.out.println("1. Check account information");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Log out");
            System.out.println("Please select:");

            int command = sc.nextInt();

            switch (command) {
                case 1:
                    //Check account information
                    showLoginAccount();
                    break;
                case 2:
                    //Deposit
                    depositMoney();
                    break;
                case 3:
                    //withdrawal
                    drawMoney();
                    break;
                case 4:
                    //log out
                    System.out.println("Log out successful!");
                    return;

                default:
                    System.out.println("The option you selected does not exist. Please try again.");
                    break;
            }
        }
    }

    private void drawMoney() {
        System.out.println("===Withdrawal===");

        //if balance is below 50, stop withdrawal
        if(loginAcc.getBalance()<50){
            System.out.println("Your balance is below $50. You cannot withdraw money yet.");
            return;
        }

        while (true) {
            //enter withdrawal amount
            System.out.println("Please enter the withdrawal amount:");
            double money = sc.nextDouble();
// see whether the account has enough balance
            if (loginAcc.getBalance() >= money) {
                //update amount

                loginAcc.setBalance(loginAcc.getBalance() - money);
                System.out.println("You have withdrawn " + money + ", and your current balance is " + loginAcc.getBalance());
           break;
            } else {
                System.out.println("your current balance is " + loginAcc.getBalance() + ". You don't have enough money to withdraw");
            }
        }
    }

    //deposit money
    private void depositMoney() {
        System.out.println("===Deposit===");
        System.out.println("Please enter the deposit amount:");
        double money=sc.nextDouble();

        //update amount

        loginAcc.setBalance(loginAcc.getBalance()+money);
        System.out.println("You have deposited "+ money +", and your current balance is "+loginAcc.getBalance());
    }

    //create new account
    private void createAccount() {
//1. create accountHolder class
        AccountHolder acc = new AccountHolder();

        // input information into accountHolder class
        //username
        while (true) {
            System.out.println("Please enter your user name:");
            String name = sc.next();
            AccountHolder existingAcc = getAccountByUsername(name);
            if (existingAcc == null) {
                acc.setUserName(name);
                break;
            } else {
                System.out.println("The user name you entered already exists.");
            }
        }

        //password
        while (true) {
            System.out.println("Please enter your password:");
            String passWord = sc.next();
            System.out.println("Please enter your password again:");
            String okPassWord = sc.next();
            //see whether the 2 passwords match
            if (okPassWord.equals(passWord)) {
                acc.setPassWord(passWord);
                break;
            } else {
                System.out.println("The 2 passwords you entered are different. Please confirm.");
            }
        }

        //put accountHolder information int accountHolder class
        accounts.add(acc);

        System.out.println("Congratulations " + acc.getUserName() + "! your account has been set up!");

        loginAcc = acc;
        //set initial balance
        System.out.println("Please enter your initial balance:");
        double initialBalance = sc.nextDouble();
        loginAcc.setBalance(initialBalance);
        return;
    }


    //login
    private void login() {
        System.out.println("==system login==");
        // check whether account exist
        //if no account exists, return
        if (accounts.size() == 0) {
            System.out.println("No account in database. Please open an account first.");
            return;//jump out of login
        }

        while (true) {
            System.out.println("Please enter username:");
            String userName = sc.next();
            //see whether username exist
            AccountHolder acc = getAccountByUsername(userName);
            if (acc == null) {
                System.out.println("User name does not exist. Please confirm.");
            } else {
                //account exist
                while (true) {
                    System.out.println("please enter your password:");
                    String passWord = sc.next();
                    //check whether password is correct
                    if (acc.getPassWord().equals(passWord)) {
                        loginAcc = acc;
                        //successful login
                        System.out.println("Hi " + acc.getUserName() + ", login successful!");
                        //Display after-log-in page
                        showUserCommand();
                        return;
                    } else {
                        System.out.println("The password you entered is incorrect. Please confirm.");
                    }
                }
            }
        }

    }


    private AccountHolder getAccountByUsername(String userName) {
        //iterate through the arrayList
        for (int i = 0; i < accounts.size(); i++) {
            AccountHolder acc = accounts.get(i);
            if (acc.getUserName().equals(userName))
                return acc;

        }
        return null;
    }
}

