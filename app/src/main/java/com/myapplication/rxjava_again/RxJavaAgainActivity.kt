package com.myapplication.rxjava_again

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.data.Data
import com.data.EmployeeData
import com.data.TreeModel
import com.google.gson.Gson
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.jakewharton.rxbinding3.widget.textChanges
import com.myapplication.R
import com.myapplication.rxjava_retrofit.network.RetrofiGenerator
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import kotlinx.android.synthetic.main.activity_retrofit_again.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.random.Random


class RxJavaAgainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_again)
        btn.setOnClickListener {
//            testFlatMap()
//            getEmployeesUsingFlatMap()
//            concatList()
//            testSwitchMap1()

        }
//        ed_search.addTextChangedListener {
            useSwitchMapSearchText()
//        }
    }


//    Flat Map Start

    fun testFlatMap(){
        var actualOutput= ArrayList<String>()
        var scheduler = TestScheduler()
        var keywordsToSearch = arrayListOf<String>("b", "bo", "boo", "book", "books")

        Observable.fromIterable(keywordsToSearch)
                .flatMap {
                    return@flatMap Observable.just(it+"FirstResult")
                }
                .delay(10,TimeUnit.SECONDS,scheduler)
                .toList()
                .doOnSuccess {
                     actualOutput.addAll(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        scheduler.advanceTimeBy(1,TimeUnit.MINUTES)

        println(actualOutput)
        tv_data.setText(actualOutput.toString())
    }

//    Flat Map End
//    Switch Map Start

    fun testSwitchMap1(){
        var race = arrayListOf<String>("b", "bo", "boo", "book", "books")
        Observable.fromIterable(race)
                .switchMap {
                    val delay = Random.nextLong(2)
                    return@switchMap Observable.just(it)
                            .map{
                                val c: Calendar = Calendar.getInstance()

                                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:sss")
                                val formattedDate: String = df.format(c.getTime())
                                println(formattedDate + it + "Result")
                                it+" Result"
                            }
                            .delay(delay, TimeUnit.SECONDS,Schedulers.io())
                }
                .toList()
                .doOnSuccess {
                    val c: Calendar = Calendar.getInstance()

                    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:sss")
                    val formattedDate: String = df.format(c.getTime())
                    println(formattedDate + it + "Result")
                    println(it)
                    tv_data.setText(it.toString())
                }
                .subscribe()


    }

    fun testSwitchMap(){
        var actualOutput= ArrayList<String>()
        var tempOutput= ArrayList<String>()
        var scheduler = TestScheduler()
        var keywordsToSearch = arrayListOf<String>("b", "bo", "boo", "book", "books")

        Observable.fromIterable(keywordsToSearch)
                .switchMap {
                    return@switchMap Observable.just(it+" Result")
                }
//                .flatMap {
//                    return@flatMap Observable.just(it+" Result")
//                }
                .delay(10,TimeUnit.SECONDS,scheduler)

                .doOnNext() {
                     actualOutput.add(it)
                    println(actualOutput)
                }
                .toList()
                .doOnSuccess {
                    tempOutput.addAll(it)
                    println("temp Output ::"+tempOutput)

                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        scheduler.advanceTimeBy(1,TimeUnit.MINUTES)

        println(actualOutput)
        tv_data.setText(actualOutput.toString())
    }

//    Flat Switch End
//************Flat Map APi Start /
    private fun getEmployeesUsingFlatMap() {
        var apis = RetrofiGenerator.getRequestApi()
        apis.getAllEmployeesRxJava().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(object : Function<Response<EmployeeData>, ObservableSource<Data>?> {
                    override fun apply(response: Response<EmployeeData>): ObservableSource<Data>? {
                        if (response.isSuccessful) {
                            var empData = response.body()?.data?.get(1)

                            return Observable.fromIterable(response.body()!!.data)
                                    .subscribeOn(Schedulers.io())
//                            return getEmpDetail(empData)
                        }
                        return null
                    }
                })
                .flatMap(object : Function<Data, ObservableSource<Response<Data>>?> {
                    override fun apply(t: Data): ObservableSource<Response<Data>>? {
                        return getEmpDetail(t)
                    }
                })

                .subscribeWith(object : Observer<Response<Data>> {
                    override fun onComplete() {
                        println("OnComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        println("OnSubscribed")
                    }

                    override fun onNext(t: Response<Data>) {
                        println("OnNext")
                        println(t.toString())
//                        tv_data.setText(t.toString())
                    }

                    override fun onError(e: Throwable) {
                        println("OnError ${e.message}")
//                        tv_data.setText(e.message.toString())
                    }
                })
    }

    private fun getEmpDetail(empData: Data?): ObservableSource<Response<Data>>? {
        var apis = RetrofiGenerator.getRequestApi()
        var employee = Data("${empData!!.id}", "Vishnu ${empData!!.id}", "500000", empData!!.id + "", "")
        var gson = Gson()
        var json = gson.toJson(employee)
        println("JSON****************${json.toString()}")
        var requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        return apis.updateEmployee(empData!!.id, requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

//***********Flat Map APi End /

    //*********** Map Start

    fun getEmployeesUsingMap() {

        var apis = RetrofiGenerator.getRequestApi()

        apis.getAllEmployeesRxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(object : Function<Response<EmployeeData>, List<Data>?> {
                    override fun apply(response: Response<EmployeeData>): List<Data>? {
                        return response.body()?.data
                    }
                })
                .subscribe(object : Observer<List<Data>?> {
                    override fun onComplete() {
                        println("OnComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        println("onSubscribe")
                    }

                    override fun onNext(response: List<Data>) {
                        println("OnNext")
                        println(response.toString())
                        tv_data.setText(response.toString())
                    }

                    override fun onError(e: Throwable) {
                        println(e.message)
                    }
                })

    }

//*********** Map Start


    //***********Concat Map Start
    var list1 = arrayOf<Any>(5, 6, 32, 12, 54, 78, 21, 28)
    var list2 = arrayOf<Any>("A", "B", "C", "d", "E")

    fun getObservable(list: Array<Any>): Observable<Any> {
        return Observable.create<Any> {
            MainScope().launch {
                for (i in list) {
                    it.onNext(i.toString())
                    delay(1000)
                }
                it.onComplete()
            }
        }
    }


    fun concatList() {
        Observable.concat(getObservable(list1), getObservable(list2)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Any> {
                    override fun onNext(t: Any) {
                        println("OnNext  ${t.toString()}")
                    }

                    override fun onComplete() {
                        println("onComplete  ")
                    }

                    override fun onSubscribe(d: Disposable) {
                        println("onSubscribe  ")
                    }

                    override fun onError(e: Throwable) {
                        println("onError  ")
                    }
                })
    }


//***********Concat Map End


//***********Switch Map Start

    var iapi = RetrofiGenerator.getRequestApi()

    fun useSwitchMapSearchText(){
         ed_search.textChangeEvents()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.start
                    it.text.toString()
                }
                .debounce(400L,TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap  (object : Function<String,Observable<TreeModel>?>{
                    override fun apply(t: String): Observable<TreeModel> {
                        println("bbbbbb ${t}")
                        return iapi.searchApi(t, 5)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .delay(2,TimeUnit.SECONDS,Schedulers.io())


                    }
                })
                 .subscribe(
                         object :Observer<TreeModel>{
                             override fun onComplete() {

                             }

                             override fun onSubscribe(d: Disposable) {
                             }

                             override fun onNext(it: TreeModel) {

                                 println("aaaaaaa ${it}")
                                 tv_data.setText(it.toString())
                             }

                             override fun onError(e: Throwable) {
                             }
                         }
                 )
    }

    fun useSwitchMap(search: String) {
        MainScope().launch {
            delay(3000)

            iapi.searchApi(search, 5)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .debounce(400, TimeUnit.MILLISECONDS)
//                .switchMap {  }
                    .switchMap(object : Function<TreeModel, ObservableSource<TreeModel>> {
                        override fun apply(t: TreeModel): ObservableSource<TreeModel> {
//                            val delay: Long = Random.nextLong(10)
                            return Observable.just(t)
                                    .delay(2, TimeUnit.SECONDS, Schedulers.io())
                        }
                    })
                    .toList()
                    .doOnSuccess {

                        println("aaaaaaa ${it}")
                        tv_data.setText(it.toString())
                    }
                    .subscribe()


        }
    }
    fun EditText.addTextWatcher(): Flowable<EditTextFlow> {
        return Flowable.create<EditTextFlow>({ emitter ->
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.BEFORE))
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.ON))
                }

                override fun afterTextChanged(p0: Editable?) {
                    emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.AFTER))
                }
            })
        }, BackpressureStrategy.BUFFER)
    }

//***********Switch Map Api End
//***********Switch Map  Start



//***********Switch Map  End

}
