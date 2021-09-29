package com.john.threadlocal;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author John
 * @create 2021-09-2712:20
 */
public class ThreadLocalTest {

    public static Map<String, Object> data = new Hashtable<>() ;
    private static Random random = new Random();

    public static class Task implements Runnable {
        @Override
        public void run() {
//            在Run方法中,随机生成一个变量（线程要关联的数据），然后一当前线程名为key保存到map中
            Integer i = random.nextInt();
            String name = Thread.currentThread().getName();
            System.out.println("线程["+name+"]生产的随机数是"+i);
            data.put(name, i);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = data.get(name);
            System.out.println("线程["+name+"]快结束时取出关联的数据是"+o);


//            在Run方法结束之前，以当前线程名获取数据并打印，查看是否可以取出操作
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }
}
