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
//            ��Run������,�������һ���������߳�Ҫ���������ݣ���Ȼ��һ��ǰ�߳���Ϊkey���浽map��
            Integer i = random.nextInt();
            String name = Thread.currentThread().getName();
            System.out.println("�߳�["+name+"]�������������"+i);
            data.put(name, i);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = data.get(name);
            System.out.println("�߳�["+name+"]�����ʱȡ��������������"+o);


//            ��Run��������֮ǰ���Ե�ǰ�߳�����ȡ���ݲ���ӡ���鿴�Ƿ����ȡ������
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }
}
