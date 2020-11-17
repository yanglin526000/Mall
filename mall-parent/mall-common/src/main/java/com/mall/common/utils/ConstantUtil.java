package com.mall.common.utils;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Common Constant
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 00:34:31
 */
@Component
public final class ConstantUtil {

    /**
     * HashMap Load factor initialization - (8 [0x8])
     */
    public static final int RESULT_MAP_INIT_COUNT = 0x8;

    /**
     * The default maximum number per page
     */
    public static final String DEFAULT_PAGE_SIZE = "10000";

    /**
     * The default page index number
     */
    public static final String DEFAULT_PAGE_INDEX = "0";

    /**
     * Deleted flag
     */
    public static final Byte IS_DELETE = 1;

    /**
     * Not Deleted flag
     */
    public static final Byte IS_NOT_DELETE = 0;

    /**
     * Project Package Name
     */
    public static final String PROJECT_PACKAGE_NAME = "com.mall.";

    /**
     * Common Package Name
     */
    public static final String COMMON_PACKAGE_NAME = PROJECT_PACKAGE_NAME + "common.*";

    /**
     * Goods Package Name
     */
    public static final String POJO_PACKAGE_NAME = ".pojo";

    /**
     * Goods Package Name
     */
    public static final String GOODS_PACKAGE_NAME = PROJECT_PACKAGE_NAME + "goods" + POJO_PACKAGE_NAME;

    /**
     * User Package Name
     */
    public static final String USER_PACKAGE_NAME = PROJECT_PACKAGE_NAME + "user" + POJO_PACKAGE_NAME;
}
