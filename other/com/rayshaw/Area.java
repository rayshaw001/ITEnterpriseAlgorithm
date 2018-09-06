package com.rayshaw;
import java.util.Scanner;

public class Area{
    public Area(){
    }

    public static void main(String args[]){
        int n ,k,t;
        int array[];
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        k=sc.nextInt();
        t=sc.nextInt();
        array=new int[n];
        for(int i=0;i<n;i++){
            array[i]=sc.nextInt();
        }
        sc.close();
        System.out.println(new Area().calculate(array,k,t));
    }

    public int calculate(int array[],int k,int t){
        int times=0;
        for(int l=1;k+l-1<array.length;l++){
            times+=Search(array, 0, k-1)>k?1:0;
        }
        return times;
    }
    public int Search(int a[],int start,int end){
        int max=0;//保持到目前为此出现次数最多的那个数
        int count=1;
        int maxnum=count;//保存最大计数次
        int maxd =0;
        for(int i=start;i<end;i++){
            max=a[i];
            if(a[i+1]==max)
                count++;
            else
                count=1;
            if(count>maxnum){
                maxnum=count;
                maxd=max;
            }
        }
        return maxnum;
    }
}

