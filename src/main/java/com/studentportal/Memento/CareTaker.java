package com.studentportal.Memento;

import java.util.Stack;

public class CareTaker {

    private Stack<MementoConcrete> stack_memento ;

    public  CareTaker(){
        super();
        stack_memento=new Stack<MementoConcrete>();
    }

    public  MementoConcrete getLastMemento() {
        if(stack_memento.isEmpty()){
            return null;
        }
        else
            return stack_memento.pop();
    }

    public void saveMemento(MementoConcrete memento){
         System.err.println("save pos :"+ memento.toString());
         stack_memento.push(memento);
    }

}
