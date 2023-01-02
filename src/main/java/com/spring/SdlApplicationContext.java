package com.spring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName: com.spring
 * @ClassName: SdlApplicationContest
 * @Description:
 * @author: sdl
 * @date: 2022/6/13/2:23
 */
public class SdlApplicationContext {


    private Class configClass;

    /**
     * 单利
     */
    private final String SINGLETON = "singleton";

    /**
     * 单利池
     */
    private final ConcurrentHashMap<String, Object> singletonPool = new ConcurrentHashMap<>();

    /**
     * bean定义map
     */
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public SdlApplicationContext(Class configClass) {
        this.configClass = configClass;

        //扫描bean
        scan(configClass);

        //创建bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals(SINGLETON)) {
                Object bean = creatBean(beanDefinition);
                singletonPool.put(beanName,bean);
            }
        }



    }

    private Object creatBean(BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getClazz();

        Object instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;

    }

    private void scan(Class configClass) {
        //获取注解
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        //获取类路径
        String path = componentScanAnnotation.value();
        path = path.replace(".", "/");
//        System.out.println(path);


        ClassLoader classLoader = SdlApplicationContext.class.getClassLoader();
        URL loaderResource = classLoader.getResource(path);
//        System.out.println(loaderResource);
        File file = new File(loaderResource.getFile());
//        System.out.println("file = " + file);

        if (file.isDirectory()) {

            File[] files = file.listFiles();
            for (File f : files) {

//                System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
                String fileName = f.getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
//                    System.out.println("fileName = " + fileName);
                    className = className.replace("\\", ".");
//                    System.out.println("className = " + className);

                    try {
                        Class<?> loadClass = classLoader.loadClass(className);
//                        System.out.println("loadClass = " + loadClass);
                        if (loadClass.isAnnotationPresent(Component.class)) {
                            //当前类是bean
                            Component componentAnnotation = loadClass.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(loadClass);
                            if (loadClass.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = loadClass.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                beanDefinition.setScope(SINGLETON);
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }


                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }


        }
    }

    public Object getBean(String BeanName) {

        if (BeanName.isEmpty()) {
            return null;
        }

        if (beanDefinitionMap.containsKey(BeanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(BeanName);
            if (beanDefinition.getScope().equals(SINGLETON)) {
                return singletonPool.get(BeanName);
            } else {
                //创建bean
                return creatBean(beanDefinition);
            }

        } else {
            return new NullPointerException();
        }

    }
}
