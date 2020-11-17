package com.Obee;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static final byte MONTHS_IN_YEAR = 12;
    static final byte PERCENT = 100;

    public static void main(String[] args) {
        //Taking in principal amount
        double principal = readNumber("Principal:", 1000, 1000000);

        //Taking in interest rate and converting it to a double
        double monthlyInterestRate = readNumber("Annual Interest Rate: ", 1, 30) / PERCENT / MONTHS_IN_YEAR;

        //Taking in total period in years
        int totalPayments = (int)readNumber("Period (Years): ", 1, 30) * MONTHS_IN_YEAR;

        //Calculating mortgage payment
        String monthlyPayment = calculateMortgagePayments(principal,monthlyInterestRate,totalPayments);
        System.out.println("MORTGAGE\n--------");
        System.out.println("Monthly Payments: " + monthlyPayment);
        displayPaymentSchedule(principal, monthlyInterestRate, totalPayments);
    }

    public static double readNumber(String prompt, double min, double max){
        double number = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print(prompt);
            number = scanner.nextDouble();
            if(number >= min && number <= max){
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return number;
    }

    public static String calculateMortgagePayments(double principal, double monthlyInterestRate, int totalPayments){
        double mortgagePayment = principal * ((monthlyInterestRate * Math.pow((1 + monthlyInterestRate), totalPayments)) /
                ( Math.pow((1 + monthlyInterestRate), totalPayments) - 1 ));
        return NumberFormat.getCurrencyInstance().format(mortgagePayment);
    }

    public static void displayPaymentSchedule(double principal, double monthlyInterestRate, int totalPayments){
        System.out.println("Payment Schedule\n----------------");
        double currentBalance = 0;
        for (int i = 1; i <= totalPayments; i++) {
            currentBalance = principal * ((Math.pow((1 + monthlyInterestRate), totalPayments) - (Math.pow((1 + monthlyInterestRate),i)))/((Math.pow((1 + monthlyInterestRate),totalPayments) - 1)));
            System.out.println(NumberFormat.getCurrencyInstance().format(currentBalance));
        }
    }
}
