package com.sues.myweb.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (Accountandpassword)表实体类
 *
 * @author makejava
 * @since 2022-12-26 15:30:18
 */
@Data
public class Accountandpassword extends Model<Accountandpassword> {


    private String account;
    
    private String password;



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    }

