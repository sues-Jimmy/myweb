package com.sues.myweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sues.myweb.dao.AccountandpasswordDao;
import com.sues.myweb.entity.Accountandpassword;
import com.sues.myweb.service.AccountandpasswordService;
import com.sues.myweb.utils.Result;
import com.sues.myweb.utils.UserHolder;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * (Accountandpassword)表服务实现类
 *
 * @author makejava
 * @since 2022-12-26 15:30:19
 */
@Service("accountandpasswordService")
public class AccountandpasswordServiceImpl extends ServiceImpl<AccountandpasswordDao, Accountandpassword> implements AccountandpasswordService {
    private static final Logger logger = LoggerFactory.getLogger(AccountandpasswordService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 验证登录部分
     *
     * */
    @Override
    public Result loginVerify(String jsonString, HttpServletResponse response) throws JsonProcessingException {
        if (UserHolder.getAccount() == null) {
            System.out.println("1111");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonString);
            String account = rootNode.get("username").asText();
            String password = rootNode.get("password").asText();

            if(account.length() > 11 || account.length() < 9){
                return Result.fail("账号格式输入错误");
            }
            Accountandpassword accountandpassword = query().eq("account", account).one();
            if(accountandpassword == null){
                return Result.fail("账号不存在");
            }
            if(!accountandpassword.getPassword().equals(password)){
                return Result.fail("密码错误");
            }
            String token = UUID.randomUUID().toString();
            System.out.println(token);
            stringRedisTemplate.opsForValue().set(token,account);
            stringRedisTemplate.expire(token,3, TimeUnit.MINUTES);
            Cookie newCookie = new Cookie("token",account);
            newCookie.setMaxAge(180);
            response.addCookie(newCookie);
            System.out.println("添加cookie成功");
        }
        System.out.println("进入系统");
        return Result.ok("进入系统");
    }

    @Override
    public Result registerAccount(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        String account = rootNode.get("username").asText();
        String password = rootNode.get("password").asText();
        String confirmPassword = rootNode.get("confirmPassword").asText();
        //查询账户中是否已经存在该帐号了
        Accountandpassword accountandpassword1 = this.query().eq("account",account).one();
        if(accountandpassword1 != null){
            return Result.fail("账户已存在");
        }
        if(account.length() > 11 || account.length() < 9){
            return Result.fail("账号格式输入错误");
        }
        if(!password.equals(confirmPassword)){
            return Result.fail("两次密码不一致");
        }
        Accountandpassword accountandpassword = new Accountandpassword();
        accountandpassword.setAccount(account);
        accountandpassword.setPassword(password);
        this.save(accountandpassword);
        logger.info("注册成功");
        Result.ok("注册成功");
        return Result.ok();
    }

}

