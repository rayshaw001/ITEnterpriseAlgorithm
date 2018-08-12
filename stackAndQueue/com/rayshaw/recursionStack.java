package com.rayshaw;

/**
 * 仅用递归以及堆操作实现堆的转置
 */

import java.util.Stack;

public class recursionStack{
    public recursionStack(){

    }
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int last = stack.pop();
        if(stack.empty()){
            return last;
        }else{
            int tmp = getAndRemoveLastElement(stack);
            stack.push(last);
            return tmp;
        }
    }
    public static void reverse(Stack<Integer> stack){
        if(stack.empty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String args[]){
        int []array = {1,2,3,4,5,6,7,8,9};
        Stack<Integer> stack = new Stack<Integer>();
        for(Integer i:array){
            stack.push(i);
        }
        while(!stack.empty()){
            System.out.println(getAndRemoveLastElement(stack));
        }
        for(Integer i:array){
            stack.push(i);
        }
        reverse(stack);
        while(!stack.empty()){
            System.out.println(stack.pop());
        }

    }
}