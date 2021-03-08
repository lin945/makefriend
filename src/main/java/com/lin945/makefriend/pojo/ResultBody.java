package com.lin945.makefriend.pojo;
import com.lin945.makefriend.config.CodeConfig;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;
/**
 * @author lwm
 * @date 2020-09-16 19:20
 * @description 统一返回类
 */
@Data
public class ResultBody {

    // 是否成功
    private Boolean success;

    // 返回码
    private Integer code;

    // 返回信息
    private String message;

    // 返回数据
    private Object data;

    // 附带说明
    private Map<String, String> expound = new HashMap<String, String>();

    private ResultBody(){}

    /**
     * 请求成功
     * @return ResultBody
     */
    public static ResultBody ok(){
        ResultBody r = new ResultBody();
        r.setSuccess(true);
        r.setCode(CodeConfig.SUCCESS.code);
        r.setMessage(CodeConfig.SUCCESS.message);
        return r;
    }

    /**
     * 请求失败
     * @return ResultBody
     */
    public static ResultBody error(){
        ResultBody r = new ResultBody();
        r.setSuccess(false);
        r.setCode(CodeConfig.ERROR.code);
        r.setMessage(CodeConfig.ERROR.message);
        return r;
    }

    /**
     * 请求失败
     * @return ResultBody
     */
    public static ResultBody error(CodeConfig code){
        ResultBody r = new ResultBody();
        r.setSuccess(false);
        r.setCode(CodeConfig.ERROR.code);
        r.setMessage(code.message);
        return r;
    }

    /**
     * 设置返回信息
     * @param message
     * @return
     */
    public ResultBody message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置返回code
     * @param code
     * @return
     */
    public ResultBody code(Integer code){
        this.setCode(code);
        return this;
    }



    /**
     * 设置返回数据
     * <br/> 基本引用类型、列表类型都不能使用该方法 <br/> 对map集合、对象兼容 <br/> <strong>使用时考虑清楚</strong>
     * @param data
     * @return
     */
    public ResultBody data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 追加说明信息
     * @param field 追加信息字段名
     * @param info 追加信息内容
     * @return
     */
    public ResultBody info(String field, String info) {
        this.expound.put(field, info);
        return this;
    }

    /**
     * 追加说明信息
     * @param expound 追加信息
     * @return
     */
    public ResultBody info(Map<String, String> expound) {
        this.setExpound(expound);
        return this;
    }

}
