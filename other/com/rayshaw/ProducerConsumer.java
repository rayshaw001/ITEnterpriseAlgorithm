import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer{
    public static void main(String args[]){
        Resource resource= new Resource(5);
        Producer p[] = new Producer[20];
        Consumer c[] = new Consumer[200];
        for(int i=0;i<20;i++){
            p[i] = new Producer(resource);
            p[i].start();
        }
        for(int i=0;i<200;i++){
            c[i] = new Consumer(resource);
            c[i].start();
        }
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            p[i].shutdown();
        }
        for (int i = 0; i < 200; i++) {
            c[i].shutdown();
        }

    }
}

class Producer extends Thread{
    Resource r;
    boolean signal = true;
    Producer(Resource p){
        r=p;
    }
    @Override
    public void run(){
        int i = 0;
        while(signal){
            r.add("Producer " + Thread.currentThread().toString() + "\t" + i++);
        }
    }
    public void shutdown(){
        signal=false;
    }
}

class Consumer extends Thread{
    Resource r;
    boolean signal = true;
    Consumer(Resource p){
        r=p;
    }
    @Override
    public void run(){
        while(signal){
            System.out.println("Consumer " + Thread.currentThread().toString() + "\t" + r.pop());
        }
    }
    public void shutdown(){
        signal=false;
    }
}

class Resource{
    private Queue<String> queue;
    private int capacity;
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition(); 
    public Resource(int capacity){
        queue = new ArrayDeque<String>(capacity);
        this.capacity = capacity;
    }
    public boolean add(String s){
        lock.lock();
        try{
            while(queue.size()==capacity){
                full.await();
            }
            queue.add(s);
            empty.signal();
            
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return true;
    }
    public String pop(){
        String result = null;
        lock.lock();
        try{
            while(queue.isEmpty()){
                empty.await();
            }
            result = queue.poll();
            full.signal();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return result;
    }
}