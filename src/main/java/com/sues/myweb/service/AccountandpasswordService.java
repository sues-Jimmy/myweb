package com.sues.myweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sues.myweb.entity.Accountandpassword;
import com.sues.myweb.utils.Result;

import javax.servlet.http.HttpServletResponse;

/**
 * (Accountandpassword)表服务接口
 *
 * @author makejava
 * @since 2022-12-26 15:30:19
 */
public interface AccountandpasswordService extends IService<Accountandpassword> {
    Result loginVerify(String jsonString,HttpServletResponse response) throws JsonProcessingException;

    Result registerAccount(String jsonString) throws JsonProcessingException;
}

