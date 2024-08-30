package com.maffy.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.maffy.entity.dto.PageDTO;
import com.maffy.entity.dto.UserFormDTO;
import com.maffy.entity.po.User;
import com.maffy.entity.po.UserInfo;
import com.maffy.entity.query.UserQuery;
import com.maffy.entity.vo.UserVO;
import com.maffy.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/18/2024 9:22 PM
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor     //lombok, 生成必要字段的构造器
public class UserController {

    // @Autowired   不推荐对字段进行注入，推荐使用构造器注入，但不是所有的字段都要注入，
    // 加 final 的字段需要在初始化时完成对其的初始化，将需要注入的字段加final，再利用lombok自动生成只包含它们的构造器
    private final IUserService userService;

    @ApiOperation("新增用户接口")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userDTO) {
        // 1. 把DTO拷贝到PO
        User user = BeanUtil.copyProperties(userDTO, User.class, "info");
        System.out.println(userDTO.getInfo());
        UserInfo userInfo = JSONUtil.toBean(userDTO.getInfo(), UserInfo.class);
        System.out.println(userInfo);
        user.setInfo(userInfo);
        // 2. 新增
        userService.save(user);
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("{id}")
    public void deleteUserById(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.removeById(id);
    }

    @ApiOperation("根据id查询用户接口")
    @GetMapping("{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable("id") Long id) {
        return userService.queryUserWithAddressById(id);
    }

    @ApiOperation("根据id批量查询用户接口")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @RequestParam("ids") List<Long> ids) {
        return userService.queryUserWithAddressByIds(ids);
    }

    @ApiOperation("根据id扣减用户余额")
    @PutMapping("{id}/deduction/{money}")
    public void decrBalanceById(
            @ApiParam("用户id") @PathVariable("id") Long id,
            @ApiParam("扣减金额") @PathVariable("money") Integer money
    ) {
        userService.decrBalanceById(id, money);
    }

    @ApiOperation("根据复杂条件查询用户接口")
    @GetMapping("/list")
    public List<UserVO> queryUserByCondition(UserQuery query) {
        // 1. 查询用户PO
        List<User> users = userService.queryUserByCondition(query.getName(), query.getStatus(), query.getMinBalance(),query.getMaxBalance());
        // 2. 把PO拷贝到VO
        return BeanUtil.copyToList(users, UserVO.class);
    }

    /**
     * ************************************************************
     * @param query
     * @return
     */
    @ApiOperation("根据复杂条件分页查询用户接口")
    @GetMapping("/page")
    public PageDTO<UserVO> queryUserPages(UserQuery query) {
        return userService.queryUserPages(query);
    }

    //**************************************************************
}
