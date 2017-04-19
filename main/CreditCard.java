package main;

/**
 * Created by rliu on 4/18/17.
 */
public class CreditCard implements Comparable<CreditCard> {
    private long creditCardNumber;
    private int balance;
    private int creditLine;
    private boolean isValidCreditCard;
    private CreditCardUser creditCardUser;

    CreditCard(long creditCardNumber, CreditCardUser user, int creditLine) {
        this.creditCardNumber = creditCardNumber;
        this.balance = 0;
        this.creditLine = creditLine;
        this.creditCardUser = user;
    }

    public long getNumber() {
        return creditCardNumber;
    }

    //get and set balance
    public int getBalance() {
        return balance;
    }

    //need set balance method for fraud charging accident
    public void setBalance(int balance) {
        this.balance = balance;
    }

    //get and set credit line
    public int getCreditLine() {
        return this.creditLine;
    }

    public void setCreditLine(int creditLine) {
        this.creditLine = creditLine;
    }

    public boolean getValidation() {
        return this.isValidCreditCard;
    }

    public void setValidation(boolean isValid) {
        this.isValidCreditCard = isValid;
    }

    public CreditCardUser getCreditCardUser() {
        return creditCardUser;
    }

    public synchronized int charge(int money) {
        if (this.balance + money <= creditLine)
            this.balance += money; //decline the charge as over credit line
        return this.balance;
    }

    public synchronized int credit(int money) {
        this.balance -= money;
        return this.balance;
    }

    @Override
    public int compareTo(CreditCard o) {
        return this.creditCardUser.getName().compareTo(o.creditCardUser.getName());
    }
}
