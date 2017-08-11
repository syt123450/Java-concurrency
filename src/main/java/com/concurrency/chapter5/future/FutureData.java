package com.concurrency.chapter5.future;

/**
 * Created by ss on 2017/8/9.
 */
public class FutureData implements Data {

    private RealData realData = null;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {

        if (isReady) {
            return;
        }

        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {

        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData.getResult();
    }
}
