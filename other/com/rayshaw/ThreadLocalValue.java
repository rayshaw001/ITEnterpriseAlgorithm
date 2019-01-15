
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import sun.reflect.Reflection;

public class ThreadLocalValueUtil {
    
    public static void main(String args[]){
        Thread t = new TestReflection();
        t.start();
        try{
            Thread.sleep(1000);
            java.lang.reflect.Field f = TestReflection.class.getDeclaredField("tl");
            f.setAccessible(true);
            ThreadLocal tl = (ThreadLocal)f.get(t);
            Method m = ThreadLocal.class.getDeclaredMethod("getMap",Thread.class);
            System.out.println(tl);
            m.setAccessible(true);
            Object threadLocalMap = m.invoke(tl,t);
            Class threadLocalMapClazz = threadLocalMap.getClass();
            Method getEntry = threadLocalMapClazz.getDeclaredMethod("getEntry", ThreadLocal.class);
            getEntry.setAccessible(true);
            Object threadLocalMapEntry = getEntry.invoke(threadLocalMap,tl);
            Class threadLocalMapEntryClass = threadLocalMapEntry.getClass();
            Field threadlocalValue = threadLocalMapEntryClass.getDeclaredField("value");
            threadlocalValue.setAccessible(true);
            System.out.println(threadlocalValue.get(threadLocalMapEntry));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


class TestReflection extends Thread{
    ThreadLocal tl = new ThreadLocal<>();
    @Override
    public void run(){
        tl.set(10);
        try{
            System.out.println("threadlocal:"+tl);
            System.out.println("threadlocal value:"+tl.get());
            Thread.sleep(3000);       
        }catch(Exception e){

        }
    }
}
