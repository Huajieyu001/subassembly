package top.huajieyu001.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.huajieyu001.security.entity.User;
import top.huajieyu001.security.service.IUserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huajieyu
 * @since 2024-09-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }
}
