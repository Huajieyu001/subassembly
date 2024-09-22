package com.itheima.mp.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void test(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void testSaveOneByOne() {
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            userService.save(buildUser(i));
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    private User buildUser(int i) {
        User user = new User();
        user.setUsername("user_" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
        UserInfo userInfo = UserInfo.of(24, "英文老师", "female");
        user.setInfo(userInfo);
//        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(user.getCreateTime());
        return user;
    }

    @Test
    void testSaveByBatch(){
        List<User> list = new ArrayList<>(1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(buildUser(i));
            if(i % 1000 == 0){
                userService.saveBatch(list);
                list.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    void testPage(){
        int no = 1;
        int size = 2;

        Page<User> page = Page.of(no, size);
        page = page.addOrder(new OrderItem("balance", true));
        page = page.addOrder(new OrderItem("id", true));

        Page<User> paged = userService.page(page);

        System.out.println("paged.getTotal() = " + paged.getTotal());
        System.out.println("paged.getPages() = " + paged.getPages());
        System.out.println("Data:");
        List<User> records = paged.getRecords();
        records.forEach(System.out::println);
    }
}
