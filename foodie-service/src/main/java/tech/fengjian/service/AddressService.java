package tech.fengjian.service;

import tech.fengjian.pojo.UserAddress;
import tech.fengjian.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {


    /**
     * 用户地址列表
     *
     * @param userId 用户 id
     * @return 地址列表
     */
    List<UserAddress> queryAll(String userId);

    void addNewUserAddress(AddressBO addressBO);

    void updateUserAddress(AddressBO addressBO);

    void deleteUserAddress(String userId, String addressId);

    void updateUserAddressToDefault(String userId, String adressId);

    UserAddress queryUserAddress(String userId, String addressId);
}
