package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User queryUserById(@Param("id") Long id);

    void updateBalanceByIds(@Param("ew") LambdaQueryWrapper wrapper, @Param("amount")int amount);

    @Update("update user set balance = balance - #{money} where id = #{id}")
    void deduct(@Param("id")Long id, @Param("money")Integer money);
}
