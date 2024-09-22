package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.query.UserQuery;

import java.util.List;

public interface IUserService extends IService<User> {

    void deduct(Long id, Integer money);

    List<User> listByCustom(UserQuery query);
}
