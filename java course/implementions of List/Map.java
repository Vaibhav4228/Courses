
import java.util.HashMap;

public class Map{
    public static void main (String[] args){
        HashMap<String, Integer> map = new HashMap<>();

        map.put("hello", 1);
        map.put("vaibhav", 2);
        map.put("sharma", 3);

        System.out.println("My name is:  " + map.get("vaibhav"));

        System.out.println(map.containsKey("hello"));

        map.remove("hello");

        for(String key: map.keySet()){
            System.out.println(key + " " + map.get(key));
        }

        map.clear();
        System.out.println("maps is blank"+ map.isEmpty());
        
    }
}