package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.Bean.*;
import com.zhaokxkx13.dao.entity.*;
import com.zhaokxkx13.dao.inf.*;
import com.zhaokxkx13.service.CustomerService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Service
public class CustomerServicceImpl implements CustomerService {
    private static final String ORDER_CACHE_NAME = "mybatisCache";
    private static final String PRODUCT_CACHE_NAME = "productCache";
    private static final String RECEIVABLE = "receivable";
    private static final String COLLECTED = "collected";
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductOrderMapper productOrderMapper;
    @Autowired
    DepartmentPlanMapper departmentPlanMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<CustomerConsume> getTopNCustomerConsum(Date startDate, Date endDate, int n) {
        Map<String, Date> map = new HashMap<>();
        map.put("startDate", startDate);
        if (endDate != null) {
            map.put("endDate", endDate);
        }
        List<ProductOrder> productOrderList = productOrderMapper.selectByDate(map);
        List<CustomerConsume> customerConsumeList = new ArrayList<>();
        Map<String, CustomerConsume> reduceMap = new HashMap<>();
        for (ProductOrder item : productOrderList) {
            String companyName = item.getOrder().getCustomer().getCompany().getName();
            if (reduceMap.get(companyName) != null) {
                reduceMap.get(companyName).setMoneyAmount(reduceMap.get(companyName).getMoneyAmount() + item.getPrice() * item.getNum());
                reduceMap.get(companyName).setNum(reduceMap.get(companyName).getNum() + item.getNum());
            } else {
                CustomerConsume consume = new CustomerConsume();
                Order order = item.getOrder();
                Customer customer = order.getCustomer();
                consume.setCustomer(customer);
                consume.setNum(item.getNum());
                consume.setMoneyAmount(item.getPrice() * item.getNum());
                reduceMap.put(item.getOrder().getCustomer().getCompany().getName(), consume);
            }
        }
        for (Map.Entry<String, CustomerConsume> entry : reduceMap.entrySet()) {
            customerConsumeList.add(entry.getValue());
        }
        Collections.sort(customerConsumeList, new Comparator<CustomerConsume>() {
            @Override
            public int compare(CustomerConsume o1, CustomerConsume o2) {
                return (int) (o1.getMoneyAmount() - o2.getMoneyAmount());
            }
        });
        return customerConsumeList.size() > n ? customerConsumeList.subList(0, n) : customerConsumeList;
    }

    @Override
    public List<DepartmentSellKpi> getDepartmentSellKpi(Date startDate, Date endDate, int n) {
        Map<String, Date> parameterMap = new HashMap<>();
        parameterMap.put("startDate", startDate);
        parameterMap.put("endDate", endDate);
        List<DepartmentPlan> planList = departmentPlanMapper.selectByDate(parameterMap);
        List<ProductOrder> orderList = productOrderMapper.selectByDate(parameterMap);

        Map<String, Double> planMap = new HashMap<>();
        for (DepartmentPlan item : planList) {
            String departmentName = item.getDepartment().getName();
            planMap.put(departmentName, item.getPlanSell());
        }

        Map<String, Double> actMap = new HashMap<>();
        for (ProductOrder item : orderList) {
            String departmentName = item.getOrder().getEmployee().getDepartment().getName();
            Double actSell = item.getPrice() * item.getNum();
            if (!actMap.containsKey(departmentName)) {
                actMap.put(departmentName, actSell);
            } else {
                actMap.put(departmentName, actMap.get(departmentName) + actSell);
            }
        }

        List<DepartmentSellKpi> resultList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : actMap.entrySet()) {
            if (planMap.get(entry.getKey()) == null)
                continue;
            DepartmentSellKpi departmentSellKpi = new DepartmentSellKpi();
            departmentSellKpi.setDepartmentName(entry.getKey());
            departmentSellKpi.setActSell(entry.getValue());
            departmentSellKpi.setPlanSell(planMap.get(entry.getKey()) == null ? 0.0 : planMap.get(entry.getKey()));
            departmentSellKpi.setPersent(departmentSellKpi.getPlanSell() == 0 ? 0 : departmentSellKpi.getActSell() / departmentSellKpi.getPlanSell());
            resultList.add(departmentSellKpi);
        }

        resultList.sort(new Comparator<DepartmentSellKpi>() {
            @Override
            public int compare(DepartmentSellKpi o1, DepartmentSellKpi o2) {
                return -(int) (o1.getPersent() - o2.getPersent());
            }
        });
        return resultList;
    }

    @Cacheable(value = ORDER_CACHE_NAME)
    @Override
    public List<OrderDetails> getOrderDetails(String name) {
        List<Product> productList = productMapper.selectByName(name);
        List<OrderDetails> details = new ArrayList<>();
        for (Product product : productList) {
            List<ProductOrder> productOrders = product.getProductOrderList();
            String productName = product.getName();
            String typeBig = product.getTypeBig();
            String typeSmall = product.getTypeSmall();
            String spec = product.getSpec();
            for (ProductOrder order : productOrders) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setAddress(order.getOrder().getAddress());
                orderDetails.setBigType(typeBig);
                orderDetails.setNum(order.getNum());
                orderDetails.setFreitght(order.getOrder().getFreight());
                orderDetails.setSmallType(typeSmall);
                orderDetails.setPrice(order.getPrice());
                orderDetails.setOrderDateStr(order.getOrder().getDate().toString());
                orderDetails.setDate(order.getOrder().getDate());
                orderDetails.setReceiver(order.getOrder().getReceiver());
                orderDetails.setSender(order.getOrder().getSender());
                orderDetails.setProductName(productName);
                orderDetails.setSum(order.getNum() * order.getPrice());
                orderDetails.setSpec(spec);
                details.add(orderDetails);
            }
        }
        return details;
    }

    @Cacheable(value = PRODUCT_CACHE_NAME)
    @Override
    public List<ProductDetails> getProductDetails(String name) {
        List<OrderDetails> orderDetailsList = getOrderDetails(name);
        Calendar calendarBefore = Calendar.getInstance();
        calendarBefore.setTime(new Date());
        calendarBefore.add(Calendar.YEAR, -1);
        List<ProductDetails> result = new ArrayList<>();
        Map<Integer, Double> yearSum = new HashMap<>();
        Map<Pair<Integer, Integer>, ProductDetails> tempMap = new HashMap<>();
        for (OrderDetails orderDetails : orderDetailsList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(orderDetails.getDate());
            Integer year = calendar.get(Calendar.YEAR);
            if (calendarBefore.compareTo(calendar) > 0)
                continue;
            Integer month = calendar.get(Calendar.MONTH);
            Pair pair = new Pair(year, month + 1);
            if (yearSum.containsKey(year)) {
                yearSum.put(year, yearSum.get(year) + orderDetails.getSum());
            } else {
                yearSum.put(year, orderDetails.getSum());
            }
            if (!tempMap.containsKey(pair)) {
                ProductDetails details = new ProductDetails();
                details.setMonth(month + 1);
                details.setYear(year);
                details.setDate(orderDetails.getDate());
                details.setSellSum(orderDetails.getSum());
                tempMap.put(pair, details);
            } else {
                ProductDetails details = tempMap.get(pair);
                details.setSellSum(details.getSellSum() + orderDetails.getSum());
                tempMap.put(pair, details);
            }

        }
        for (Pair<Integer, Integer> key : tempMap.keySet()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tempMap.get(key).getDate());
            calendar.add(Calendar.YEAR, -1);
            int yearPre = calendar.get(Calendar.YEAR);
            calendar.setTime(tempMap.get(key).getDate());
            calendar.add(Calendar.MONTH, -1);
            int monthPre = calendar.get(Calendar.MONTH) + 1;
            Pair<Integer, Integer> samePair = new Pair<>(yearPre, key.getValue());
            Pair<Integer, Integer> circlePair = new Pair<>(key.getValue() == 1 ? yearPre : key.getKey(), monthPre);
            if (tempMap.containsKey(samePair)) {
                ProductDetails pd = tempMap.get(key);
                ProductDetails periodPd = tempMap.get(samePair);
                pd.setSameTime(periodPd.getSellSum());
                pd.setSameTimeRate((pd.getSellSum() / periodPd.getSellSum()));
                tempMap.put(key, pd);
            }
            if (tempMap.containsKey(circlePair)) {
                ProductDetails pd = tempMap.get(key);
                ProductDetails periodPd = tempMap.get(circlePair);
                pd.setPreTime(periodPd.getSellSum());
                pd.setIncreaseCircleRate((pd.getSellSum() - periodPd.getSellSum()) / periodPd.getSellSum());
                tempMap.put(key, pd);
            }
            ProductDetails pd = tempMap.get(key);
            pd.setPercent(pd.getSellSum() / yearSum.get(pd.getYear()));
        }
        List<ProductDetails> yearLast = new ArrayList<>();
        List<ProductDetails> yearNow = new ArrayList<>();

        for (Pair<Integer, Integer> key : tempMap.keySet()) {
            if (key.getKey() == calendarBefore.get(Calendar.YEAR)) {
                yearLast.add(tempMap.get(key));
            } else {
                yearNow.add(tempMap.get(key));
            }
        }

        Comparator<ProductDetails> onYearComparator = new Comparator<ProductDetails>() {
            @Override
            public int compare(ProductDetails o1, ProductDetails o2) {
                return -(int) (o1.getSellSum() - o2.getSellSum());
            }
        };

        yearLast.sort(onYearComparator);
        yearNow.sort(onYearComparator);
        int index = 0;
        for (ProductDetails item : yearLast) {
            item.setPos(++index);
        }
        index = 0;
        for (ProductDetails item : yearNow) {
            item.setPos(++index);
        }
        result.addAll(yearLast);
        result.addAll(yearNow);
        double sum = 0;

        result.sort(new Comparator<ProductDetails>() {
            @Override
            public int compare(ProductDetails o1, ProductDetails o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("#.00%");
//        for (ProductDetails item : result) {
//            if (item.getYear() == calendarBefore.get(Calendar.YEAR)) {
//                sum += item.getSellSum();
//                item.setSumToMonth(sum);
//                item.setSameTimeRateStr(decimalFormat.format(item.getSameTimeRate()));
//                item.setPercentStr(decimalFormat.format(item.getPercent()));
//                item.setIncreaseCircleRateStr(decimalFormat.format(item.getIncreaseCircleRate()));
//            }
//        }
//        sum = 0;
//        for (ProductDetails item : result) {
//            if (item.getYear() == calendarBefore.get(Calendar.YEAR) + 1) {
//                sum += item.getSellSum();
//                item.setSumToMonth(sum);
//                item.setSameTimeRateStr(decimalFormat.format(item.getSameTimeRate()));
//                item.setPercentStr(decimalFormat.format(item.getPercent()));
//                item.setIncreaseCircleRateStr(decimalFormat.format(item.getIncreaseCircleRate()));
//            }
//        }
//
        for (int i = 0; i < result.size(); i++) {
            if (i > 0 && result.get(i).getYear() != result.get(i - 1).getYear()) {
                sum = 0;
            }
            ProductDetails item = result.get(i);
            sum += item.getSellSum();
            item.setSumToMonth(sum);
            if (item.getSameTimeRate() != null)
                item.setSameTimeRateStr(decimalFormat.format(item.getSameTimeRate().doubleValue()));
            if (item.getPercent() != null)
                item.setPercentStr(decimalFormat.format(item.getPercent().doubleValue()));
            if (item.getIncreaseCircleRate() != null)
                item.setIncreaseCircleRateStr(decimalFormat.format(item.getIncreaseCircleRate().doubleValue()));
        }

        return result;
    }

    @Override
    public CustomerPurchase getCustomerPurchase(String companyName) {
        CustomerPurchase purchase = new CustomerPurchase();
        List<Company> companyList = companyMapper.selectByName(companyName);
        if (companyList == null || companyList.size() == 0)
            return purchase;
        List<Customer> customerList = customerMapper.selectByCompanyId(companyList.get(0).getId());
        List<Integer> customerIdList = new ArrayList<>();
        if (customerList == null || customerList.size() == 0)
            return purchase;
        for (Customer customer : customerList) {
            customerIdList.add(customer.getId());
        }
        List<Order> orderList = orderMapper.selectByCustomerId(customerIdList);

        List<Integer> orderIdList = new ArrayList<>();
        if (orderList == null || orderList.size() == 0)
            return purchase;
        for (Order order : orderList) {
            orderIdList.add(order.getId());
        }
        List<ProductOrder> productOrderList = productOrderMapper.selectByOrderId(orderIdList);

        Map<String, Double> bigTypeMap = new HashMap<>();
        Map<String, Double> smallTypeMap = new HashMap<>();
        Map<String, Double> typeSum = new HashMap<>();
        for (ProductOrder productOrder : productOrderList) {
            String bigType = productOrder.getProduct().getTypeBig();
            String smallType = productOrder.getProduct().getTypeSmall();
            Double sellSum = productOrder.getPrice() * productOrder.getNum();
            Double receivable = productOrder.getReceivable();
            Double collected = productOrder.getCollected();
            if (bigTypeMap.containsKey(bigType)) {
                bigTypeMap.put(bigType, bigTypeMap.get(bigType) + sellSum);
            } else {
                bigTypeMap.put(bigType, sellSum);
            }
            if (smallTypeMap.containsKey(smallType)) {
                smallTypeMap.put(smallType, smallTypeMap.get(smallType) + sellSum);
            } else {
                smallTypeMap.put(smallType, sellSum);
            }
            if (typeSum.containsKey(RECEIVABLE)) {
                typeSum.put(RECEIVABLE, typeSum.get(RECEIVABLE) + receivable);
            } else {
                typeSum.put(RECEIVABLE, receivable);
            }
            if (typeSum.containsKey(COLLECTED)) {
                typeSum.put(COLLECTED, typeSum.get(COLLECTED) + collected);
            } else {
                typeSum.put(COLLECTED, collected);
            }
        }
        purchase.setBigTypeSum(bigTypeMap);
        purchase.setSmallTypeSum(smallTypeMap);
        purchase.setPayType(typeSum);
        return purchase;
    }

    @Override
    public AreaSellDetails getAreaSellDetails(Date startDate, Date endDate) {
        Map<String, Date> parameter = new HashMap<>();
        parameter.put("startDate", startDate);
        parameter.put("endDate", endDate);
        List<Order> orderList = orderMapper.selectByDate(parameter);
        Map<String, Double> areaMap = new HashMap<>();
        Map<String, Double> cityMap = new HashMap<>();
        for (Order order : orderList) {
            String city = order.getCustomer().getCompany().getCity();
            String area = order.getCustomer().getCompany().getArea();
            List<ProductOrder> productOrderList = productOrderMapper.selectByOrderId(Collections.singletonList(order.getId()));
            double sum = 0.0;
            for (ProductOrder productOrder : productOrderList) {
                sum += productOrder.getNum() * productOrder.getPrice();
            }
            if (cityMap.containsKey(city)) {
                cityMap.put(city, cityMap.get(city) + sum);
            } else {
                cityMap.put(city, sum);
            }
            if (areaMap.containsKey(area)) {
                areaMap.put(area, areaMap.get(area) + sum);
            } else {
                areaMap.put(area, sum);
            }
        }
        AreaSellDetails result = new AreaSellDetails();
        result.setAreaSells(areaMap);
        result.setCitySells(cityMap);
        return result;
    }

}
