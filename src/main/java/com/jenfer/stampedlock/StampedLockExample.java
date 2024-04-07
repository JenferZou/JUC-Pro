package com.jenfer.stampedlock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private double x = 0, y = 0;
    private final StampedLock lock = new StampedLock();

    // 写方法
    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp); // 释放写锁
        }
    }

    // 读方法
    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead(); // 乐观读锁
        double currentX = x, currentY = y;
        if (!lock.validate(stamp)) { // 验证乐观读锁是否有效
            stamp = lock.readLock(); // 获取悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 示例方法
    public static void main(String[] args) {
        StampedLockExample point = new StampedLockExample();

        // 写操作
        point.move(3, 4);

        // 读操作
        System.out.println("Distance from origin: " + point.distanceFromOrigin());
    }
}
