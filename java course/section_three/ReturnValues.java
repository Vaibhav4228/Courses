public class ReturnValues {
    public ReturnValues() {
    }
 
    public static void main(String[] var0) {
       double var1 = calculateArea(5.5, 10.0);
       double var3 = calculateArea(7.5, 3.5);
       double var5 = calculateArea(2.5, 4.5);
       String var7 = explainArea("English");
    }
 
    public static double calculateArea(double var0, double var2) {
       double var4 = var0 * var2;
       return var4;
    }
 
    public static String explainArea(String var0) {
       switch (var0) {
          case "English":
             return "Area equals length times width.";
          case "Spanish":
             return " helooo this is spanish";
          case "French":
             return "La zone \u00c3\u00a9quivaut \u00c3\u00a0 la longueur fois la largeur.";
          default:
             return "Invalid language.";
       }
    }
 }
 