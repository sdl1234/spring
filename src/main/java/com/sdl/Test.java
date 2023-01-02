package com.sdl;

import com.spring.SdlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @PackageName: com.sdl
 * @ClassName: Test
 * @Description:
 * @author: sdl
 * @date: 2022/6/13/2:21
 */
public class Test {


    public static void main(String[] args) throws ParseException {
        //启动项
        SdlApplicationContext applicationContest = new SdlApplicationContext(AppConfig.class);

        System.out.println(applicationContest.getBean("userService"));
        System.out.println(applicationContest.getBean("userService"));
        System.out.println(applicationContest.getBean("userService"));

        Calendar instance = Calendar.getInstance();
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        int actualMaximum2 = instance.getActualMaximum(Calendar.DAY_OF_YEAR);
        int actualMaximum3 = instance.getActualMaximum(Calendar.DAY_OF_WEEK);
        System.out.println("actualMaximum = " + actualMaximum);
        System.out.println("actualMaximum = " + actualMaximum2);
        System.out.println("actualMaximum = " + actualMaximum3);

        int actualMinimum = instance.getActualMinimum(Calendar.DAY_OF_MONTH);
        System.out.println("actualMinimum = " + actualMinimum);

        Date time = instance.getTime();
        System.out.println("time = " + time);

//        int i = instance.get(Calendar.DATE);
//        System.out.println("i = " + i);


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
// 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).collect(Collectors.toList());
        System.out.println("squaresList = " + squaresList);

        HashSet<String> set = new HashSet<>();
        set.add("123");
        set.add("123");
        set.add("1234");
        System.out.println("set = " + set);
        System.out.println("set.size() = " + set.size());

        HashMap<Object, Object> map = new HashMap<>();
        map.put("set1","1");
        map.put("set2","2");
        map.put("set3","3");

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        Calendar calendar = Calendar.getInstance();


        calendar.add(Calendar.DAY_OF_MONTH,-1);

        String format1 = df.format(calendar.getTime());
        System.out.println("format1 = " + format1);

    }
}
