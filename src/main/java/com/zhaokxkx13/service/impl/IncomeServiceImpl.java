package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.inf.IncomeMapper;
import com.zhaokxkx13.service.IncomeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
@Service
public class IncomeServiceImpl implements IncomeService, InitializingBean {
    @Autowired
    IncomeMapper incomeMapper;

    private Double indusAll;
    private Double indusSellAll;
    private Double incomeAll;
    private Double mainServiceIncome;
    private Double techIncome;
    private Double productSellIncome;
    private Double htProductSellIncome;
    private Double goodsSellIncome;


    @Override
    public void afterPropertiesSet() throws Exception {


    }

    public Income getAllIncome() {
        Income income = new Income();
        income.setType("all");

        List<Income> incomeList = incomeMapper.selectAllIncome();
        income = getSum(incomeList);
        return income;
    }

    @Override
    public Income getCurSumMonthIncome(int month, int year) {
        List<Income> incomeList = getCurMonthIncome(month, year);
        return getSum(incomeList);
    }

    @Override
    public List<Income> getCurMonthIncome(int month, int year) {
        List<Income> incomeList = incomeMapper.selectAllIncome();
        List<Income> result = new ArrayList<>();
        for (Income item : incomeList) {
            Date date = item.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.MONTH) == month && calendar.get(Calendar.YEAR) == year)
                result.add(item);
        }

        return result;
    }

    public Income compareMonth(Income cur, Income pre) {
        Income income = new Income();
        income.setTechIncome(cur.getTechIncome() - pre.getTechIncome());
        income.setProductSellIncome(cur.getProductSellIncome() - pre.getProductSellIncome());
        income.setMainServiceIncome(cur.getMainServiceIncome() - pre.getMainServiceIncome());
        income.setIndusSellAll(cur.getIndusSellAll() - pre.getIndusSellAll());
        income.setGoodsSellIncome(cur.getGoodsSellIncome() - pre.getGoodsSellIncome());
        income.setHtProductSellIncome(cur.getHtProductSellIncome() - pre.getHtProductSellIncome());
        income.setIncomeAll(cur.getIncomeAll() - pre.getIncomeAll());
        income.setIndusAll(cur.getIndusAll() - pre.getIndusAll());
        income.setType(cur.getType());
        return income;
    }

    private Income getSum(List<Income> incomeList) {
        indusAll = 0.0;
        indusSellAll = 0.0;
        incomeAll = 0.0;
        mainServiceIncome = 0.0;
        techIncome = 0.0;
        productSellIncome = 0.0;
        htProductSellIncome = 0.0;
        goodsSellIncome = 0.0;

        Income income = new Income();
        income.setType("all");
        income.setDate(new Date());
        if (incomeList != null && incomeList.size() > 0)
            for (Income item : incomeList) {
                this.indusAll += item.getIndusAll();
                this.incomeAll += item.getIncomeAll();
                this.indusSellAll += item.getIndusSellAll();
                this.mainServiceIncome += item.getMainServiceIncome();
                this.goodsSellIncome += item.getGoodsSellIncome();
                this.htProductSellIncome += item.getHtProductSellIncome();
                this.productSellIncome += item.getProductSellIncome();
                this.techIncome += item.getTechIncome();
            }
        income.setIndusAll(this.indusAll);
        income.setGoodsSellIncome(this.goodsSellIncome);
        income.setHtProductSellIncome(this.htProductSellIncome);
        income.setIncomeAll(this.incomeAll);
        income.setIndusSellAll(this.indusSellAll);
        income.setMainServiceIncome(this.mainServiceIncome);
        income.setProductSellIncome(this.productSellIncome);
        income.setTechIncome(this.techIncome);

        return income;
    }
}
