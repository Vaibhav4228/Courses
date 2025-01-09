
import java.util.Scanner;

public class StringConcepts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("may i know your name ");
        String name = sc.nextLine();

        String trim = name.trim();
        System.out.println("trim namesse" + trim);

        String capital = name.toUpperCase();
        System.out.println("capital namesse" + capital);

        String lower = name.toLowerCase();
        System.out.println("lower namesse" + lower);

        boolean found = trim.toUpperCase().contains("va");
        System.out.println("found" + found);


        String formate = String.format("the final result is: ", name);
       System.out.println(formate);
    }
}
