package chapter01;

/**
 * @author caogs
 * @className: homework.java
 * @description:
 * @date 2019/9/1 14:59
 * @Copyright: TODO, Ltd. All rights reserved.
 * 注意：本内容仅限于TODO公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Homework {

    public static void main(String[] args) {
        IntSleep intSleep = new IntSleep();
        intSleep.start();

        try {
            Thread.sleep(2000);
            // 中断线程
            intSleep.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}

class IntSleep extends Thread {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("hello");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}