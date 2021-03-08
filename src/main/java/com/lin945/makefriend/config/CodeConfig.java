package com.lin945.makefriend.config;

/**
 * @author lwm
 * @date 2020-09-12 17:05
 * @description 异常返回配置类
 */
public enum CodeConfig {


    ERROR(100, "失败"),
    UserNameOrPassword_Erro(108, "用户名或密码错误"),
    PARAM_ERROR(101, "参数错误"),
    SFM_JYM_NOTNULL(102, "token不能为空"),
    TOKEN_ERROR(103, "token有误"),
    DORMITORY_INFO_EXCEPTION(104, "学生寝室信息异常"),
    PERMISSION_DENIED(105, "权限不足"),
    NO_DORMITORY_INFORMATION(106, "暂无宿舍信息"),
    NOT_YET_TEACHER_TIMETABLE(107, "暂无老师课表"),
    SUCCESS(200, "成功"),

    FUNCTION_NOT_OPEN(400, "功能未开放"),

    SERVER_BUSY(500, "服务器繁忙"),

    ERROR_LOGIN(10003, "无此登陆方式"),

    NO_CHEAK_IN_RECORD(10004, "今日没有打卡"),
    ERROR_LENGTH(10009, "账户位数错误"),
    ERROR_PASSWORD(10010, "密码错误"),
    ERROR_NAME(10011, "暂无此账号信息"),

    ERROR_SERIALIZE(10012,"序列化失败"),
    ERROR_DESERIALIZE(10013,"反序列化失败"),

    /*缓存服务器异常*/
    REDIS_MISS(20000, "缓存服务器异常"),
    ERROR_OPENID(20004, "获取用户openid失败"),
    ERROR_LOST_UPDATE(30000, "修改信息的不存在或已经找到了"),
    ERROR_LOST_DELETE(30006,"删除信息不存在"),
    ERROR_LOST_MISS(30015,"暂无此寻物方式"),
    ERROR_LOST_REPEAT(30020,"已有人捡到了你的卡"),
    ERROR_MAX_FileUpload(50000, "图片上传过大"),
    ERROR_REFUSE_FileUpload(50001, "文件类型不符合");


    public final Integer code;
    public final String message;

    CodeConfig(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
