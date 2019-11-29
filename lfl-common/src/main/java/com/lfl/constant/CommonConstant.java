package com.lfl.constant;

public class CommonConstant {

    /**
     *redis三天有效期
     */
    public static final int REDIS_EXPIRE_SECONDS = 60 * 60 * 24 * 3;

    /**
     * redis-key的前缀
     */
    public static final String REDIS_KEY_PREX ="user:info:";

    public static final long JWT_EXPIRE_Millis = 60 * 60 * 24 *3* 1000L;

}
