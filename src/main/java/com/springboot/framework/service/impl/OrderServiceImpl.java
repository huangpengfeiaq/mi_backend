package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.OrderMapper;
import com.springboot.framework.dao.pojo.Order;
import com.springboot.framework.dto.OrderDTO;
import com.springboot.framework.service.BaseService;
import com.springboot.framework.service.OrderService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:24
 */
@Service
public class OrderServiceImpl implements BaseService<Order, OrderDTO> {
    @Resource
    private OrderMapper orderMapper;
    
    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(OrderDTO orderDTO) {
        //2.创建entity
        Order record = new Order(orderDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (orderMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(OrderDTO orderDTO) {
        //1.请求校验
//        Errors errors = validRequest(orderDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        Order record = new Order(orderDTO);
        //3.响应校验
        if (orderMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Order> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(orderMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<Order> adminList = orderMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        Order record = new Order();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(orderMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(OrderDTO orderDTO) {
        //1.请求校验
//        Errors errors = validRequest(orderDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        Order admin = new Order(orderDTO);
        //3.响应校验
        if (orderMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
