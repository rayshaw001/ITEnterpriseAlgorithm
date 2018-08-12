package com.rayshaw;

/**
 * 
 * 获取堆中最小元素，复杂度O(1)
 * 
 * */

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> mStack;

    MinStack(){
        stack = new Stack<Integer>();
        mStack = new Stack<Integer>();
    }

    public int getMinStack() {
        return mStack.peek();
    }

    public int push(int t) {
        mStack.push(mStack.empty()?t:t<mStack.peek()?t:mStack.peek());
        return stack.push(t);
    }

    public int pop() {
        mStack.pop();
        return stack.pop();
    }

    public boolean isintmpty() {
        return stack.empty();
    }

    public static void main(String args[]) {
        int[] array = { 10, 5, 6, 4, 5, 7, 3, 5, 3, 7, 9 };
        MinStack mStack = new MinStack();
        for (Integer i : array) {
            mStack.push(i);
        }
        while (!mStack.isintmpty()) {
            System.out.println(mStack.getMinStack() + ":" + mStack.pop());
        }
    }
}