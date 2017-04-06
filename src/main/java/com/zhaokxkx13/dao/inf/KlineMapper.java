package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Kline;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/6.
 */
@Mapper
public interface KlineMapper {
    List<Kline> selectDateAfter(Date date);
}
