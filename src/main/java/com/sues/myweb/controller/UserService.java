package com.sues.myweb.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sues.myweb.entity.Accountandpassword;
import com.sues.myweb.service.AccountandpasswordService;
import com.sues.myweb.utils.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Accountandpassword)表控制层
 *
 * @author Alan
 * @since 2022-12-26 15:30:14
 */
@RestController
@RequestMapping("/userService")
public class UserService{

    @Resource
    private AccountandpasswordService accountandpasswordService;

    //验证登录部分
    @PostMapping("/login")
    public Result login(@RequestBody String jsonString) throws JsonProcessingException {
        return accountandpasswordService.loginVerify(jsonString);
    }
}

