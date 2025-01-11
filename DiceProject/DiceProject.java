import java.util.Scanner;

public class DiceProject {
    public static void main(String[] args) {
        int roll1 = rollDice();
        int roll2 = rollDice();
        rollDice();
        int roll3 = rollDice();

        Scanner sc = new Scanner(System.in);
        System.out.println("enter three number b/w 1 to 6");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        if (lessThan1(num1, num2, num3)) {
            System.out.println("Invalid input. Number must be greater than 1");
        } else if (greaterThan6(num1, num2, num3)) {
            System.out.println("Invalid input. Number must be less than 6");
        } else {
            System.out.println("Dice rolls: ");
            System.out.println(roll1);
            System.out.println(roll2);
            System.out.println(roll3);

            System.out.println("Your numbers: ");
            System.out.println(num1);
            System.out.println(num2);
            System.out.println(num3);

            int comapre = 0;
            if(num1 == roll1) comapre++;
            if(num2 == roll2) comapre++;
            if(num3 == roll3) comapre++;

            System.out.println("Matches " + comapre + "rolls");
        }

       sc.close();
    }
  public static boolean lessThan1(int num1, int num2, int num3) {
    return (num1 < 1 || num2 < 1 || num3 < 1);
       
    }
    public static boolean greaterThan6(int num1, int num2, int num3) {
        return (num1 > 6 || num2 > 6 || num3 > 6);
    }

    
    public static int rollDice() {
        double randomNumber = Math.random()*6;
        randomNumber += 1;
        return (int)randomNumber;
    }
}