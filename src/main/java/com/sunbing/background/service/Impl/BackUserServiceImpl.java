package com.sunbing.background.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunbing.background.constant.Constant;
import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.mapper.BackUserMapper;
import com.sunbing.background.param.BackUserQueryParam;
import com.sunbing.background.param.BackUserSaveParam;
import com.sunbing.background.service.BackUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunbing.background.vo.BackUserPageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sunbing
 * @since 2020-11-23
 */
@Service
public class BackUserServiceImpl extends ServiceImpl<BackUserMapper, BackUser> implements BackUserService {

    @Value("${login.salt}")
    private String salt;

    public IPage<BackUserPageVo> selectPageVo(Page page, BackUserQueryParam queryParam){
        return this.baseMapper.selectPageVo(page,queryParam);
    }

    @Override
    public BackUser getByUserNameAndPassword(String userName, String password) {
        return getOne(new QueryWrapper<>(new BackUser().setUserName(userName).setPassword(password)));
    }

    @Override
    public ResponseResult saveEntity(BackUserSaveParam param) {
        List<BackUser> list = list(new QueryWrapper<>(new BackUser().setUserName(param.getUserName())));
        if(!CollectionUtils.isEmpty(list)){
            return ResponseResult.Fail("用户已存在");
        }
        BackUser entity = new BackUser();
        BeanUtils.copyProperties(param,entity);
        String encryptionPwd = DigestUtils.md5DigestAsHex((param.getPassword() + salt).getBytes());
        entity.setPassword(encryptionPwd);
        boolean result = save(entity);
        if(result){
            return ResponseResult.Success("成功");
        }
        return ResponseResult.Fail("新增失败");
    }

    @Override
    public ResponseResult updateEntity(BackUserSaveParam param) {
        BackUser backUser = getById(param.getId());
        if(backUser==null){
            return ResponseResult.Fail("数据不存在，无法更新");
        }
        BackUser entity = new BackUser();
        BeanUtils.copyProperties(param,entity);
        boolean result = updateById(entity);
        if(result){
            return ResponseResult.Success("成功");
        }
        return ResponseResult.Fail("更新失败");
    }

    @Override
    public ResponseResult resetPwd(Integer id) {
        BackUser backUser = getById(id);
        if(backUser==null){
            return ResponseResult.Fail("数据不存在，无法更新");
        }
        String defaultPwd = Constant.DEFAULT_PWD;
        BackUser entity = new BackUser();
        entity.setId(id);
        entity.setPassword(DigestUtils.md5DigestAsHex((defaultPwd + salt).getBytes()));

        boolean result = updateById(entity);
        if(result){
            return ResponseResult.Success("成功，密码重置为：" + defaultPwd);
        }
        return ResponseResult.Fail("重置失败");
    }
}
