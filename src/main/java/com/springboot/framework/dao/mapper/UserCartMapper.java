package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.pojo.UserCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author huangpengfei
 */
public interface UserCartMapper extends Mapper<UserCart> {
    @Update("UPDATE user_cart SET cart_number = cart_number + #{cartNumber} WHERE cart_id = #{id}")
    int updateByCartNumber(@Param("id") Integer id, @Param("cartNumber") Integer cartNumber);
}