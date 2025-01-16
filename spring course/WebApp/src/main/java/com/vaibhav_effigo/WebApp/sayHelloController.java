package com.vaibhav_effigo.WebApp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class sayHelloController {

    @RequestMapping("say-hi")
    @ResponseBody
    public String sayHello(){
        return "hello.....";
    }
    @RequestMapping("say-hi-html")
    @ResponseBody
    public String sayHelloGTML(){
        StringBuffer sb = new StringBuffer();
          sb.append("<html>");
        sb.append("<title>HTML WEB PAGE</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("HTML page for body");
        sb.append("</body>");
        sb.append("</html>");


        return sb.toString();
    }
    @RequestMapping("/say-hello-jsp")
    @ResponseBody
    public String sayHiiJSP(){
        return "v";
    }

}
