package com.lin945.makefriend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin945.makefriend.pojo.ao.LoginAo;
import com.lin945.makefriend.pojo.ao.UserUpdateAo;
import com.lin945.makefriend.pojo.model.Users;
import com.lin945.makefriend.pojo.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luomingsen
 * @since 2020-11-12
 */
public interface UsersService extends IService<Users> {
    boolean registerUser(Users users);
    boolean updateIcon(MultipartFile multipartFile,String userId);
    Users login(Users users);

    String loginAndGetToken(LoginAo ao);
    Users getUsersByUserName(String username);
    UserVO getUserInfo(String uid);

    boolean loginOut(String uid);

    boolean updateUserInfo(UserUpdateAo updateAo,String uid);

    List<UserVO> getAllUserList();
}
