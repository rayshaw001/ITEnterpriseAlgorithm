package com.rayshaw;

/**
 * 猫狗队列
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.rayshaw.catAndDog.*;

public class catAndDogQueue{
    List<Pet> pet;
    int cat = 0;
    int dog = 0;
    catAndDogQueue(){
        List pet = new LinkedList<Pet>();
    }
    public void add(Pet p){
        if(p.getType().equals("dog")){
            dog++;
        }else{
            cat++;
        }
        pet.add(p);
    };
    public boolean isEmpty(){
        return cat+dog > 0;
    }
    public boolean isDogEmpty(){
        return dog>0;
    }
    public boolean isCatEmpty(){
        return cat>0;
    }
    public Pet pollAll(){
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        Pet p = pet.get(cat+dog-1);
        if(p.getType().equals("dog")){
            dog--;
        }else{
            cat--;
        }
        pet.remove(cat+dog-1);
        return p;
    }
    public Cat pollCat(){
        if(isCatEmpty()){
            throw new RuntimeException("queue is empty");
        }
        Pet p;
        for(int i = pet.size()-1;i>=0;i--){
            p = pet.get(i);
            if(p.getType().equals("cat")){
                pet.remove(i);
                cat--;
                return (Cat)p;
            }
        }
    }
    public Dog pollDog(){
        if(isCatEmpty()){
            throw new RuntimeException("queue is empty");
        }
        Pet p;
        for(int i = pet.size()-1;i>=0;i--){
            p = pet.get(i);
            if(p.getType().equals("dog")){
                pet.remove(i);
                dog--;
                return (Dog)p;
            }
        }
    }
}