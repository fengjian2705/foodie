package tech.fengjian.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.fengjian.pojo.bo.UserBO;
import tech.fengjian.enums.Sex;
import tech.fengjian.mapper.UsersMapper;
import tech.fengjian.pojo.Users;
import tech.fengjian.service.UserService;
import tech.fengjian.utils.DateUtil;
import tech.fengjian.utils.MD5Utils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;
    public static final String USER_FACE = "https://avatars.githubusercontent.com/u/96092737?v=4";

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return true: 存在 false: 不存在
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String username) {
        Example example = new Example(Users.class);
        Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users users = usersMapper.selectOneByExample(example);
        return users != null;
    }

    /**
     * 创建用户
     *
     * @param userBO 注册信息
     * @return 用户信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        String userId = sid.nextShort();
        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 默认昵称同用户名
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01", DateUtil.ISO_EXPANDED_DATE_FORMAT));
        // 默认性别为 保密
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    /**
     * 检索用户名和密码是否匹配
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }

}
