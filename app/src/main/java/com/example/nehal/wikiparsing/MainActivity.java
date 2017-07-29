package com.example.nehal.wikiparsing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nehal.wikiparsing.Model.RecyclerItemClickListener;
import com.example.nehal.wikiparsing.Model.WikiPage;
import com.example.nehal.wikiparsing.Model.WikiResponse;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable _disposables;
    private Api api;
    private RecyclerView rView;
    private WikiAdapter wikiAdapter;
    private ArrayList<WikiPage> pagesList = new ArrayList<>();
    private EditText searchText;
    private Disposable _disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        _disposables = new CompositeDisposable();
        api = AppUtils.getWikiRetrofitApi(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        wikiAdapter = new WikiAdapter(this,pagesList);
        rView.setAdapter(wikiAdapter);
        _disposable = RxTextView.textChangeEvents(searchText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(changes -> !TextUtils.isEmpty(changes.text().toString()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(_getSearchObserver());

        rView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("pageTitle",pagesList.get(position).getTitle() != null ? pagesList.get(position).getTitle():"");
                startActivity(intent);
            }
        }));

    }
    private void fetchDetailsFromAPI() {
        _disposables.add(
                api.getWikiResponse("query","json","2","pageimages|pageterms","prefixsearch","1","thumbnail","50","10","description",searchText.getText().toString(),"10","url")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<WikiResponse>() {
                            @Override
                            public void onNext(WikiResponse wikiResponse) {
                                Log.i("Response","Success");
                                pagesList = (ArrayList)wikiResponse.getQuery().getPages();
                                wikiAdapter = new WikiAdapter(MainActivity.this,pagesList);
                                rView.setAdapter(wikiAdapter);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }
    private void initViews() {
        rView = (RecyclerView)findViewById(R.id.recyclerView);
        searchText = (EditText)findViewById(R.id.searchText);
    }

    private DisposableObserver<TextViewTextChangeEvent> _getSearchObserver() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                fetchDetailsFromAPI();

            }
        };
    }
}
