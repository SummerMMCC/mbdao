package com.mmc.mbdao.dao.provider;

import com.mmc.mbdao.util.LocalObjectUtil;
import com.mmc.mbdao.util.LocalStringUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BaseDaoProvider {
    public String findByCondition(Class clazz, Map<String, Object> map) {
        return new SQL() {{
            SELECT(LocalObjectUtil.getPropertyNameLines(clazz));
            FROM(LocalStringUtil.classToLine(clazz));
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                WHERE(entry.getKey() + entry.getValue());
            }
        }}.toString();
    }
}
