package com.sues.myweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sues.myweb.dao.AccountandpasswordDao;
import com.sues.myweb.entity.Accountandpassword;
import com.sues.myweb.service.AccountandpasswordService;
import com.sues.myweb.utils.Result;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.management.Query;

/**
 * (Accountandpassword)表服务实现类
 *
 * @author makejava
 * @since 2022-12-26 15:30:19
 */
@Service("accountandpasswordService")
public class AccountandpasswordServiceImpl extends ServiceImpl<AccountandpasswordDao, Accountandpassword> implements AccountandpasswordService {
    private static final Logger logger = LoggerFactory.getLogger(AccountandpasswordService.class);

    //登录验证系统
    @Override
    public Result loginVerify(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        String account = rootNode.get("username").asText();
        String password = rootNode.get("password").asText();
        if(account.length() > 11 || account.length() < 9){
            return Result.fail("error");
        }
        //select password from accountandpassword where account == account
        Accountandpassword accountandpassword = query().eq("account", account).one();
        if(accountandpassword == null){
            System.out.println("账号不存在");
            return Result.fail("账号不存在");
        }
        if(!accountandpassword.getPassword().equals(password)){
            System.out.println("密码错误");
            return Result.fail("密码错误");
        }
        //快速打印日志
        logger.info("进入系统");
        Result.ok("进入系统");
        return Result.ok();
    }

}

