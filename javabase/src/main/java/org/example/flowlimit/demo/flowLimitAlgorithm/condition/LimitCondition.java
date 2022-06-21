package org.example.flowlimit.demo.flowLimitAlgorithm.condition;


import org.example.flowlimit.demo.flowLimitAlgorithm.window.Window;

public abstract class LimitCondition {

    public synchronized boolean isPass(Window window) {
        boolean isPass = doCheckCondition(window);
        if (isPass) {
            window.addCount();
        }
        return isPass;
    }


    protected abstract boolean doCheckCondition(Window window);

}
