package com.ylf.manage.util;

public class UUID {
    public static String getUUID(){   //返回32位随机码(数据库主键)
        String id=java.util.UUID.randomUUID().toString();
        return id.replaceAll("-","");
    }

    //*9+1是为了得到1-9之间的小数,或者直接乘10获得0-9之间的小数，Math.random()返回[0.0,1.0)
    public static String getCode(){   //返回6位随机数(验证码)
        int code= (int)((Math.random()*9+1)*100000);
        return  String.valueOf(code);
    }
}
