package com.zhaokxkx13.utils;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Created by zhaokxkx13 on 2017/5/3.
 */
public class WEKADbutils {
    public static InstanceQuery getQuery() {
        InstanceQuery query = null;
        try {
            query = new InstanceQuery();
            query.setDatabaseURL("jdbc:mysql://43.241.222.147:3306/stat?characterEncoding=utf-8");
            query.setUsername("root");
            query.setPassword("3362486");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }

    public static Instances getData(String sql) {
        Instances data = null;
        try {
            InstanceQuery query = getQuery();
            query.setQuery(sql);
            data = query.retrieveInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
