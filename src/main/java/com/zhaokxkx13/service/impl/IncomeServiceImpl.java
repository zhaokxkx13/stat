package com.zhaokxkx13.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.inf.IncomeMapper;
import com.zhaokxkx13.dao.inf.ProductMapper;
import com.zhaokxkx13.service.IncomeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
@Service
public class IncomeServiceImpl implements IncomeService, InitializingBean {
    @Autowired
    IncomeMapper incomeMapper;

    @Autowired
    ProductMapper productMapper;

    private Double indusAll;
    private Double indusSellAll;
    private Double incomeAll;
    private Double mainServiceIncome;
    private Double techIncome;
    private Double productSellIncome;
    private Double htProductSellIncome;
    private Double goodsSellIncome;
    private DateFormat dateFormat;

    @Override
    public void afterPropertiesSet() throws Exception {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
            if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year)
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

    @Override
    public List<Income> getCurYearIncome(int year) {
        List<Income> incomeList = incomeMapper.selectAllIncome();
        List<Income> result = new ArrayList<>();
        for (Income item : incomeList) {
            Date date = item.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.YEAR) == year) {
                item.setDateStr(dateFormat.format(item.getDate()));
                result.add(item);
            }
        }
        result.sort(new Comparator<Income>() {
            @Override
            public int compare(Income o1, Income o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return result;
    }

    @Override
    public List<Income> getYearIncome(Date date, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Income> incomeList = incomeMapper.selectByYear(date);
        return incomeList;
    }

    @Override
    public Double getSeason(int month, int year) {
        int seasonNum = (int) (Math.ceil(month / 4.0));
        List<Income> incomeList = incomeMapper.selectAllIncome();
        List<Income> added = new ArrayList<>();
        Double result = 0.0;
        for (Income income : incomeList) {
            Date date = income.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int itemSeason = (int) Math.ceil((calendar.get(Calendar.MONTH) + 1) / 4.0);
            if (itemSeason == seasonNum && year == calendar.get(Calendar.YEAR)) {
                result += income.getIncomeAll();
                added.add(income);
            }
        }
        return result;
    }

    @Override
    public List<String> getProductName() {
        return productMapper.selectProductName();
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
