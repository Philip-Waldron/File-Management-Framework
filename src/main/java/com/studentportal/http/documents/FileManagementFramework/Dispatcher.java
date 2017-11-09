package com.studentportal.http.documents.FileManagementFramework;

import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<Interceptor> interceptors;

    Dispatcher() {
        interceptors = new ArrayList<>();
    }

    public void register(Interceptor i) {
        interceptors.add(i);
    }

    public void remove(Interceptor i) {
        interceptors.remove(i);
    }

    protected void dispatch(Context context) {
        for(Interceptor i : interceptors)
            i.notify(context);
    }
}
