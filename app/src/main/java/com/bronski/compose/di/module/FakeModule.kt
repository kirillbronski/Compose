package com.bronski.compose.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.datafaker.Faker
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeModule {

    @Provides
    @Singleton
    fun provideFaker() = Faker(Locale("RU"))

}