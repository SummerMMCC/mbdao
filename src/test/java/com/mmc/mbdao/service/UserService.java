package com.mmc.mbdao.service;

import com.mmc.mbdao.Application;
import com.mmc.mbdao.dao.BaseDao;
import com.mmc.mbdao.dto.UserDto;
import com.mmc.mbdao.entity.BaseEntity;
import com.mmc.mbdao.entity.UserEntity;
import com.mmc.mbdao.mapper.UserMapper;
import com.mmc.mbdao.util.AutoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Service
@Slf4j
public class UserService<E extends BaseEntity> {

    @Autowired
    BaseDao baseDao;

    @Autowired
    UserMapper userMapper;

    @Test
    public void findByCondition() {
        Map m = new HashMap<String, Object>();
        m.put("id = ", "1");
        baseDao.findByCondition(UserEntity.class, m);
    }

    @Test
    public void testmap() {
        userMapper.selectAll();
    }

    @Test
    public void save() {

        UserDto ud = new UserDto();
        ud.setPassword("!111");
        ud.setName("mmcc");

        UserEntity u = new UserEntity();

        AutoMapper.mapping(ud, u);

//        userMapper.insertSelective(u);
    }
}
