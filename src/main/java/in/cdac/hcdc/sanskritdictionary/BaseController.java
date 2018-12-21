/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary;
import java.util.Map;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Harshada
 */

@Controller
@EnableAutoConfiguration
public class BaseController {
    
        @RequestMapping("/home")
	public String home() {
            return "index";
	}
        
        @RequestMapping("/msg")
        public String msg(Map<String, Object> model){
            model.put("message", "from cntlr");
            model.put("path", "C:\\Users\\Mithilesh\\Pictures\\basic.jpg");
            return "welcome";
        }
        
        @RequestMapping("/uploaded")
        public String uploaded(Map<String, Object> model){
            System.out.println("file uploded");
            model.put("filePath", "ok");
            return "welcome";
        }

}
