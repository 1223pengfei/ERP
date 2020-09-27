package com.scd.erp.utils.trace;

import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.util.StringUtil;

public class tableNameUtil {
    public static String tableName(Class<?> entityClass) {
        EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
        String prefix = entityTable.getPrefix();
        return StringUtil.isNotEmpty(prefix) ? prefix + "." + entityTable.getName() : entityTable.getName();
    }
}
