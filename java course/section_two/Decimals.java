import java.math.BigDecimal;

public class Decimals {
    // public static void main(String[] args) {
    //     float decimal = 3.14f;
    //     System.out.println(decimal);
    // }

    // public static void main(String[] args) {
    //     double decimal = 3.14;
    //     System.out.println(decimal);
    // }

    public static void main(String[] args) {
        BigDecimal v1 = new BigDecimal("0.550");
        BigDecimal v2 = new BigDecimal("0.110");
        BigDecimal ans = v1.add(v2);
        BigDecimal ans2 = v1.max(v2);
        System.out.println(ans2);
    }

}

