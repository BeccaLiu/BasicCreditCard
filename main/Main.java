package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        CreditCardCenter creditCardCenter = CreditCardCenter.getInstance();
        if (args.length <= 2) {
            String fileName = args.length == 0 ? args[0] : args[1];
            try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    creditCardCenter.process(line.split(" "));
                }
                creditCardCenter.getSummary();
            }
        } else {
            creditCardCenter.process(args);
            creditCardCenter.getSummary();
        }
    }
}
