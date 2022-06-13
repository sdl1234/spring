package com.sdl;

import com.spring.SdlApplicationContext;

/**
 * @PackageName: com.sdl
 * @ClassName: Test
 * @Description:
 * @author: sdl
 * @date: 2022/6/13/2:21
 */
public class Test {


    public static void main(String[] args) {
        //启动项
        SdlApplicationContext applicationContest = new SdlApplicationContext(AppConfig.class);

        System.out.println(applicationContest.getBean("userService"));
        System.out.println(applicationContest.getBean("userService"));
        System.out.println(applicationContest.getBean("userService"));
    }
}
