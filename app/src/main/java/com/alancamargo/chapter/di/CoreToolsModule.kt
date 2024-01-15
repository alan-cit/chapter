package com.alancamargo.chapter.di

import com.alancamargo.chapter.core.tools.ToastHelper
import com.alancamargo.chapter.core.tools.ToastHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CoreToolsModule {

    @Binds
    @ActivityScoped
    abstract fun bindToastHelper(impl: ToastHelperImpl): ToastHelper
}
