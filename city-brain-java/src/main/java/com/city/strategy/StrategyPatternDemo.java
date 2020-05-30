package com.city.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("参数累加："+context.executeStrategy(1,2));

        context = new Context(new OperationSubtract());
        System.out.println("参数相减："+context.executeStrategy(2,1));
    }
}
