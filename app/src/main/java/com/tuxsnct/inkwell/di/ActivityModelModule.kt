package com.tuxsnct.inkwell.di

import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SearchViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModelModule {
    @ActivityScoped
    @Provides
    fun provideEditorViewModel(): EditorViewModel {
        return EditorViewModel()
    }

    @ActivityScoped
    @Provides
    fun provideManagerViewModel(): ManagerViewModel {
        return ManagerViewModel()
    }

    @ActivityScoped
    @Provides
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    @ActivityScoped
    @Provides
    fun provideSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel()
    }
}