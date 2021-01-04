package com.mmc.mbdao.dao.provider;

import com.mmc.mbdao.util.LocalObjectUtil;
import com.mmc.mbdao.util.LocalStringUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BaseDaoProvider {
    public String findByCondition() {
        return new SQL() {{
            SELECT("*");
            FROM("mmc_user");
        }}.toString();
    }
}
