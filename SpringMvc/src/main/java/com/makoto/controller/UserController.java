package com.makoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoto.domain.User;
import com.makoto.domain.VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/user",method = RequestMethod.GET)
public class UserController {

    @RequestMapping(value = "/quick24",method = RequestMethod.POST)
    public String save24(HttpServletRequest request, String username) {
        request.setAttribute("username",username);
        return "success";
    }

    @RequestMapping(value = "/quick23",method = RequestMethod.POST)
    @ResponseBody
    public void save23(String username, MultipartFile[] uploadFile) throws Exception {
        System.out.println(username);
        //System.out.println(uploadFile);
        //获得上传文件的名称
        for (MultipartFile multipartFile : uploadFile) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("D:\\Java_SSM\\Spring\\SpringMvc\\upload\\"+originalFilename));
        }
    }

    @RequestMapping(value = "/quick22",method = RequestMethod.POST)
    @ResponseBody
    public void save22(String username, MultipartFile uploadFile, MultipartFile uploadFile2) throws Exception {
        System.out.println(username);
        //System.out.println(uploadFile);
        //获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\Java_SSM\\Spring\\SpringMvc\\upload\\"+originalFilename));
        String originalFilename2 = uploadFile2.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\Java_SSM\\Spring\\SpringMvc\\upload\\"+originalFilename2));
    }

    @RequestMapping(value = "/quick21")
    @ResponseBody
    public void save21(@CookieValue(value = "JSESSIONID",required = false) String jsessionId) {
        System.out.println(jsessionId);
    }

    @RequestMapping(value = "/quick20")
    @ResponseBody
    public void save20(@RequestHeader(value = "User-Agent", required = false) String user_agent) {
        System.out.println(user_agent);
    }

    @RequestMapping(value = "/quick19")
    @ResponseBody
    public void save19(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }

    @RequestMapping(value = "/quick18")
    @ResponseBody
    public void save18(Date date) {
        System.out.println(date);
    }

    //localhost/user/quick17/zhangsan
    @RequestMapping(value = "/quick17/{username}",method = RequestMethod.GET)
    @ResponseBody
    public void save17(@PathVariable(value = "username") String username) {
        System.out.println(username);
    }

    @RequestMapping("/quick16")
    @ResponseBody
    public void save16(@RequestParam(value = "name") String username) throws IOException {
        System.out.println(username);
    }

    @RequestMapping("/quick15")
    @ResponseBody
    public void save15(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }

    @RequestMapping("/quick14")
    @ResponseBody
    public void save14(VO vo) throws IOException {
        System.out.println(vo);
    }

    @RequestMapping("/quick13")
    @ResponseBody
    public void save13(String[] strs) throws IOException {
        System.out.println(Arrays.asList(strs));
    }

    @RequestMapping("/quick12")
    @ResponseBody
    public void save12(User user) throws IOException {
        System.out.println(user);
    }

    @RequestMapping("/quick11")
    @ResponseBody
    public void save11(String username, Integer age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping(value = "/quick10")
    @ResponseBody
    //期望SpringMVC自动将User转成json字符串
    public User save10() throws IOException {
        User user = new User();
        user.setAge(32);
        user.setName("lisi2");
        return user;
    }

    @RequestMapping(value = "/quick9")
    @ResponseBody
    public String save9() throws IOException {
        User user = new User();
        user.setAge(30);
        user.setName("lisi");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        return json;
    }

    @RequestMapping(value = "/quick8")
    @ResponseBody
    public String save8() throws IOException {
        return "{\"username\":\"zhangsan\",\"age\":18}";
    }

    @RequestMapping(value = "/quick7")
    @ResponseBody//告知springMVC框架 不进行视图跳转 直接进行数据响应
    public String save7() throws IOException {
        return  "hello makoto";
    }

    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello gu");
    }

    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest request) {
        request.setAttribute("username","顾");
        return "success";
    }

    @RequestMapping(value = "/quick4",method = RequestMethod.GET)
    public String save4(Model model) {
        model.addAttribute("username","makotogu");
        return "success";
    }

    @RequestMapping(value = "/quick3",method = RequestMethod.GET)
    public ModelAndView save3(ModelAndView modelAndView) {
        modelAndView.addObject("username","gu");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick2",method = RequestMethod.GET)
    public ModelAndView save2() {
        /**
         * Model:模型 作用封装数据
         * View: 视图 作用于展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username","makoto");
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @RequestMapping(value = "/quick",method = RequestMethod.GET)
    public String save() {
        System.out.println("Controller save running");
        return "success";
    }
}

