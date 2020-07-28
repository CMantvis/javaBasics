package com.ATM;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MD5 hash rather than the original value for security reasons

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException ");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new unique universal id for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts

        this.accounts = new ArrayList<Account>();

        System.out.printf("New user %s, %s with ID %s created.\n ", lastName, firstName, this.uuid);

    }

    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String aPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash );
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException ");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary", this.firstName);
        for (int a = 0; a <this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }
    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }
}
