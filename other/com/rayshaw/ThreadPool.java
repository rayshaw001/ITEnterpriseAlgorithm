import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;

public class ThreadPool<Job extends Runnable>{
    List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    List<Job> jobs = new LinkedList<Job>();

    public void execute(Job job){
        if (job != null) {
            synchronized (jobs) {
                jobs.add(0,job);
                jobs.notify();
            }
        }
    }

    public void initializeWorker(int number){
        for(int i=0;i<number;i++){
            Worker worker =new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    class Worker implements Runnable{
        private volatile boolean running = true; 
        @Override
        public void run(){
            while(running){
                Job job = null;
                synchronized(jobs){
                    while(jobs.isEmpty()){
                        try{
                            jobs.wait();
                        } catch(InterruptedException ie){
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.remove(jobs.size()-1);
                }
                if(job != null){
                    try{
                        job.run();
                    } catch(Exception ex){
    
                    }
                }
            }
        }
        public void shutdown(){
            running = false;
        }
    
    }


    public static void main(String args[]){
        
        LinkedHashMap map = new LinkedHashMap();
        ThreadPool tp = new ThreadPool<>();
        tp.initializeWorker(5);
        for(int i=0;i<10000;i++){
            tp.execute(new Runnable(){
                @Override
                public void run(){
                    try{
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
                    } catch(Exception e){

                    }
                }

            });
        }
    }
}
interface Job extends Runnable{
}

