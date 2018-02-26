package com.example.rumi.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {
    String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> integerObservable=new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(1);
                observer.onNext(2);
                observer.onNext(3);
                observer.onNext(4);
            }
        };

        final Observable<Integer> integerObservable1=Observable.just(1,2,3,4,5);

        integerObservable1.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
//                Log.e(TAG, "apply: map "+integer );
                return integer * 2;
            }
        }).filter(x->x>5)
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "onNext:map "+value );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//        integerObservable1.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.e(TAG, "onSubscribe: "+d );
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.e(TAG, "onNext: "+value );
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


        integerObservable1.flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                return Observable.just(integer * 3, integer * 5);
            }
        })
                .doOnNext(x->{
//                    System.out.println("inside"  +x);
                })
                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Log.e(TAG, "onNext: flatmap "+value );

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



    }
}
