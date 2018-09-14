package cap.controller;


import cap.entity.Admin;
import cap.service.AdminService;
import cap.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wolftrace
 * @version 1.0.0
 * @createTime 2018-09-13 9:56
 */


@Controller
@RequestMapping
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ModelAttribute
    public Admin getById(Integer id){
        if (id !=null ){
            return adminService.getById(id);
        }
        return new Admin();
    }

//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Admin login(@RequestBody Admin admin, HttpServletRequest request) {
        Admin admin1 = adminService.login(admin);
        if (admin1 != null){
            request.getSession().setAttribute("user",admin);
            return admin1;
        }
        return null;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo) {
        PageBean pageBean = adminService.getByPage(pageNo, 5);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageBean", pageBean);
        modelAndView.setViewName("list");
        return modelAndView;

    }
    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String del(String id){
        if (!id.isEmpty()){
            Integer id1 = Integer.parseInt(id);
            Integer delete = adminService.delete(id1);
            if (delete>0){
                return "delete"+id +"successful";
            }
        }
        return "delete failed";
    }

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(){
        return "edit";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(RedirectAttributes redirectAttributes, Admin admin){
        Integer integer = adminService.update(admin);
        if (integer>0){
            redirectAttributes.addFlashAttribute("msg","更新数据id="+admin.getId()+"成功了");
        }
        redirectAttributes.addFlashAttribute("msg","更新数据失败了");

        return "redirect:list";

    }


    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(){
        return "add";
    }


    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public String insert(Admin admin,RedirectAttributes redirectAttributes){
        Integer insert = adminService.insert(admin);
        if (insert>0){
            redirectAttributes.addFlashAttribute("msg","添加数据成功了,点击后跳转到首页");
        }else{
            redirectAttributes.addFlashAttribute("msg","添加数据失败了");

        }
        return "redirect:list";
    }
}
