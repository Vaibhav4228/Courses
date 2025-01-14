// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      String var2 = promptTitle(var1);
      String var3 = promptForPublisher(var1);
      int var4 = promptForIssueNumber(var1);
      int var5 = promptForPublicationYear(var1);
      Magazine var6 = new Magazine(var2, var3, var4, var5);
      System.out.println("\nMagazine Details:");
      System.out.println("Title: " + var6.getTitle());
      System.out.println("Publisher: " + var6.getPublisher());
      System.out.println("Issue Number: " + var6.getIssueNumber());
      System.out.println("Publication Year: " + var6.getPublicationYear());
   }

   public static boolean isNull(String var0) {
      return var0 == null || var0.isBlank();
   }

   public static boolean incorrectIssueNumber(int var0) {
      return var0 <= 0;
   }

   public static boolean incorrectPublicationYear(int var0) {
      return var0 <= 0;
   }

   public static String promptTitle(Scanner var0) {
      while(true) {
         System.out.print("Enter the title: ");
         String var1 = var0.nextLine();
         if (!isNull(var1)) {
            return var1;
         }

         System.out.println("Title cannot be null or blank.");
      }
   }

   public static String promptForPublisher(Scanner var0) {
      while(true) {
         System.out.print("Enter the publisher: ");
         String var1 = var0.nextLine();
         if (!isNull(var1)) {
            return var1;
         }

         System.out.println("Publisher cannot be null or blank.");
      }
   }

   public static int promptForIssueNumber(Scanner var0) {
      while(true) {
         System.out.print("Enter the issue number: ");
         if (var0.hasNextInt()) {
            int var1 = var0.nextInt();
            var0.nextLine();
            if (!incorrectIssueNumber(var1)) {
               return var1;
            }
         } else {
            var0.nextLine();
         }

         System.out.println("Issue number must be greater than 0.");
      }
   }

   public static int promptForPublicationYear(Scanner var0) {
      while(true) {
         System.out.print("Enter the publication year: ");
         if (var0.hasNextInt()) {
            int var1 = var0.nextInt();
            var0.nextLine();
            if (!incorrectPublicationYear(var1)) {
               return var1;
            }
         } else {
            var0.nextLine();
         }

         System.out.println("Publication year must be greater than 0.");
      }
   }
}