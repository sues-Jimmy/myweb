package com.sues.myweb.utils;

/**
 * @author Alan
 * @date 2022/12/30 19:56
 **/
public class UserHolder {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();
    public static final void saveAccount(String account){
        tl.set(account);
    }
    public static final String getAccount(){
        return tl.get();
    }
    public static final void removeAccount(){
        tl.remove();
    }
}
