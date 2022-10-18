package tech.fengjian.service;


import tech.fengjian.pojo.bo.UserBO;
import tech.fengjian.pojo.Users;

public interface UserService {

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return true: 存在 false: 不存在
     */
    boolean queryUserNameIsExist(String username);

    /**
     * 创建用户
     *
     * @param userBO 注册信息
     * @return 用户信息
     */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    Users queryUserForLogin(String username, String password);
}
