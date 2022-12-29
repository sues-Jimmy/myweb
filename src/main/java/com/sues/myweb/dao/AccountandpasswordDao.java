package com.sues.myweb.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.sues.myweb.entity.Accountandpassword;

/**
 * (Accountandpassword)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-26 15:30:14
 */
public interface AccountandpasswordDao extends BaseMapper<Accountandpassword> {

int insertBatch(@Param("entities") List<Accountandpassword> entities);

int insertOrUpdateBatch(@Param("entities") List<Accountandpassword> entities);

}

