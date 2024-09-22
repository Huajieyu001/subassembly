package com.itheima.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.itheima.mp.domain.po.Address;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.AddressVO;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.enums.UserStatus;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.query.UserQuery;
import com.itheima.mp.service.IAddressService;
import com.itheima.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IAddressService addressService;

    @Override
    @Transactional
    public void deduct(Long id, Integer money) {
        User user = this.getById(id);
        if(user == null || user.getStatus() == UserStatus.FREEZE){
            throw new RuntimeException("用户状态异常");
        }
        if(user.getBalance() < money){
            throw new RuntimeException("你没钱了");
        }
        System.out.println("user.getBalance() =" + user.getBalance());
        System.out.println("money =" + money);
        lambdaUpdate()
                .eq(user != null || user.getStatus() != UserStatus.FREEZE, User::getId, id)
                .set(user.getBalance() >= money, User::getBalance, user.getBalance() - money)
                .set(Objects.equals(user.getBalance(), money), User::getStatus, UserStatus.FREEZE)
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

    @Override
    public UserVO queryUserAndAddressById(Long id) {
        User user = this.getById(id);
        if(user == null || user.getStatus() == UserStatus.FREEZE){
            throw new RuntimeException("用户状态异常");
        }
        List<Address> list = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, id)
                .list();
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if(!CollUtil.isEmpty(list)){
            userVO.setAddresses(BeanUtil.copyToList(list, AddressVO.class));
        }
        return userVO;
    }

    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> ids) {
        List<UserVO> list = new ArrayList<>();
        ids.forEach(e -> {
            UserVO userVO = this.queryUserAndAddressById(e);
            list.add(userVO);
        });
        return list;
    }
}
