package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.query.UserQuery;
import com.itheima.mp.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    @Transactional
    public void deduct(Long id, Integer money) {
        User user = this.getById(id);
        if(user == null || user.getStatus() == 2){
            throw new RuntimeException("用户状态异常");
        }
        if(user.getBalance() < money){
            throw new RuntimeException("你没钱了");
        }
        System.out.println("user.getBalance() =" + user.getBalance());
        System.out.println("money =" + money);
        lambdaUpdate()
                .eq(user != null || user.getStatus() != 2, User::getId, id)
                .set(user.getBalance() >= money, User::getBalance, user.getBalance() - money)
                .set(Objects.equals(user.getBalance(), money), User::getStatus, 2)
                .eq(User::getBalance, user.getBalance())
                .update();
    }

    @Override
    public List<User> listByCustom(UserQuery query) {
        List<User> list = lambdaQuery()
                .like(query.getName() != null, User::getUsername, query.getName())
                .eq(query.getStatus() != null, User::getStatus, query.getStatus())
                .gt(query.getMinBalance() != null, User::getBalance, query.getMinBalance())
                .lt(query.getMaxBalance() != null, User::getBalance, query.getMaxBalance())
                .list();
        return list;
    }
}
