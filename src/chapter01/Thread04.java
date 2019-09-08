package chapter01;

/**
 * @author caogs
 * @className: thead04.java
 * @description:
 * @date 2019/9/1 14:29
 * @Copyright: TODO, Ltd. All rights reserved.
 * 注意：本内容仅限于TODO公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Thread04 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            while (!Thread.interrupted()) {
                System.out.println(name);
            }
        };

//        Thread.State.TERMINATED

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
        thread2.interrupt();

    }
}
