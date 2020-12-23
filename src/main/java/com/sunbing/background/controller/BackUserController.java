package com.sunbing.background.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunbing.background.entity.BackUser;
import com.sunbing.background.entity.ResponseResult;
import com.sunbing.background.param.BackUserQueryParam;
import com.sunbing.background.param.BackUserSaveParam;
import com.sunbing.background.service.BackUserService;
import com.sunbing.background.vo.BackUserPageVo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author sunbing
 * @since 2020-11-23
 */
@Controller
@RequestMapping("/back/user")
public class BackUserController extends BaseController {
    @Autowired
    private BackUserService backUserService;

    @GetMapping(value = "/listByPage",produces = "application/json;charset=UTF-8")
    public String listByPage(HttpServletRequest request){
        return "backUser/list";
    }

    @PostMapping(value = "/listByPageData",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult listByPage(HttpServletRequest request,BackUserQueryParam param){
        Page page = new Page(param.getCurrent(),param.getSize());
        IPage<BackUserPageVo> pageInfo = backUserService.selectPageVo(page,param);
        return ResponseResult.Success(pageInfo);
    }

    @GetMapping(value = "/create",produces = "application/json;charset=UTF-8")
    public String create(HttpServletRequest request){
        return "backUser/create";
    }

    @PostMapping(value = "/saveOrUpdate",produces = "application/json;charset=UTF-8")
    public String saveOrUpdate(HttpServletRequest request,BackUserSaveParam param){
        //参数校验
        try {
            ResponseResult result = null;
            if(param.getId() == null){
                result = backUserService.saveEntity(param);
            }else{
                result = backUserService.updateEntity(param);
            }
            if(result.getReturncode() == 0){
                return "common/success";
            }else{
                request.setAttribute("message",result.getMessage());
                return "common/fail";
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message", ExceptionUtils.getStackTrace(e));
            return "common/fail";
        }
    }

    @GetMapping(value = "/edit",produces = "application/json;charset=UTF-8")
    public String edit(HttpServletRequest request){
        Integer id = getIdFromRequest(request);
        BackUser backUser = backUserService.getById(id);
        request.setAttribute("model",backUser);
        return "backUser/edit";
    }

    @GetMapping(value = "/resetPwd",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult resetPwd(HttpServletRequest request){
        Integer id = getIdFromRequest(request);
        return backUserService.resetPwd(id);
    }

    @GetMapping(value = "/deleteById",produces = "application/json;charset=UTF-8")
    public String delById(HttpServletRequest request){
        Integer id = getIdFromRequest(request);
        backUserService.removeById(id);
        return "redirect:listByPage";
    }

    @GetMapping(value = "/delBatch",produces = "application/json;charset=UTF-8")
    public String delBatch(HttpServletRequest request){
        String ids = request.getParameter("ids");
        String[] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String str : idArray){
            idList.add(Integer.parseInt(str));
        }
        backUserService.removeByIds(idList);
        return "redirect:listByPage";
    }

}

