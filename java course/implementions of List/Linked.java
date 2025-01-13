
import java.util.LinkedList;

public class Linked {
   public Linked() {
   }

   public static void main(String[] var0) {
      LinkedList var1 = new LinkedList();
      var1.add("animals");
      var1.add("dogs");
      System.out.println(var1);
      var1.addFirst("men");
      System.out.println(var1);
      var1.removeLast();
      System.out.println(var1);
   }
}
