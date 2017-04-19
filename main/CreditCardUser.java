package main;

/**
 * Created by rliu on 4/18/17.
 */
public class CreditCardUser {
    private String name;
//    private CreditCard creditCard;

    CreditCardUser(String name) {
        this.name = name;
    }

    //    CreditCardUser(String name, CreditCard creditCard) {
//        this.name = name;
//        this.creditCard = creditCard;
//    }
//
    public String getName() {
        return this.name;
    }
//
//    public CreditCard getCreditCard() {
//        return creditCard;
//    }
//
//    public void setCreditCard(CreditCard creditCardt) {
//        this.creditCard = creditCardt;
//    }
//
//    public int hashCode() {
//        int valid = creditCard.getValidation() ? 1 : 0;
//        int result = (int) creditCard.getNumber() % 10 * name.length() + valid;
//        return result;
//    }

}
