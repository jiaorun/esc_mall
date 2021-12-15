package com.esc.mall;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期 工具类
 *
 * @author jiaorun
 * @date 2021/09/15 15:06
 **/
public class DateUtils implements Serializable {

    private static final long serialVersionUID = 616632179784012334L;

    /**
     * 获取当前日期
     *
     * @return java.util.Date
     * @author jiaorun
     * @date 2021/09/15 15:07
     */
    public static Date currentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
