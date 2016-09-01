package com.mrchandler.rxbugtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InterruptedIOException;
import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainActivity extends AppCompatActivity {

    private static final int IO = 0;
    private static final int EXECUTOR = 1;
    private static SuperModelController controller = new SuperModelController(new SuperModel());
    private CompositeDisposable disposables;
    private Button ioButton;
    private Button executorButton;
    private Button resetButton;
    private boolean loading;
    private int loadSource = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            loading = savedInstanceState.getBoolean("loading", false);
            loadSource = savedInstanceState.getInt("loadSource");
        }
        ioButton = (Button) findViewById(R.id.io);
        ioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorButton.setEnabled(false);
                ioButton.setEnabled(false);
                loadSource = IO;
                subscribe(Schedulers.io());
            }
        });
        executorButton = (Button) findViewById(R.id.executor);
        executorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorButton.setEnabled(false);
                ioButton.setEnabled(false);
                loadSource = EXECUTOR;
                subscribe(Schedulers.from(Executors.newSingleThreadExecutor()));
            }
        });
        resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.reset();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposables = new CompositeDisposable();
        if (loading) {
            switch (loadSource) {
                case IO:
                    subscribe(Schedulers.io());
                    break;
                default:
                    subscribe(Schedulers.from(Executors.newSingleThreadExecutor()));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("loading", loading);
        outState.putInt("loadSource", loadSource);
    }

    private void subscribe(Scheduler scheduler) {
        loading = true;
        disposables.add(controller.getFlowable()
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<String>>() {
                    @Override
                    public void onNext(List<String> strings) {
                        Toast.makeText(MainActivity.this, "Got some items.", Toast.LENGTH_SHORT).show();
                        ioButton.setEnabled(true);
                        executorButton.setEnabled(true);

                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace(System.err);
                        if (t instanceof InterruptedIOException) {
                            Toast.makeText(MainActivity.this, "Got an Interrupted Exception!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Got an error!", Toast.LENGTH_SHORT).show();
                        }
                        ioButton.setEnabled(true);
                        executorButton.setEnabled(true);
                        resetButton.callOnClick();
                    }

                    @Override
                    public void onComplete() {
                        resetButton.callOnClick();
                    }
                }));
    }
}
