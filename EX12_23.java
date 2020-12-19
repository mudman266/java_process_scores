// Exercise 12.23
// Process scores in a text file
// Josh Williams

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.URL;

public class EX12_23 {

    public static boolean debugging = false;

    public static void main(String[] args) throws ArithmeticException, IllegalArgumentException, InputMismatchException {

        // Gather input from user
        System.out.println("Enter valid URL or file path ending with .dat: ");
        String inputString = new Scanner(System.in).next();
        try {
            // Check for .dat ending
            if (inputString.toUpperCase().endsWith(".DAT")) {
                // Check for http: at beginning
                if (inputString.toUpperCase().startsWith("HTTP:")) {
                    URL url = new URL(inputString);
                    Scanner input = new Scanner(url.openStream());
                    double count = 0;
                    double total = 0;
                    // Loop through the scores, incrementing count and adding to total
                    while (input.hasNext()) {
                        int score = input.nextInt();
                        // Validate score
                        if (score >= 0 && score <= 100) {
                            total += score;
                            count++;
                        } else {
                            // Score outside of bounds
                            throw new ArithmeticException();
                        }
                    }
                    System.out.printf("Total: %.0f\nAverage: %.2f\n", total, total / count);
                } else {
                    // File input
                    java.io.File fileName = new java.io.File(inputString);
                    Scanner file = new Scanner(fileName);
                    double count = 0;
                    double total = 0;
                    // Loop through the scores, incrementing count and adding to total
                    while (file.hasNext()) {
                        String score = file.next();
                        if (Integer.parseInt(score) >= 0 && Integer.parseInt(score) <= 100) {
                            total += Integer.parseInt(score);
                            count++;
                        } else {
                            // Score outside of bounds
                            throw new ArithmeticException();
                        }
                    }
                    System.out.printf("Total: %.0f\nAverage: %.2f\n", total, total / count);
                    file.close();
                }
            } else {
                // Did not in .dat
                throw new InputMismatchException();
            }
        } catch (IOException ex) {
            System.out.println("Filename or URL is invalid.");
            if (debugging) ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            System.out.println("Illegal argument. Scores must be numbers.");
            if (debugging) ex.printStackTrace();
        } catch (ArithmeticException ex) {
            System.out.println("Invalid value detected. Scores must be between 0 - 100.");
            if (debugging) ex.printStackTrace();
        } catch (InputMismatchException ex) {
            System.out.println("Input must end with .dat");
            if (debugging) ex.printStackTrace();
        }
    }
}