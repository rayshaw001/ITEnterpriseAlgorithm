package com.rayshaw;

import java.util.Stack;

public class StacksQueue{
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;
    public StacksQueue(){
        stackPop = new Stack<Integer>();
        stackPush = new Stack<Integer>();
    }
    public void add(Integer e){
        stackPush.add(e);
    }
    public Integer poll(){
        if(stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackPop.empty()){
            while(!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }
    public Integer peek(){
        if(stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackPop.empty()){
            while(!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

}