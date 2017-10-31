package com.studentportal.memento;

import java.util.ArrayList;

public class Originator{

    private ArrayList <String> message_in_word ;

    public Originator() {
        super();
    }

    //Create memento and return a memento obj 备忘操作，并且返回备忘录对象
    public Memento createMemento(ArrayList <String> message_in_word){
        return new MementoConcrete(message_in_word);
    }
    //recovery the data to the value in memento 进行数据恢复，数据恢复成指定的备忘录的值
    public String Undo(Memento m) {
        return ((MementoConcrete)m).getMessage_in_world().toString();
    }

}
