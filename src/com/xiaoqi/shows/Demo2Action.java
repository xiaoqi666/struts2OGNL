package com.xiaoqi.shows;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;
import com.xiaoqi.domain.User;

/**
 * 模型驱动:两种方式,写的时候写一种即可
 */
public class Demo2Action extends ActionSupport implements Preparable, ModelDriven<User> {
    private User user = new User();


    @Override
    public String execute() throws Exception {
        System.out.println("-------------Demo1Action!!!");
        System.out.println(user.getName());
        return SUCCESS;
    }

    @Override
    //实现方式1:Preparable
    public void prepare() throws Exception {
        //获得值栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        //压入栈顶
        vs.push(user);
    }

    @Override
    //实现方式2: ModelDriven<User>
    public User getModel() {
        return user;
    }
}
