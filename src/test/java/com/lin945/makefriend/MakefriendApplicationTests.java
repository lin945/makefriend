package com.lin945.makefriend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin945.makefriend.dao.UsersMapper;
import com.lin945.makefriend.pojo.model.Users;
import com.lin945.makefriend.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MakefriendApplicationTests {
    @Autowired
    private UsersMapper usersMapper;

    //redis测试
    @Test
    void Redistest() {
        RedisUtil.set("test", "test", 1, TimeUnit.MINUTES);
        System.out.println(RedisUtil.get("1"));
    }

    @Test
    void UserTest() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "root");
        System.out.println(usersMapper.selectOne(queryWrapper));
    }
}
