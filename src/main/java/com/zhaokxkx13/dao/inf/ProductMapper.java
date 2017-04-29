package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Mapper
public interface ProductMapper {
    List<Product> selectByName(String name);

    List<Product> selectByNameRaw(String name);

    List<Product> selectAll();

}
