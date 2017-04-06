package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.dao.entity.Kline;
import com.zhaokxkx13.dao.inf.KlineMapper;
import com.zhaokxkx13.service.KlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/6.
 */
@Service
public class KlineServiceImpl implements KlineService {
    @Autowired
    KlineMapper klineMapper;
    @Override
    public List<Kline> getKlineByYear(Date date) {
        return klineMapper.selectDateAfter(date);
    }
}
