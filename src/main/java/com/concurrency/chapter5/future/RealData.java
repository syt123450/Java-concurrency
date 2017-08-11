package com.concurrency.chapter5.future;

/**
 * Created by ss on 2017/8/9.
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String para) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result = sb.toString();
    }

    @Override
    public String getResult() {

        return result;
    }

    public static void main(String[] args) {

//        //构造方法是阻塞的
//        RealData realData = new RealData("123");
//        //一个类的方法只有在构造方法执行完成之后才会被调用
//        System.out.println(realData.getResult());

        //就算没有返回值，构造方法也是阻塞的
        new RealData("123");
        System.out.println("123");
    }

}
