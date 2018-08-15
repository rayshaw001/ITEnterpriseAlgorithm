package com.rayshaw;
/**
 * get window max value
 */
import java.util.LinkedList;

public class windowMax{
    public static void main(String args[]){
        int [] arr = {4,3,5,4,3,3,6,7};
        windowMax wm = new windowMax();
        for(int i:wm.getWM(arr, 3)){
            System.out.println(i);
        }

    }
    public int[] getWM(int[] array,int window){
        if(window<=0 ||array==null|| array.length<window){
            return null;
        }
        int []res = new int[array.length-window+1]; 
        int max = array[0];
        int i = 0;
        while(i<window){
            max = max>array[i]?max:array[i];
            i++;
        }
        res[0]=max;
        for(;i<array.length;i++){
            max = max>array[i]?max:array[i];
            res[i-window+1]=max;
        }
        return res;
    }
}