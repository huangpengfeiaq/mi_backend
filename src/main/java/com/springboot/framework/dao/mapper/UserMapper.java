package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("SELECT * FROM user_master WHERE status != -1 AND (user_phone = #{loginKey} OR user_account = #{loginKey}) AND user_password = #{password}")
    User login(@Param("loginKey") String loginKey, @Param("password") String password);

    @Update("UPDATE user_master SET user_password = #{newPassword}, update_by = #{updateBy} WHERE user_id = #{id} AND user_password = #{oldPassword}")
    int updateByPassword(@Param("id") Integer id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword, @Param("updateBy") String updateBy);
}