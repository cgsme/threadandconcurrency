package chapter01;

/**
 * @author cguisheng
 * @className: thread02.java
 * @description:
 * @date 2019/8/31 15:16
 * @Copyright: TODO, Ltd. All rights reserved.
 * 注意：本内容仅限于TODO公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Thread02 {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (!Thread.interrupted()) {
                    System.out.println(name + ": " + count++);
                }
                System.out.println("######################");
            }
        };

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        while (true) {
            double n = Math.random();
            if (n >= 0.49999999 && n <= 0.50000001) {
                break;
            }

        }

        thread1.interrupt();
        thread2.interrupt();
        System.out.println(thread1.isInterrupted());
        try {
            thread1.join();
        System.out.println(thread1.isInterrupted());
        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
