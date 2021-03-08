package com.lin945.makefriend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin945.makefriend.dao.UsersMapper;
import com.lin945.makefriend.pojo.ao.LoginAo;
import com.lin945.makefriend.pojo.ao.UserUpdateAo;
import com.lin945.makefriend.pojo.model.Users;
import com.lin945.makefriend.pojo.vo.UserVO;
import com.lin945.makefriend.service.UsersService;
import com.lin945.makefriend.utils.HttpContextUtil;
import com.lin945.makefriend.utils.RedisUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luomingsen
 * @since 2020-11-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Value("${lin.file.imgpath}")
    private String path;

    @Override
    public boolean registerUser(Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", users.getUsername());
        Users users1 = baseMapper.selectOne(queryWrapper);

        if (users1 == null) {
           return baseMapper.insert(users)==1;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateIcon(MultipartFile multipartFile,String userId) {
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            String url=path + originalFilename;
            File file1 = new File(url);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            FileCopyUtils.copy(multipartFile.getBytes(),file1 );
            String path = "/static/img/".concat(originalFilename);
            Users users = baseMapper.selectById(userId);
            users.setIcon(path);
           return baseMapper.updateById(users)==1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Users login(Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", users.getUsername());
        queryWrapper.eq("password", users.getPassword());
        Users users1 = baseMapper.selectOne(queryWrapper);
        if (users1 != null) {
//            HttpContextUtil.request().setAttribute("user", users1);
        }
        return users1;
    }

    @Override
    public String loginAndGetToken(LoginAo ao) {
        Users users = new Users();
        BeanUtils.copyProperties(ao, users);

        return null;
    }

    @Override
    public Users getUsersByUserName(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public UserVO getUserInfo(String uid) {
        Users users = baseMapper.selectById(uid);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(users, vo);
        return vo;
    }

    @Override
    public boolean loginOut(String uid) {
        RedisUtil.del(uid);
        return RedisUtil.get(uid)==null;
    }

    @Override
    public boolean updateUserInfo(UserUpdateAo updateAo,String uid) {
        Users users = new Users();
        BeanUtils.copyProperties(updateAo, users);
        users.setUid(Integer.parseInt(uid));
        return baseMapper.updateById(users)==1;
    }

    @Override
    public List<UserVO> getAllUserList() {
        List<Users> users = baseMapper.selectList(null);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(users);
            List<UserVO> userVOS = objectMapper.readValue(s,List.class);
            return userVOS;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}

