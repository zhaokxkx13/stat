package com.zhaokxkx13.DataBaseStat;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by zhaokxkx13 on 2017/3/10.
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {
        @WebInitParam(name = "loginUsername", value = "root"),
        @WebInitParam(name = "loginPassword", value = "3362486"),
        @WebInitParam(name = "resetEnable", value = "false")

})
public class DruidStatViewServlet extends StatViewServlet {
}
