package top.muyan.ninewebnotepad.mapper;

import org.apache.ibatis.annotations.Param;
import top.muyan.ninewebnotepad.model.db.User;

import java.util.List;

public interface UserMapper {
    public List<User> list();
    public List<User> listByPassword(@Param("password")String password);
    public void add(@Param("user")User user);
    public void update(@Param("user")User user);
}
