package makoto.controller;

import makoto.domain.Role;
import makoto.domain.User;
import makoto.service.RoleService;
import makoto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list")
    public ModelAndView list() {
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userList",userList);
        return modelAndView;
    }

    @RequestMapping(value = "/saveUI")
    public ModelAndView saveUI() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public String AddUser(User user, Long[] roleIds) {
        userService.addUser(user,roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long userId) {
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/login")
    public String login(String username, String password, HttpSession session) {
        User user = userService.login(username,password);
        if (user != null) {
            session.setAttribute("user",user);
            return "redirect:/user/list";
        }
        return "redirect:/login.jsp";
    }
}
