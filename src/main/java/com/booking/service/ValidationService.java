package com.booking.service;

import java.util.Scanner;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan

    public static String validationOfAlphabet(String question, String errorMessage, String regex){
        Scanner input = new Scanner(System.in);
        
        String result = "";
        boolean isLooping = true;

        do {
            System.out.println(question);
            result = input.nextLine();

            if (result.matches(regex)) {
                isLooping = false;

            } else {
                System.out.println(errorMessage);
            }
            
        } while (isLooping);
        
        return result;
    }

    public static int validationOfNumberWithMin(String question, String errorMessage, String regex, int minimum){  
        int result = 0;
        boolean isLooping = true;

        do {
            isLooping = true;
            result = Integer.valueOf(validationOfAlphabet(question, errorMessage, regex));

            if (result >= minimum) {
                isLooping = false;

            } else {
                System.out.println("Angka harus minimal : " + minimum);
            }
            
        } while (isLooping);
        
        return result;
    }

    public static String validationOfID(String question, String errorMessage, String regex){
        Scanner input = new Scanner(System.in);
        String result;
        boolean isLooping = true;

        do {
          System.out.println(question);  
          result = input.nextLine();

          if (result.matches(regex)) {
            isLooping = false;
          } else {
            System.out.println(errorMessage);
          }
        } while (isLooping);

        return result;
    }
}
