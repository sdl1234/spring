package com.sdl.service.test;

import java.util.Calendar;

/**
 * @PackageName: com.sdl.service.test
 * @ClassName: service
 * @Description:
 * @author: sdl
 * @date: 2022/6/22/20:20
 */
public class MockServiceImpl  implements MockService{
    @Override
    public String get() {
        Calendar instance = Calendar.getInstance();
        return getCount(instance);
    }

    private String getCount(Calendar instance) {


        int i = instance.get(Calendar.DATE);
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (i > 1 && i < actualMaximum){
            return "月中";
        }else if (i == 1){
            return "月初";
        }else {
            return "月底";
        }

    }
}
