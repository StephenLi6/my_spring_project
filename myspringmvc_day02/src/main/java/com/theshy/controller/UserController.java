package com.theshy.controller;

import com.theshy.exception.SysException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2619:34
 * com.theshy.controllerMyDailyProject
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(path = "testString")
    public String testString(Model model){
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(30);
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(ModelAndView mv){
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(30);
        mv.setViewName("success");
        mv.addObject(user);
        return mv;
    }

    @RequestMapping("/testRequestOrForward")
    public String testRequestOrForward(HttpServletRequest request){
        System.out.println("執行了web");
       // return "forward:/WEB-INF/mypage/success.jsp";
        System.out.println(request.getContextPath());
        //return "redirect:/index.jsp";
        return "user/testString";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(HttpServletRequest request) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        List<FileItem> list = fileUpload.parseRequest(request);

        for (FileItem fileItem : list) {
            if (fileItem.isFormField()){
                System.out.println(fileItem.getFieldName() + ":" + fileItem.getString());
            }else {
                String fileName = fileItem.getName();
                String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
                fileName = uuid + "_" +fileName;
                fileItem.write(new File(file,fileName));
                fileItem.delete();
            }
        }
     return "success";
    }

    @RequestMapping("/testFileUploadMVC")
    public String testFileUpload(HttpServletRequest request, @RequestParam("upload") MultipartFile multipartFile) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if (!file.exists()) {
            // 创建目录
            file.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        fileName = uuid + "_" + fileName;
        multipartFile.transferTo(new File(file, fileName));
        return "success";
    }

    /**
     * 模拟异常
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll() throws Exception{
        System.out.println("findAll方法执行了...");
        try {
            // 调用service的方法
            int a = 1/0;
        } catch (Exception e) {
            // 在控制台打印异常的信息
            e.printStackTrace();
            // 准备给用户做提示
            //throw new SysException("查询所有的用户出现错误了...");
            throw new RuntimeException();
        }
        return "success";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
