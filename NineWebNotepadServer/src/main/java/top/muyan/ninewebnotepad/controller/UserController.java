package top.muyan.ninewebnotepad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.User;
import top.muyan.ninewebnotepad.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/havePassword",method = RequestMethod.GET)
    public Result<Integer> havePassword(){
        return userService.havePassword();
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<String> register(String password){
        return userService.register(password);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<String> login(String password){
        return userService.login(password);
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public Result<String> changePassword(String oldPassword,String newPassword){
        return userService.changePassword(oldPassword, newPassword);
    }
}
