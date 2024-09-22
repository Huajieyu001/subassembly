package com.itheima.mp.service;

import com.itheima.mp.domain.po.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceImplTest {

    @Autowired
    private IAddressService addressService;

    @Test
    void testLogicDelete(){
        Address before = addressService.getById(59L);
        addressService.removeById(59L);
        Address after = addressService.getById(59L);

        System.out.println("before \t= " + before);
        System.out.println("after \t= " + after);
    }
}
