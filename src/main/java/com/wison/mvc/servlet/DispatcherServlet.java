package com.wison.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    List<String> classUrls = new ArrayList<String>();
    Map<String,Object> ioc = new HashMap<String, Object>();
    Map<String,Object> urlHandlers = new HashMap<String, Object>();

    public void init(ServletConfig config){
        doScanPackage("com.wison");//路径扫描，找类
        doInstance();//找到特殊注解的类，放入map
        doAutowired();//处理依赖关系
        doUrlMapping();//路径与方法一一映射

    }

    public void doScanPackage(String basePackage){
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.","/"));
        String fileStr = url.getFile();
        File file = new File(fileStr);
        String[] filesStr = file.list();
        for (String path : filesStr) {
            File filePath = new File(fileStr + path);// /WorkPlace/com/wison/
            if(filePath.isDirectory()){
                doScanPackage(basePackage + "." + path);
            }else {
                classUrls.add(basePackage + "." + filePath.getName().replace(".class",""));
            }

        }

    }
}
