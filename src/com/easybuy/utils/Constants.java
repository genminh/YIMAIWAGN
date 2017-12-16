package com.easybuy.utils;

public class Constants {
    public static interface ReturnResult{
        /**
         * json 格式返回数据的status标识说明
         */
        public static int SUCCESS =1;
        public static int FAIL = -1;
    }

    /**
     * 前台用户
     */
    public static interface UserType{
        public static int PRE = 0;
        public static int  BACKEND = 1;
    }
}
