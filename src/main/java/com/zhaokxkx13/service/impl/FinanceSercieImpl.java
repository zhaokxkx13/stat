package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.dao.entity.Balance;
import com.zhaokxkx13.dao.entity.CashFlow;
import com.zhaokxkx13.dao.entity.Profit;
import com.zhaokxkx13.dao.inf.BalanceMapper;
import com.zhaokxkx13.dao.inf.CashFlowMapper;
import com.zhaokxkx13.dao.inf.ProfitMapper;
import com.zhaokxkx13.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
@Service
public class FinanceSercieImpl implements FinanceService {
    @Autowired
    BalanceMapper balanceMapper;

    @Autowired
    CashFlowMapper cashFlowMapper;

    @Autowired
    ProfitMapper profitMapper;

    Double assetsLiability = 0.0;

    @Override
    public Map<String, String> getKpiDetails(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, -1);
        Date startDateLastYear = calendar.getTime();
        calendar.setTime(endDate);
        calendar.add(Calendar.YEAR, -1);
        Date endDateLastYear = calendar.getTime();

        Map<String, String> resultMap = new HashMap<>();
        Map<String, Date> parameterMap = new HashMap<>();
        Map<String, Date> parameterMapLastYear = new HashMap<>();
        parameterMap.put("startDate", startDate);
        parameterMap.put("endDate", endDate);
        parameterMapLastYear.put("startDate", startDateLastYear);
        parameterMapLastYear.put("endDate", endDateLastYear);

        List<Balance> balanceList = balanceMapper.selectByDate(parameterMap);
        List<Profit> profitList = profitMapper.selectByDate(parameterMap);
        List<Profit> profitLastYearList = profitMapper.selectByDate(parameterMapLastYear);
        List<CashFlow> cashFlowList = cashFlowMapper.selectByDate(parameterMap);

        DecimalFormat decimalFormat = new DecimalFormat("00.00");
        DecimalFormat decimalFormat1 = new DecimalFormat("#.00");
        Double mainServicePorfit = 0.0;
        Double mainServiceProfitLastYear = 0.0;
        Double mainServiceIncome = 0.0;
        Double mainServiceCost = 0.0;
        Double mainServicePureProfit = 0.0;
        Double totalAssets = 0.0;
        Double totalLiability = 0.0;
        Double shareHolder = 0.0;
        Double cashSum = 0.0;
        for (Profit profit : profitList) {
            mainServiceIncome += profit.getIncome();
            mainServiceCost += profit.getCost();
            mainServicePureProfit += profit.getPureProfit();
            mainServicePorfit += profit.getProfit();
        }
        for (Profit profit : profitLastYearList) {
            mainServiceProfitLastYear += profit.getProfit();
        }
        for (Balance balance : balanceList) {
            totalAssets += balance.getCurrentAssets() + balance.getFixAssets();
            totalLiability += balance.getCurrentLiabilities() + balance.getFixAssets() + balance.getShareholders();
            shareHolder += balance.getShareholders();
        }
        for (CashFlow cashFlow : cashFlowList) {
            cashSum += cashFlow.getCashIncrease() + cashFlow.getExchangeCash() + cashFlow.getFinanceCash()
                    + cashFlow.getinvestmentCash() + cashFlow.getOperateCash();
        }
        resultMap.put("资产负债率", decimalFormat.format(totalLiability / totalAssets * 100));
        resultMap.put("营业利润率", decimalFormat.format(mainServicePureProfit / mainServiceIncome * 100));
        resultMap.put("营业利润增长率", decimalFormat.format((mainServicePorfit - mainServiceProfitLastYear) /
                mainServiceProfitLastYear * 100));
        resultMap.put("权益净利率", decimalFormat.format(mainServicePureProfit / shareHolder * 100));
        resultMap.put("现金比率", decimalFormat.format(cashSum / totalLiability * 100));
        resultMap.put("主营业务收入", decimalFormat1.format(mainServiceIncome / 10000));
        resultMap.put("主营业务成本", decimalFormat1.format(mainServiceCost / 10000));
        resultMap.put("主营业务利润", decimalFormat1.format(mainServicePorfit / 10000));
        assetsLiability = totalLiability / totalAssets;
        return resultMap;
    }

    @Override
    public Map<String, String> getDupontDetails(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, -1);
        Date startDateLastYear = calendar.getTime();
        calendar.setTime(endDate);
        calendar.add(Calendar.YEAR, -1);
        Date endDateLastYear = calendar.getTime();

        Map<String, String> resultMap = new HashMap<>();
        Map<String, Date> parameterMap = new HashMap<>();
        Map<String, Date> parameterMapLastYear = new HashMap<>();
        parameterMap.put("startDate", startDate);
        parameterMap.put("endDate", endDate);
        parameterMapLastYear.put("startDate", startDateLastYear);
        parameterMapLastYear.put("endDate", endDateLastYear);
        Random random = new Random();
        List<Balance> balanceList = balanceMapper.selectByDate(parameterMap);
        List<Profit> profitList = profitMapper.selectByDate(parameterMap);
        List<Profit> profitLastYearList = profitMapper.selectByDate(parameterMapLastYear);
        List<CashFlow> cashFlowList = cashFlowMapper.selectByDate(parameterMap);

        Double operationCost = 0.0;
        Double sellCost = 0.0;
        Double mangementCost = 0.0;
        Double otherCost = 0.0;
        Double costSum = 0.0;
        Double otherProfit = 0.0;
        Double tax = 0.0;
        Double pureProfit = 0.0;
        Double sellPureProfitRate = 0.0;
        Double assetsPureProfitRate = 0.0;
        Double assetsRolarRate = 0.0;
        Double powerItem = 0.0;
        Double powerPureRate = 0.0;
        Double securitiesSum = 0.0;     //现金有价证券
        Double receivableSum = 0.0;
        Double otherAssets = 0.0;
        Double stockSum = 0.0;
        Double flowSum = 0.0;
        Double fixSum = 0.0;
        Double assetsSum = 0.0;
        Double sellIncome = 0.0;

        for (Balance balance : balanceList) {
            securitiesSum += balance.getSecurities();
            receivableSum += balance.getReceivable();
            stockSum += balance.getStock();
            otherAssets += random.nextDouble() * 1000;
            fixSum += balance.getFixAssets();
        }
        flowSum = securitiesSum + receivableSum + stockSum;
        assetsSum = flowSum + fixSum;
        for (Profit profit : profitList) {
            tax += profit.getIncomeTax();
            sellIncome += profit.getIncome();
            operationCost += profit.getCost();
            sellCost += profit.getExpenses();
            mangementCost += random.nextDouble() * 1000;
            otherCost += random.nextDouble() * 1000;
            otherProfit += random.nextDouble() * 1000;
        }
        costSum = operationCost + sellCost + mangementCost + otherCost;
        otherProfit = random.nextDouble() * 1000;
        pureProfit = sellIncome - costSum + otherProfit - tax;
        sellPureProfitRate = pureProfit / sellIncome;
        assetsPureProfitRate = pureProfit / assetsSum;
        assetsRolarRate = sellIncome / assetsSum;
        powerItem = 1 / (1 - random.nextDouble());
        powerPureRate = powerItem * assetsPureProfitRate;
        DecimalFormat decimalFormat = new DecimalFormat("00.00");
        DecimalFormat decimalFormat1 = new DecimalFormat("00.00%");
        resultMap.put("sellCost", decimalFormat.format(sellCost));
        resultMap.put("operationCost", decimalFormat.format(operationCost));
        resultMap.put("mangementCost", decimalFormat.format(mangementCost));
        resultMap.put("otherCost", decimalFormat.format(otherCost));
        resultMap.put("otherProfit", decimalFormat.format(otherProfit));
        resultMap.put("sellIncome", decimalFormat.format(sellIncome));
        resultMap.put("costSum", decimalFormat.format(costSum));
        resultMap.put("tax", decimalFormat.format(tax));
        resultMap.put("pureProfit", decimalFormat.format(pureProfit));
        resultMap.put("sellPureProfitRate", decimalFormat1.format(sellPureProfitRate));
        resultMap.put("assetsPureProfitRate", decimalFormat1.format(assetsPureProfitRate));
        resultMap.put("powerPureRate", decimalFormat1.format(powerPureRate));
        resultMap.put("securitiesSum", decimalFormat.format(securitiesSum));
        resultMap.put("receivableSum", decimalFormat.format(receivableSum));
        resultMap.put("stockSum", decimalFormat.format(stockSum));
        resultMap.put("otherAssets", decimalFormat.format(otherAssets));
        resultMap.put("fixSum", decimalFormat.format(fixSum));
        resultMap.put("flowSum", decimalFormat.format(flowSum));
        resultMap.put("assetsSum", decimalFormat.format(assetsSum));
        resultMap.put("assetsRolarRate", decimalFormat1.format(assetsRolarRate));
        resultMap.put("powerItem", decimalFormat.format(powerItem));
        return resultMap;
    }
}
