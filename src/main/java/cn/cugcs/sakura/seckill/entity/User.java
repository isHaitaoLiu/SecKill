package cn.cugcs.sakura.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 秒杀用户表
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号,用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号码
     */
    private Long phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * MD5(MD5(password明文+固定salt) + salt)
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 头像，云存储的id
     */
    private String head;

    /**
     * 注册时间
     */
    private LocalDateTime registerDate;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;


}
