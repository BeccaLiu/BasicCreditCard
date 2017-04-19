package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by rliu on 4/18/17.
 */
public class CreditCardCenter {
    //HashSet<CreditCardUser> creditCardUserSet;
    private static CreditCardCenter instance = null;
    private HashMap<String, CreditCard> creditCardUserMap;

    private CreditCardCenter() {
        creditCardUserMap = new HashMap<>();
    }

    public static synchronized CreditCardCenter getInstance() {
        if (instance == null)
            instance = new CreditCardCenter();
        return instance;
    }

    public void process(String[] content) {
        String processType = content[0];
        String userName = content[1];
        switch (processType) {
            case "Add":
                String creditCard = content[2];
                String creditLine = content[3].substring(1);
                add(userName, Long.parseLong(creditCard), Integer.parseInt(creditLine));
                break;
            case "Charge": {
                String money = content[2].substring(1);
                charge(userName, Integer.parseInt(money));
                break;
            }
            case "Credit": {
                String money = content[2].substring(1);
                credit(userName, Integer.parseInt(money));
                break;
            }
        }
    }

    public HashMap<String, CreditCard> getCreditCardUserMap() {
        return creditCardUserMap;
    }

    public void add(String holderName, long cardNumber, int creditLine) {
        CreditCardUser user = new CreditCardUser(holderName);
        CreditCard creditCard = new CreditCard(cardNumber, user, creditLine);
        creditCard.setValidation(luhnTenCreditCardValidation(Long.toString(cardNumber)));

        creditCardUserMap.put(holderName, creditCard);
    }

    public void charge(String holderName, int money) {
        CreditCard creditCard = creditCardUserMap.get(holderName);
        if (creditCard != null) {
            creditCard.charge(money);
        }
    }

    public void credit(String holderName, int money) {
        CreditCard creditCard = creditCardUserMap.get(holderName);
        if (creditCard != null) {
            creditCard.credit(money);
        }
    }

    public void getSummary() {
        ArrayList<CreditCard> userRank = new ArrayList<>();
        userRank.addAll(creditCardUserMap.values());
        Collections.sort(userRank);

        for (CreditCard cu : userRank) {
            System.out.print(cu.getCreditCardUser().getName() + ": ");
            if (cu.getValidation())
                System.out.print("$" + cu.getBalance());
            else
                System.out.print("error");

            System.out.println();
        }
    }

    public boolean luhnTenCreditCardValidation(String cardNumber) {
        int sum = 0;
        boolean even = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = cardNumber.charAt(i) - '0';
            if (even) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            even = !even;
        }
        return sum % 10 == 0;
    }
}
