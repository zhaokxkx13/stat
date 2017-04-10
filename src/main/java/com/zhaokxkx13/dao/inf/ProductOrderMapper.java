package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.ProductOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Mapper
public interface ProductOrderMapper {
    List<ProductOrder> selectById(Integer id);

    List<ProductOrder> selectByDate(Map<String, Date> map);
}
