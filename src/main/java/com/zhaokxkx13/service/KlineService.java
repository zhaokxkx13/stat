package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.Kline;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/6.
 */
public interface KlineService {
    List<Kline> getKlineByYear(Date date);
}
