package com.maffy.service.impl;

import com.maffy.entity.po.Address;
import com.maffy.mapper.AddressMapper;
import com.maffy.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maffy
 * @since 2024-07-19
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
