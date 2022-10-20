package tech.fengjian.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.fengjian.pojo.UserAddress;
import tech.fengjian.pojo.bo.AddressBO;
import tech.fengjian.service.AddressService;
import tech.fengjian.utils.JSONResult;
import tech.fengjian.utils.MobileEmailUtils;

import java.util.List;
import java.util.Objects;

@Api(value = "地址接口", tags = {"用户地址相关接口"})
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "查询用户收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult list(
            @ApiParam(name = "用户ID", value = "用户", required = true)
            @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        List<UserAddress> list = addressService.queryAll(userId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "新增用户收货地址", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody AddressBO addressBO) {

        JSONResult checkAddress = checkAddress(addressBO);
        if (!Objects.equals(checkAddress.getStatus(), 200)) {
            return checkAddress;
        }
        addressService.addNewUserAddress(addressBO);
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户修改收货地址", httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(@RequestBody AddressBO addressBO) {

        if (StringUtils.isBlank(addressBO.getAddressId())) {
            return JSONResult.errorMsg("修改地址错误：addressId不能为空");
        }
        JSONResult checkAddress = checkAddress(addressBO);
        if (!Objects.equals(checkAddress.getStatus(), 200)) {
            return checkAddress;
        }
        addressService.updateUserAddress(addressBO);
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户删除收货地址", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(@RequestParam String userId,
                             @RequestParam String addressId) {

        if (StringUtils.isBlank(addressId) ||
                StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        addressService.deleteUserAddress(userId, addressId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户设置默认收货地址", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public JSONResult setDefault(@RequestParam String userId,
                                 @RequestParam String addressId) {

        if (StringUtils.isBlank(addressId) ||
                StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        addressService.updateUserAddressToDefault(userId, addressId);
        return JSONResult.ok();
    }

    private JSONResult checkAddress(AddressBO addressBO) {

        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return JSONResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return JSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return JSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return JSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean mobileIsOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!mobileIsOk) {
            return JSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();

        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return JSONResult.errorMsg("收货人地址不能为空");
        }

        return JSONResult.ok();
    }

}
