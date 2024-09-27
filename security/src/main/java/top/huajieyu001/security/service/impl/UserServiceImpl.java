package top.huajieyu001.security.service.impl;

import top.huajieyu001.security.entity.User;
import top.huajieyu001.security.mapper.UserMapper;
import top.huajieyu001.security.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huajieyu
 * @since 2024-09-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
