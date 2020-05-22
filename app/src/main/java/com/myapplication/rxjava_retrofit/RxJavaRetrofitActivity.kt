package com.myapplication.rxjava_retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.CommentsDataModel
import com.data.PostDataModel
import com.myapplication.R
import com.myapplication.rxjava_retrofit.network.RetrofiGenerator
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random


class RxJavaRetrofitActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerAdapter
    lateinit var recyclerView: RecyclerView
    val TAG = "MainActivity"
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_retrofit)
        initRecyclerView();
        getPosts()
    }

    private fun getPosts() {
        getPostObservable()
                .subscribeOn(Schedulers.io())
                .flatMap ( object : Function<PostDataModel?,ObservableSource<PostDataModel>>{
                    override fun apply(post: PostDataModel): ObservableSource<PostDataModel> {
                        return getCommentObsevable(post)
                    }

                } )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PostDataModel>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable.add(d)
                    }

                    override fun onNext(post: PostDataModel) {
                        updatePost(post)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG,e.fillInStackTrace().toString())
                    }
                })
    }

    private fun updatePost(post: PostDataModel){

        Observable.fromIterable(adapter.getPosts()).
                filter(Predicate { it:PostDataModel-> return@Predicate it.id==post.id })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<PostDataModel>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                    }

                    override fun onNext(t: PostDataModel) {
                        adapter.updatePost(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG,"Error: ${e.fillInStackTrace()}")
                    }
                })
    }

    private fun getCommentObsevable(post: PostDataModel): Observable<PostDataModel> {
       return RetrofiGenerator.getRequestApi().getComments(post.id)
                .subscribeOn(Schedulers.io())
                .flatMap(object : Function<List<CommentsDataModel>?,ObservableSource<PostDataModel>>{
                    override fun apply(t: List<CommentsDataModel>): ObservableSource<PostDataModel> {
                        var delay:Long = (Random.nextInt(1,6)*1000L)
                        Thread.sleep(delay)
                        Log.d(TAG,"apply sleeping thread "+ Thread.currentThread())
                        post.comments = t
                        return Observable.just(post)
                                .subscribeOn(Schedulers.io())
                    }
                })

    }

    private fun initRecyclerView() {
        adapter = RecyclerAdapter()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    //    get Post Api Start
    fun getPostObservable(): Observable<PostDataModel> {
        var observable =
                RetrofiGenerator.getRequestApi()
                        .getPost()
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        /*.flatMap(object : Function<List<PostDataModel>?, ObservableSource<PostDataModel>> {
                            override fun apply(posts: List<PostDataModel>): ObservableSource<PostDataModel> {
                                adapter.setPosts(posts)
                                return Observable.fromIterable(posts)
                                        .subscribeOn(Schedulers.io())
                            }
                        })*/

                        .flatMap {
                            adapter.setPosts(it)
                        return@flatMap Observable.fromIterable(it)
                                .subscribeOn(Schedulers.io())}
        return observable
    }
//    get Post Api End

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}