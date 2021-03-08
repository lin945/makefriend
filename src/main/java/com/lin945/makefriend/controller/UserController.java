package com.lin945.makefriend.controller;
import com.lin945.makefriend.config.CodeConfig;
import com.lin945.makefriend.pojo.ResultBody;
import com.lin945.makefriend.pojo.ao.LoginAo;
import com.lin945.makefriend.pojo.ao.UserRegisterAo;
import com.lin945.makefriend.pojo.ao.UserUpdateAo;
import com.lin945.makefriend.pojo.model.Users;
import com.lin945.makefriend.pojo.vo.UserVO;
import com.lin945.makefriend.service.UsersService;
import com.lin945.makefriend.utils.JwtUtils;
import com.lin945.makefriend.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lin945
 * @date 2021/3/5 19:17
 * @description 用户controller
 */
@Validated
@RestController
@Api(tags = "用户模块",value = "用户相关接口")
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * 注册用户
     *
     * @param users 用户名，密码，邮箱
     * @return 标准返回
     */

    @ApiOperation(value = "注册用户", notes = "通过用户名，密码，邮箱用户信息")
    @PostMapping("/register")
    public ResultBody register(@ApiParam(value = "用户信息", required = true)@RequestBody @Validated UserRegisterAo users) {
        Users usersByUserName = usersService.getUsersByUserName(users.getUsername());
        if (usersByUserName != null) {
            return ResultBody.error(CodeConfig.ERROR_NAME);
        }
        Users users1 = new Users();
        BeanUtils.copyProperties(users, users1);
        boolean b = usersService.registerUser(users1);
        if (b) {
            return ResultBody.ok();
        } else {
            return ResultBody.error().message("注册失败");
        }
    }

    /**
     * 登陆
     *
     * @param loginAo 用户名，密码
     * @return token jwt验证
     */
    @ApiOperation(value = "用户登陆", notes = "通过用户名，密码登陆获取token")
    @PostMapping("/login")
    public ResultBody login(@Validated @RequestBody LoginAo loginAo) {
        Users users = new Users();
        BeanUtils.copyProperties(loginAo, users);
        Users login = usersService.login(users);
        if (login == null) {
            return ResultBody.error(CodeConfig.UserNameOrPassword_Erro);
        } else {
            return ResultBody.ok().data(JwtUtils.getToken(String.valueOf(login.getUid())));
        }
    }

    /**
     * 登出
     *
     * @return 删除redis缓存
     */
    @ApiOperation(value = "注销登陆", notes = "清除token")
    @GetMapping("loginout")
    public ResultBody loginOut() {
        return usersService.loginOut(UserUtils.getUserId()) ? ResultBody.ok() : ResultBody.error();
    }

    /**
     * 更换头像
     *
     * @param icon 上传文件
     * @return 保存图片 并更新数据库字段
     */
    @ApiOperation(value = "用户更换头像", notes = "上传图片")
    @PostMapping("/updateicon")
    public ResultBody updateIcon(@RequestParam("icon") MultipartFile icon) {
        boolean b = usersService.updateIcon(icon, UserUtils.getUserId());
        if (b) {
            return ResultBody.ok();
        }
        return ResultBody.error();
    }

    /**
     * 获取已登录用户信息
     *
     * @return info
     */
    @ApiOperation(value = "获取自己用户信息", notes = "获取详细信息")
    @GetMapping("info")
    public ResultBody userInfo() {
        String userid = UserUtils.getUserId();
        UserVO userInfo = usersService.getUserInfo(userid);
        return ResultBody.ok().data(userInfo);
    }

    /**
     * 信息修改
     *
     * @param updateAo
     * @return
     */
    @ApiOperation(value = "用户修改个人信息", notes = "头像昵称邮箱等用户信息")
    @PostMapping("/updateinfo")
    public ResultBody updateUserInfo(@RequestBody @Validated UserUpdateAo updateAo) {
        return usersService.updateUserInfo(updateAo, UserUtils.getUserId()) ? ResultBody.ok() : ResultBody.error();
    }

    /**
     *获取所有用户
     * @return
     */
    @ApiOperation(value = "获取所有已注册的用户列表", notes = "返回用户list")
    @GetMapping("getAll")
    public ResultBody getAllUser(){
        return ResultBody.ok().data(usersService.getAllUserList());
    }

}
