package cn.zxbetter.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * demo
 *
 * @author devin
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(DateUtils.truncate(new Date(), Calendar.DATE), "yyyy-MM-dd HH:mm:ss"));
    }
}

