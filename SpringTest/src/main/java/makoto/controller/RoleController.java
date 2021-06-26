package makoto.controller;

import makoto.domain.Role;
import makoto.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查看所有角色的列表
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        //设置模型
        modelAndView.addObject("roleList",roleList);
        //设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加新的角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String addRole(Role role) {
        roleService.addRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer roleId) {
        roleService.deleteRole(roleId);
        return "redirect:/role/list";
    }
}
