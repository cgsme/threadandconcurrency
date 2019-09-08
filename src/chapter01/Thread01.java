package chapter01;

/**
 * @author cguisheng
 * @className: thread01.java
 * @description:
 * @date 2019/8/29 21:57
 * @Copyright: TODO, Ltd. All rights reserved.
 * 注意：本内容仅限于TODO公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Thread01 {

    public static void main(String[] args) {
        // 是否守护线程
        boolean isDaemon = args.length == 0;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 获取当前线程对象
                Thread t1 = Thread.currentThread();
                while (true) {
                    System.out.printf("%s is %s alive and in %s state%n",
                            t1.getName(), t1.isAlive() ? "" : "not",
                            t1.getState());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };


        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable);
        if (isDaemon) {
            thread1.setDaemon(true);
            thread2.setDaemon(true);
        }
        System.out.printf("%s is %s alive and in %s state%n",
                thread1.getName(), thread1.isAlive() ? "" : "not",
                thread1.getState());

        System.out.printf("%s is %s alive and in %s state%n",
                thread2.getName(), thread2.isAlive() ? "" : "not",
                thread2.getState());

        thread1.start();
        thread2.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
