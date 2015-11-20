package com.github.daneko.android.plain;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final RuntimeException cause = new RuntimeException("cause");
                WeatherService.currentTokyoWeather()
                        .subscribeOn(Schedulers.newThread())
                        .map(new Func1<WeatherResponse.Weather, Snackbar>() {
                            @Override
                            public Snackbar call(WeatherResponse.Weather weather) {
                                return Snackbar
                                        .make(view, weather.getMain(), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<Snackbar, Observable<Snackbar>>() {
                            @Override
                            public Observable<Snackbar> call(Snackbar snackbar) {
                                return Observable.error(new RuntimeException("test", cause));
                            }
                        })
                        .subscribe(new Action1<Snackbar>() {
                                       @Override
                                       public void call(Snackbar bar) {
                                           bar.show();
                                       }
                                   },
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        Log.w("test", throwable);
                                    }
                                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
