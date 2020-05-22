package com.myapplication.dagger.dagger_components

import com.myapplication.dagger.car.Engine
import com.myapplication.dagger.car.PetrolEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
@Module
abstract class PetrolModule {

    @Binds
    abstract fun bindPetrolEngine(engine:PetrolEngine):Engine
}