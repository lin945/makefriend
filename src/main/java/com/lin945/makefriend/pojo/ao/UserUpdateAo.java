package com.lin945.makefriend.pojo.ao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lin945
 * @since 2020-11-12
 */
@Data
@AllArgsConstructor
@ApiModel(description = "用户更新")
public class UserUpdateAo implements Serializable {
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 信息
     */
    @NotBlank(message = "信息不能为空")
    @ApiModelProperty(value = "信息")
    private String info;

    @NotBlank(message = "手机不能为空")
    @ApiModelProperty(value = "手机")
    private String phone;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;
}
