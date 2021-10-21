package KitchenAPI;

import java.util.concurrent.locks.ReentrantLock;

public class Cooking_Apparatus {
    private ReentrantLock lock = new ReentrantLock(true);

    public boolean tryLock(){
        return lock.tryLock();
    }

    public void unLock(){
        lock.unlock();
    }

    public void setLock(){
        lock.lock();
    }
}
