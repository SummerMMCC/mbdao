package com.mmc.mbdao.dao;

import com.mmc.mbdao.dao.provider.BaseDaoProvider;
import com.mmc.mbdao.entity.BaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseDao<E extends BaseEntity> {
    @SelectProvider(type = BaseDaoProvider.class, method = "findByCondition")
    List<E> findByCondition(Class clazz, Map<String, Object> map);
}
