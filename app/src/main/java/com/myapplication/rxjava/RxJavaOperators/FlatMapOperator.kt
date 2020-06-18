package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {

    var flatMapOperator = FlatMapOperator();
    flatMapOperator.setUser()
    flatMapOperator.setCompanyDetail()
    flatMapOperator.getCompanyDetail()

}
class FlatMapOperator {
    var empList :ArrayList<Employee> = ArrayList()
    var companyList :ArrayList<CompanyDetail> = ArrayList()
    fun setUser(){
        for(i in 1..10){
            empList.add(Employee( i , "Vishnu $i"))
        }
    }

    fun setCompanyDetail(){
        for(i in 1..10){
            companyList.add(CompanyDetail(i,"Vishnu Pvt. Ltd. $i", "Jaipur","Software Development"))
        }
    }

//    **************************** Get Employee Company Detail Start**********
    fun getCompanyDetail(){
        Observable.just(empList)
                .flatMap {
                    Observable.fromIterable(it)
                }
                .flatMap {
                    Observable.just(getComDetails(it.emp_id))
                }
                .takeWhile(){it.emp_id<6}
                .subscribe(object :Observer<CompanyDetail?>{
                    override fun onComplete() {
                        System.out.println("Completed")
                    }

                    override fun onSubscribe(d: Disposable) {
                        System.out.println("Subscribed")
                    }

                    override fun onNext(t: CompanyDetail) {
                        System.out.println(t)
                    }

                    override fun onError(e: Throwable) {
                        System.out.println("OnError")
                    }
                })
    }

    private fun getComDetails(empId: Int): CompanyDetail? {
        var detail:CompanyDetail?=null
        var first =0;
        var last = companyList.size-1
        var mid = (first+last)/2;
        while (first<=last){
            if(empId == companyList.get(mid).emp_id){
                detail = companyList.get(mid)
                break
            }
            if(empId < companyList.get(mid).emp_id ){
                last = mid -1;
            }else {
                first = mid +1;
            }
            mid = (first+last)/2
        }
        return detail
    }

    private fun searchComDetail(){

    }

//    **************************** Get Employee Company Detail End**********

}


class Employee (
    var emp_id:Int,
    var name:String
)

data class CompanyDetail (
    var emp_id :Int,
    var name:String,
    var address:String,
    var department:String?

)