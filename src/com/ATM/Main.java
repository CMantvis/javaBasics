package com.ATM;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank theBank = new Bank("Bank of Drausin");

        User aUser = theBank.addUser("John", "Doe", "1234");

        Account newAccount = new Account("Checking", aUser, theBank);

        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            curUser = Main.mainMenuPrompt(theBank, sc);

            Main.printUserMenu(curUser, sc);

            }
        }

        public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userId;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\n Welcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: " );
            userId = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userId, pin);
            if (authUser == null) {
                System.out.println("Incorrect user Id/pin combination.");
            }
        } while( authUser == null);
        return authUser;
        }

        public static void printUserMenu(User theUser, Scanner sc) {
            theUser.printAccountsSummary();

            int choice;

            //user menu
            do {
                System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
                System.out.println(" 1) Show account transaction history");
                System.out.println(" 2) Withdrawal");
                System.out.println(" 3) Deposit");
                System.out.println(" 4) Transfer");
                System.out.println(" 5) Quit");
                System.out.println();
                System.out.println("Enter choice: ");
                choice = sc.nextInt();

                if (choice < 1 || choice >5) {
                    System.out.println("Invalid choice. Please choose 1-5");
                }
            } while (choice <1 || choice >5);

            switch (choice) {
                case 1:
                    Main.showTransHistory(theUser, sc);
                    break;
                case 2:
                    Main.withdrawlFunds(theUser, sc);
                    break;
                case 3:
                    Main.depositFunds(theUser, sc);
                    break;
                case 4:
                    Main.trasnferFunds(theUser, sc);
                    break;
            }

            // redisplay unless user wants to quit
            if (choice !=5) {
                Main.printUserMenu(theUser, sc);
            }
        }

        public static void showTransHistory(User theUser, Scanner sc) {

            int theAcct;

            do {
                System.out.printf("Enter the number (1-%d of the account\n " + "whose transactions you want to see: ", theUser.numAccounts());
                theAcct = sc.nextInt()-1;
                if (theAcct < 0 || theAcct >=theUser.numAccounts()) {
                    System.out.println("Invalid account");
                }
            } while(theAcct < 0 || theAcct >=theUser.numAccounts());
            theUser.printAcctTransHistory();

        }

    }

