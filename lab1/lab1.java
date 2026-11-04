import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Problem 1

        int number = 23;

        if (number > 0) {
            System.out.println("Positive");
        }
        else if (number < 0) {
            System.out.println("Negative");
        }
        else {
            System.out.println("Zero");
        }

        if (number % 2 == 0) {
            System.out.println("Even");
        }
        else {
            System.out.println("Odd");
        }

        if (number % 3 == 0) {
            System.out.println("Divisible by 3");
        }
        else {
            System.out.println("Not divisible by 3");
        }

        if (number % 5 == 0) {
            System.out.println("Divisible by 5");
        }
        else {
            System.out.println("Not divisible by 5");
        }
        
        if (number % 3 == 0 && number % 5 == 0) {
            System.out.println("Divisible by both 3 and 5");
        }

        //Problem 2

        int a = 10;
        int b = 30;

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = a; i <= b; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }

        //Problem 3

        int c = 5;

        for (int i = 1; i <= c; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print((i*j) + " ");
            }
            System.out.println();
        }

        //Problem 4

        int k = 5;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }

        //Problem 5

        int rows = 4;

        int columns = 6;

        int counter = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                counter++;
                if (isPrime(counter)) {
                    System.out.print("P ");
                }
                else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        //Problem 6

        int rows_2 = 2;

        int columns_2 = 4;

        for (int i = 1; i <= rows_2; i++) {
            System.err.println("Row: " + i);
            System.out.print("Enter a number: ");
            int max = input.nextInt();

            for (int j = 2; j <= columns_2; j++) {
                System.out.print("Enter a number: ");
                int number_2 = input.nextInt();
                
                if (number_2 > max) {
                    max = number_2;
                }
            }
            System.out.println("Row " + i + " maximum is: " + max);
        }
        input.close();

        //Problem 7

        int d = 578923;

        int sum = digitSum(d);

        System.out.println("Digit sum: " + sum);
    }

    static boolean isPrime(int n) {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i*i <= n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }

    static int digitSum(int n) {
        if (n < 10) {
            return n;
        }
            
        int last_digit = n % 10;

        int digits_rest = n / 10;

        return last_digit + digitSum(digits_rest);
    }
}