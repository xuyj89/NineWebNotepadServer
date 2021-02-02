package top.muyan.ninewebnotepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.muyan.ninewebnotepad.mapper.UserMapper;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.User;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Result<Integer> havePassword() {
        Result<Integer> result = new Result<>();
        result.setData(0);
        List<User> userList = userMapper.list();
        if (userList.size() > 0) {
            result.setData(1);
        }
        return result;
    }

    public Result<String> register(String password){
        Result<String> result = new Result<>();
        List<User> userList = userMapper.list();
        if(userList.size()>0){
            throw new MyException("此系统已注册密码，不可重复注册");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setPassword(password);
        user.setToken(UUID.randomUUID().toString());
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.add(user);

        return result;
    }

    public Result<String> login(String password){
        Result<String> result = new Result<>();
        List<User> userList = userMapper.listByPassword(password);
        if(userList.size()==0){
            throw new MyException("未注册或密码错误，请重试");
        }
        User user = userList.get(0);
        user.setToken(UUID.randomUUID().toString());
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.update(user);
        result.setData(user.getToken());
        return result;
    }

    public Result<String> changePassword(String oldPassword,String newPassword){
        Result<String> result = new Result<>();
        List<User> userList = userMapper.listByPassword(oldPassword);
        if(userList.size()>0){
            throw new MyException("未注册或原密码错误，请重试");
        }
        User user = userList.get(0);
        user.setPassword(newPassword);
        user.setToken(UUID.randomUUID().toString());
        user.setUpdateTime(System.currentTimeMillis());

        userMapper.update(user);

        return result;
    }
}
