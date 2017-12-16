package com.easybuy.utils;

import java.util.Collection;
import java.util.Map;

public class EmptyUtils {
    //判断是否为空
    public static boolean isEmpty(Object obj){
        if (obj == null){
            return true;
        }
        if (obj instanceof  CharSequence){
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Map){
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] objects = (Object[]) obj;
            if (objects.length ==0){
                return true;
            }
            boolean  empty = true;
            for (int i = 0;i < objects.length; i ++){
                if (!isEmpty(objects[i])){
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;

    }
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}
