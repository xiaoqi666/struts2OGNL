package com.xiaoqi.a_ognl;

import com.xiaoqi.domain.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ognl语法
 */
public class Demo {
    @Test
    public void fun1() {
        //准备ROOT
        User rootUser = new User("tom", 18);
        //准备Context
        Map<String, User> context = new HashMap<>();
        context.put("user1", new User("jack", 18));
        context.put("user2", new User("rose", 22));

        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //书写ognl
        try {
            //取root中user对象的name
            String name = (String) Ognl.getValue("name", oc, oc.getRoot());
            Integer age = (Integer) Ognl.getValue("age", oc, oc.getRoot());
            System.out.println(name);
            System.out.println(age);

            //取Context中的user对象的name属性
            //#代表從Context中取
            String name2 = (String) Ognl.getValue("#user1.name", oc, oc.getRoot());
            String name3 = (String) Ognl.getValue("#user2.name", oc, oc.getRoot());
            System.out.println(name2);
            System.out.println(name3);

            //为root中的user的name属性赋值
            Ognl.getValue("name='jerry'", oc, oc.getRoot());
            System.out.println((String) Ognl.getValue("name", oc, oc.getRoot()));

            //为context中的user的name属性赋值
            Ognl.getValue("#user1.name='nancy'", oc, oc.getRoot());
            System.out.println((String) Ognl.getValue("#user1.name", oc, oc.getRoot()));

            //调用方法
            Ognl.getValue("setName('linda')", oc, oc.getRoot());//root中user设置属性
            System.out.println(Ognl.getValue("getName()", oc, oc.getRoot()));//root中user获取属性
            System.out.println(Ognl.getValue("#user1.getName()", oc, oc.getRoot()));//context中user获取属性
            //调用静态方法
            System.out.println(Ognl.getValue("@com.xiaoqi.domain.TestUtils@echo('10')", oc, oc.getRoot()));
            //调用静态属性
            System.out.println(Ognl.getValue("@java.lang.Math@PI", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("@@PI", oc, oc.getRoot()));//OGNL内置Math

            //ognl创建对象(list,map对象)
            //创建list
            System.out.println(Ognl.getValue("{'哈哈','嘿嘿'}", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("{'哈哈','嘿嘿'}[0]", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("{'哈哈','嘿嘿'}.get(1)", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("{'哈哈','嘿嘿'}.size()", oc, oc.getRoot()));
            //创建map
            System.out.println(Ognl.getValue("#{'user':'xiaofang','age':18}", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("#{'user':'xiaofang','age':18}['age']", oc, oc.getRoot()));
            System.out.println(Ognl.getValue("#{'user':'xiaofang','age':18}.get('user')", oc, oc.getRoot()));
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }


}
