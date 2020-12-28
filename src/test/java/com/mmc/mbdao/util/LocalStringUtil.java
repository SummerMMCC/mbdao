package com.mmc.mbdao.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LocalStringUtil {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern midLinePattern = Pattern.compile("-(\\w)");

    /** 驼峰转下划线 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(toLowerCaseFirstOne(str));
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        return toHump(str, Constants.Symbol.UNDER_LINE);
    }

    /**  中横线转驼峰 */
    public static String midLineToHump(String str) {
        return toHump(str, Constants.Symbol.MID_LINE);
    }

    private static String toHump(String str, String symbol) {
        str = str.toLowerCase();
        Matcher matcher;
        if (Constants.Symbol.MID_LINE.equals(symbol)) {
            matcher = midLinePattern.matcher(str);
        } else {
            matcher = linePattern.matcher(str);
        }

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 对象转下划线 BaseDaoProvider => base_dao_provider*/
    public static String classToLine(Class clazz) {
        return humpToLine(clazz.getSimpleName());
    }

    /** 首字母转小写*/
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }


    public static Set<String> getTargetListWithPattern(Pattern pattern , String str) {
        Set<String> list = new HashSet<>();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

}
