package top.muyan.ninewebnotepad.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.muyan.ninewebnotepad.mapper.UserMapper;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.User;

import java.util.List;

@Component
public class TokenUtils {
    @Autowired
    private UserMapper userMapper;

    public void verifyToken(String token){
        List<User> userList = userMapper.list();
        if(userList.size()==0){
            throw new MyException(Result.CODE_TOKEN_ERROR,"登录验证失败，请重新登录");
        }
        User user = userList.get(0);
        if(!token.equals(user.getToken())){
            throw new MyException(Result.CODE_TOKEN_ERROR,"登录验证失败，请重新登录");
        }
    }
}
