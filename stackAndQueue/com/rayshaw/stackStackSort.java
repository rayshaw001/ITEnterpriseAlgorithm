package com.rayshaw;

import java.util.Stack;

/**
 * sort a stack with another stack
 */

public class stackStackSort{
    public void stackSort(Stack<Integer> stack){
        Stack<Integer> tmp = new Stack<Integer>();
        while(!stack.empty()){
            int tmpVal = stack.pop();
            while(!tmp.empty()&&tmpVal>tmp.peek()){
                stack.push(tmp.pop());
            }
            tmp.push(tmpVal);
        }
        while(!tmp.empty()){
            stack.push(tmp.pop());
        }
    }
}