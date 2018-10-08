package com.xiaoqi.b_config;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 模型驱动:两种方式,写的时候写一种即可
 */
public class Demo3Action extends ActionSupport {
    private String name;

    @Override
    public String execute() throws Exception {
        name = "jerry";//配合struts.xml和ongl给http地址动态携带参数(${name})
        return SUCCESS;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
