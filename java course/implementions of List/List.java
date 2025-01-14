public import java.util.ArrayList;

public class List {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("vaibhav");
        list.add("sharma");

        System.out.println(list); 

        list.remove("vaibhav");
        System.out.println(list); 

        System.out.println(list.get(1)); }
}
 
    

