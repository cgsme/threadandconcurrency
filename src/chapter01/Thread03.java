package chapter01;

import java.math.BigDecimal;

/**
 * @author cguisheng
 * @className: Thread03.java
 * @description:
 * @date 2019/8/31 22:31
 * @Copyright: TODO, Ltd. All rights reserved.
 * 注意：本内容仅限于TODO公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Thread03 {

    private static final BigDecimal FOUR = BigDecimal.valueOf(4);

    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    private static BigDecimal result;

    public static void main(String[] args) {

        Runnable r = () -> {
            System.out.println("计算线程执行计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = computePi(5000);
            System.out.println("计算线程计算完成");
        };
        Thread thread = new Thread(r);
        // 设置为守护线程，跟随父线程一起销毁。
//        thread.setDaemon(true);
        thread.start();

        System.out.println("计算线程启动");

        try {
            // 主线程等待计算线程执行结束
            thread.join();
//            thread.join(100);
            System.out.println("计算线程结束");
        } catch (InterruptedException e) {
            // 异常永远不会进来，因为thread没有调用interrupt()方法
            e.printStackTrace();
        }
        System.out.println("结果：" + result);
    }

    /**
     * 计算pi小数点后digits位
     *
     * @param digits
     * @return
     */
    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(5, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);

        return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);

    }

    public static BigDecimal arctan(int inverseX, int scale) {
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 = BigDecimal.valueOf(inverseX * inverseX);
        numer = BigDecimal.ONE.divide(invX, scale, ROUNDING_MODE);
        result = numer;
        int i = 1;
        do {
            numer = numer.divide(invX2, scale, ROUNDING_MODE);
            int denom = 2 * i + 1;
            term = numer.divide(BigDecimal.valueOf(denom), scale, ROUNDING_MODE);
            if ((i % 2 != 0)) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }
}
