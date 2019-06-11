package com.ylf.manage.util;

public class Test {
    public static void main(String[] args)throws Exception {
        //返回6位随机数(验证码)
        int code = (int) ((Math.random()*9+1) * 100000);
        System.out.println(code);
    }
}
