package kz.bitlab.logistic.controller;

import kz.bitlab.logistic.model.*;
import kz.bitlab.logistic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("requests", requestService.count());
        model.addAttribute("users", userService.count());
        return "index";
    }

    @GetMapping(value = "/addRequest")
    public String addRequest(Model model){
        model.addAttribute("productTypes", productService.getAllType());
        model.addAttribute("transports", transportService.getTransports());
        return "add-request";
    }

    @GetMapping(value = "/signin")
    public String login(){
        return "login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile(Model model){
        model.addAttribute("currentUser", userService.getUser());
        return "profile";
    }

    @GetMapping(value = "/myRequests")
    public String myRequests(Model model){
        model.addAttribute("myRequests", requestService.getAllRequests());
        return "myRequests";
    }

    @GetMapping(value="/signup")
    public String signUp(){
        return "signup";
    }
    @PostMapping(value = "signup")
    public String signUp(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_full_name") String fullname,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "user_re_password") String rePassword,
                         @RequestParam(name = "user_country") String country,
                         @RequestParam(name = "user_company") String company,
                         @RequestParam(name = "user_phone_number") String phoneNumber,
                         @RequestParam(name = "user_address") String address){
        Boolean result = userService.registerUser(email,password,rePassword,fullname,country,phoneNumber,company,address);

        if(result != null){
            if(result.equals(Boolean.TRUE)) {
                return "redirect:/signup?success";
            }else {
                return "redirect:/signup?userError";
            }
        }else {
            return "redirect:/signup?passwordError";
        }
    }

    @GetMapping(value = "/calc")
    public String calc(Model model){
        model.addAttribute("productTypes", productService.getAllType());
        model.addAttribute("transports", transportService.getTransports());
        return "testCalc";
    }

    @PostMapping(value = "updatePassword")
    public String updatePass(@RequestParam(name = "oldPass") String oldPass,
                             @RequestParam(name = "newPass") String newPass,
                             @RequestParam(name = "reNewPass") String reNewPass){

        Boolean result = userService.changePassword(oldPass, newPass, reNewPass);
        if(result != null){
            if(result.equals(Boolean.TRUE)) {
                return "redirect:/profile?success";
            }else {
                return "redirect:/profile?passwordError";
            }
        }else {
            return "redirect:/profile?oldPasswordError";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "admin")
    public String admin(){
        return "admin-panel";
    }

    @GetMapping(value = "news-details")
    public String newsDetails(){
        return "news-details";
    }

    @GetMapping(value = "addNews")
    public String addNews(){
        return "addNews";
    }

 
    @PostMapping(value = "/updatePic")
    public String uploadPic(@RequestParam(name = "avatar")MultipartFile file){
        this.fileUploadService.uploadPic(file);
        return "redirect:/profile";
    }

    @GetMapping(value = "/manageNews")
    public String manageNews(){
        return "manage-news";
    }

    @GetMapping(value = "manageNewsDetails")
    public String newsDetailsManage(){
        return "manage-news-details";
    }
}
