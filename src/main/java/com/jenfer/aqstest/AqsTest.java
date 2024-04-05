package com.jenfer.aqstest;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
class CustomLock {
    // 继承 AbstractQueuedSynchronizer 来实现自定义的同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 当状态为 1 时，锁被持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 尝试获取锁
        @Override
        protected boolean tryAcquire(int ignore) {
            return compareAndSetState(0, 1); // 尝试将状态从0设置为1，表示成功获取锁
        }

        // 尝试释放锁
        @Override
        protected boolean tryRelease(int ignore) {
            setState(0); // 直接将状态设置为0，表示释放锁
            return true;
        }
    }

    private final Sync sync = new Sync();

    // 获取锁
    public void lock() {
        sync.acquire(1);
    }

    // 释放锁
    public void unlock() {
        sync.release(1);
    }

    // 查询当前线程是否持有锁
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
public class AqsTest {
        // 继承 AbstractQueuedSynchronizer 来实现自定义的同步器
        public static void main(String[] args) {
            CustomLock lock = new CustomLock();

            // 创建一个线程来演示锁的获取和释放
            Thread t1 = new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " acquired the lock.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " released the lock.");
                }
            });

            // 创建另一个线程来演示锁的竞争
            Thread t2 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is trying to acquire the lock.");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " acquired the lock.");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock.");
            });

            t1.start();
            try {
                Thread.sleep(1000); // 等待一会，让 t1 先启动并获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.start();
        }


}

