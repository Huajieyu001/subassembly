package top.huajieyu001.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import top.huajieyu001.security.entity.User;
import top.huajieyu001.security.mapper.UserMapper;
import top.huajieyu001.security.service.IUserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBUserMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> param = new QueryWrapper<User>().eq("username", username);

        User user = mapper.selectOne(param);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        org.springframework.security.core.userdetails.User detailUser =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, grantedAuthorities);


        return detailUser;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
