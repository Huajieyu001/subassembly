package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.query.UserQuery;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("用户管理接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("新增用户接口")
    @PostMapping
    public void save(@ApiParam("用户id")@RequestBody UserFormDTO userFormDTO){
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("/{id}")
    public void delete(@ApiParam("用户id")@PathVariable("id") Long id){
        userService.removeById(id);
    }

    @ApiOperation("获取用户接口")
    @GetMapping("/{id}")
    public UserVO get(@ApiParam("用户id")@PathVariable("id") Long id){
        return userService.queryUserAndAddressById(id);
    }

    @ApiOperation("获取全部用户接口")
    @GetMapping
    public List<UserVO> getAll(@ApiParam("集合")@RequestParam("ids") List<Long> ids){
        return userService.queryUserAndAddressByIds(ids);
    }

    @ApiOperation("更新用户接口")
    @PutMapping("{id}/deduction/{money}")
    public void deduct(@ApiParam("用户id")@PathVariable("id") Long id, @ApiParam("用户扣除金额")@PathVariable("money") Integer money){
        userService.deduct(id, money);
    }

    @ApiOperation("获取符合条件的全部用户接口")
    @GetMapping("/list")
    public List<UserVO> getAll(@ApiParam("查询条件对象")UserQuery query){
        List<User> list = userService.listByCustom(query);
        return BeanUtil.copyToList(list, UserVO.class);
    }
}
