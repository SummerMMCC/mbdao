package com.mmc.mbdao.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;


public class LocalObjectUtil {
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        return getPropertyNames(true, source);
    }

    public static String[] getNotNullPropertyNames(Object source) {
        return getPropertyNames(false, source);
    }

    public static String[] getPropertyNames(boolean isNull, Object source) {
        Set<String> emptyNames = getPropertyNameSet(isNull, source);
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static Set<String> getNotNullPropertyNameSet(Object source) {
        return getPropertyNameSet(false, source);
    }

    public static Set<String> getPropertyNameSet(boolean isNull, Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            if ("class".equalsIgnoreCase(pd.getName())) continue;
            Object srcValue = src.getPropertyValue(pd.getName());
            if (isNull) {
                if (ObjectUtils.isEmpty(srcValue)) emptyNames.add(pd.getName());
            } else {
                if (ObjectUtils.isNotEmpty(srcValue)) emptyNames.add(pd.getName());
            }

        }
        return emptyNames;
    }


    public static List<String> getPropertyNameList(Class clazz, List<String> nameList) {
        return getPropertyNameList(clazz, nameList, true);
    }

    public static List<String> getNotSupperPropertyNameList(Class clazz, List<String> nameList) {
        return getPropertyNameList(clazz, nameList, false);
    }


    private static List<String> getPropertyNameList(Class clazz, List<String> nameList, boolean needSuper) {
        Field[] pFields = clazz.getFields();
        List<Field> fieldList = Arrays.asList(pFields);
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if (fieldList.contains(field)) return;
            nameList.add(LocalStringUtil.toLowerCaseFirstOne(field.getName()));
        });
        if (needSuper && clazz.getSuperclass() != Object.class) {
            getPropertyNameList(clazz.getSuperclass(), nameList);
        }
        return nameList;
    }


    public static String[] getPropertyNameLines(Class clazz) {
        List<String> nameList = getPropertyNameList(clazz, new ArrayList<>());
        String[] names = new String[nameList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = LocalStringUtil.humpToLine(nameList.get(i));
        }
        return names;
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else {
            return object instanceof Map ? ((Map) object).isEmpty() : false;
        }
    }

}
