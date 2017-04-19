package test;

import main.CreditCard;
import main.CreditCardCenter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rliu on 4/18/17 10:34 PM.
 */
public class CreditCardCenterTest {
    private static final String USERNAME = "Quincy";
    private static final String ADD = "Add";
    private static final String CREDIT = "Credit";
    private static final String CHARGE = "Charge";
    private static final String VALID_CREDIT_CARD = "5454545454545454";
    private static final String INVALID_CREDIT_CARD = "1234567890123456";
    private static final int CREDIT_LINE = 2000;
    private static final String CURRENCY = "$";

    @Test
    public void testAdd() {
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        creditCardCenter.process(new String[]{ADD, USERNAME, VALID_CREDIT_CARD, CURRENCY + CREDIT_LINE});
        HashMap<String, CreditCard> creditCardUserMap = creditCardCenter.getCreditCardUserMap();
        CreditCard creditCard = creditCardUserMap.get(USERNAME);
        assertNotNull(creditCard);
        assertEquals(creditCard.getCreditCardUser().getName(), USERNAME);
        assertEquals(creditCard.getNumber(), Long.parseLong(VALID_CREDIT_CARD));
        assertEquals(creditCard.getCreditLine(), CREDIT_LINE);
    }

    @Test
    public void testCredit() {
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        creditCardCenter.process(new String[]{ADD, USERNAME, VALID_CREDIT_CARD, CURRENCY + CREDIT_LINE});
        creditCardCenter.process(new String[]{CREDIT, USERNAME, CURRENCY + CREDIT_LINE});
        HashMap<String, CreditCard> creditCardUserMap = creditCardCenter.getCreditCardUserMap();
        CreditCard creditCard = creditCardUserMap.get(USERNAME);
        assertNotNull(creditCard);
        assertEquals(creditCard.getBalance(), 0 - CREDIT_LINE);

    }

    @Test
    public void testChargeWithinCreditLine() {
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        creditCardCenter.process(new String[]{ADD, USERNAME, VALID_CREDIT_CARD, CURRENCY + CREDIT_LINE});
        creditCardCenter.process(new String[]{CHARGE, USERNAME, CURRENCY + (CREDIT_LINE - 100)});
        HashMap<String, CreditCard> creditCardUserMap = creditCardCenter.getCreditCardUserMap();
        CreditCard creditCard = creditCardUserMap.get(USERNAME);
        assertNotNull(creditCard);
        assertEquals(creditCard.getBalance(), CREDIT_LINE - 100);
    }

    @Test
    public void testChargeOverCreditLine() {
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        creditCardCenter.process(new String[]{ADD, USERNAME, VALID_CREDIT_CARD, CURRENCY + CREDIT_LINE});
        creditCardCenter.process(new String[]{CHARGE, USERNAME, CURRENCY + (CREDIT_LINE + 100)});
        HashMap<String, CreditCard> creditCardUserMap = creditCardCenter.getCreditCardUserMap();
        CreditCard creditCard = creditCardUserMap.get(USERNAME);
        assertNotNull(creditCard);
        assertEquals(creditCard.getBalance(), 0);
    }

    @Test
    public void testInvalidCreditCard() {
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        creditCardCenter.process(new String[]{ADD, USERNAME, INVALID_CREDIT_CARD, CURRENCY + CREDIT_LINE});
        HashMap<String, CreditCard> creditCardUserMap = creditCardCenter.getCreditCardUserMap();
        CreditCard creditCard = creditCardUserMap.get(USERNAME);
        assertNotNull(creditCard);
        assertFalse(creditCard.getValidation());
    }

}
