package top.muyan.ninewebnotepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.muyan.ninewebnotepad.mapper.UserMapper;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.User;

import java.util.List;

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

    public Result<User> getUser() {
        Result<User> result = new Result<>();
        List<User> userList = userMapper.list();
        if (userList.size() > 0) {
            result.setData(userList.get(0));
        }
        return result;
    }
}
