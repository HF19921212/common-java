package com.city.aqs;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 同步器 有序运行
 * 公平锁 排它锁 互斥锁
 * 三板斧：1.自旋 2.CAS 3.LockSupport
 */
public class CityBrainLock {

    //加锁的公共资源
    private volatile int state = 0;

    //保存持有锁的线程
    private Thread lockHolder;

    //线程安全的,不能用阻塞队列
    private final ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    //尝试获取锁
    private boolean aquire(){
        int s = getState();
        Thread current = Thread.currentThread();
        //未加锁
        if(s == 0){
            if((waiters.size() == 0 || current == waiters.peek())
                    && compareAndSwapState(0,1)){
                //加锁成功,设置当前线程持有锁
                setLockHolder(current);
                return true;
            }
        }
        return false;
    }

    public void lock(){
        //加锁成功
        if(aquire()){
            return;
        }

        //加锁失败的线程放入队列等待
        ConcurrentLinkedQueue waiter = this.waiters;
        Thread current = Thread.currentThread();
        waiter.add(current);
        //锁自旋
        for(;;){
            //空跑是比较浪费CPU资源的,所以需要尝试去抢占这把锁
            //waiter.peek() 不会移除队头,只取第一个element
            if(waiter.peek() == current && aquire()){
                //把队头移除掉 park结束,T2被移除
                waiter.poll();
                return;
            }
            //T2 阻塞当前线程,等待T1 unlock唤醒 T2
            LockSupport.park();
        }

    }

    public void unlock(){
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("lockHolder is not current");
        }
        int state =getState();
        if(compareAndSwapState(1,0)){
            //设置持有锁的线程为空
            setLockHolder(null);
            //取到队首
            Thread current = waiters.peek();
            if(current != null){
                //T1 释放锁,唤醒T2, T2线程park结束
                LockSupport.unpark(current);
            }
        }
    }

    /**
     * CAS 操作
     * @param except 预期值
     * @param update 修改值
     * @return
     */
    public final boolean compareAndSwapState(int except,int update){
        return unsafe.compareAndSwapInt(this,valueOffset,except,update);
    }

    //使用反射获取魔术类实例
    private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
    private static final long valueOffset;

    /**
     * valueOffset 偏移量 整形值
     * 通过起始位置寻址12格位置,拿到这个值在内存中的一块位置
     */
    static {
        try {
            //获取偏移量,实际内存中state的位置
            valueOffset = unsafe.objectFieldOffset
                    (CityBrainLock.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
    }


    public int getState() {
        return state;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }
}
