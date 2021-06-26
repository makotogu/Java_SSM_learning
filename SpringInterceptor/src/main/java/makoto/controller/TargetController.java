package makoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TargetController {

    @RequestMapping(value = "/target")
    public ModelAndView show() {
        System.out.println("目标被执行......");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","makoto");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
