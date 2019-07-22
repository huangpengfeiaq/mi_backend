package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.pojo.UserCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author huangpengfei
 */
public interface UserCartMapper extends Mapper<UserCart> {
    /**
     * 数量加
     *
     * @param id         cart_id
     * @param cartNumber cart_number
     * @param updateBy   更新人
     * @return 更新记录数
     */
    @Update("UPDATE user_cart SET cart_number = cart_number + #{cartNumber}, update_by = #{updateBy} WHERE cart_id = #{id}")
    int updateByCartNumber(@Param("id") Integer id, @Param("cartNumber") Integer cartNumber, @Param("updateBy") String updateBy);

    /**
     * 数量减一
     *
     * @param id       cart_id
     * @param updateBy 更新人
     * @return 更新记录数
     */
    @Update("UPDATE user_cart SET cart_number = cart_number - 1, update_by = #{updateBy} WHERE cart_id = #{id}")
    int updateBySession(@Param("id") Integer id, @Param("updateBy") String updateBy);
}