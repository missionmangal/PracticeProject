package com.myapplication.dagger.dagger_components

import com.myapplication.dagger.DaggerActivity
import com.myapplication.dagger.DaggerDemo
import com.myapplication.dagger.car.Car
import com.myapplication.dagger.car.DriverModule
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named
import javax.inject.Singleton

@PerActivity
@Subcomponent( modules = [CarModule::class, WheelModule::class,PetrolModule::class])
interface CarComponent {

    fun inject(daggerActivity: DaggerActivity)
    fun inject(daggerDemo:DaggerDemo)
//    fun getCar():Car

/*    @Subcomponent.Builder
    interface Builder{

        fun build():CarComponent
//        fun appComponent(appComponent: AppComponent):Builder

        @BindsInstance
        fun horsePower(@Named("horse_power")horsePower:Int):Builder

        @BindsInstance
        fun capacity(@Named("capacity") capacity:Int):Builder
    }
    */
    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance @Named("horse_power")horsePower:Int,
                   @BindsInstance @Named("capacity") capacity:Int
                ):CarComponent
    }
}