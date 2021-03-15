package com.dash.doorlite.core.modules

import android.content.Context
import com.dash.doorlite.core.AppManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {


    @Singleton
    @Provides
    fun provideAppManager(@ApplicationContext context: Context) : AppManager{
        return AppManager(context)
    }
}