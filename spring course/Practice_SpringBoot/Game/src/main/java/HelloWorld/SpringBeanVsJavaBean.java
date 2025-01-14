package HelloWorld;

import java.io.Serializable;

class POJO{
    private String text;
    private String number;

    private String toString(){
        return text + ":" + number;

    }
}

class JavaBean implements Serializable {
    //EJB
    //No args controcturors hoti hai javaBean mein
    // and need no args contructors

    public JavaBean(){


    }

}
