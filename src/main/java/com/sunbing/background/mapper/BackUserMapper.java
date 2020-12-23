package com.sunbing.background.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunbing.background.entity.BackUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunbing.background.param.BackUserQueryParam;
import com.sunbing.background.vo.BackUserPageVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sunbing
 * @since 2020-11-23
 */
public interface BackUserMapper extends BaseMapper<BackUser> {
    IPage<BackUserPageVo> selectPageVo(Page page, @Param("queryParam") BackUserQueryParam queryParam);
}
