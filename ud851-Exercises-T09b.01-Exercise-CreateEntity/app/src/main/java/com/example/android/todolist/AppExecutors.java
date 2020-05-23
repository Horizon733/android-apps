package com.example.android.todolist;

import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;
import java.util.logging.LogRecord;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread){
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }
    public static AppExecutors getInstance(){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }
    public Executor diskIO(){return diskIO; }
    public Executor getNetworkIO(){ return networkIO; }
    public Executor getMainThread(){ return mainThread; }


    public static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
