package clinic.programming.training;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class Application {

    public int countWords(String words){
        String[] separateWords = StringUtils.split(words, " ");
        return (separateWords == null) ? 0 : separateWords.length;
    }

    public void hello(){
        List<String> list = new ArrayList<String>();
        list.add("Hello");

        for (String s : list) {
            System.out.println("hello bro: " + s);
        }
    }
    
    public Application() {
        System.out.println ("Inside Application");
    }
    public static void main (String[] args) {
    	System.out.println ("Starting Application");
	Application app = new Application();
    app.hello();
    int count = app.countWords("how are you? I am vaibhav");
    System.out.println("Word count: " + count);
    }
}