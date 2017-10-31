package com.studentportal.memento;

import java.util.ArrayList;

// 备忘录
public class MementoConcrete implements Memento {
    private ArrayList <String> Message_in_world;

    public MementoConcrete (ArrayList <String> Message_in_world ){
        super();
        this.Message_in_world = Message_in_world;

    }

    public ArrayList <String> getMessage_in_world() {
        return Message_in_world;
    }

    public void setMessage_in_world(ArrayList <String> message_in_world) {
        Message_in_world = message_in_world;
    }
}
