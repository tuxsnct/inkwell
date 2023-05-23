package com.tuxsnct.inkwell.di

import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SearchViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object ActivityModelModule {
    @ActivityRetainedScoped
    @Provides
    fun provideEditorViewModel(): EditorViewModel {
        return EditorViewModel()
    }

    @ActivityRetainedScoped
    @Provides
    fun provideManagerViewModel(): ManagerViewModel {
        return ManagerViewModel()
    }

    @ActivityRetainedScoped
    @Provides
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    @ActivityRetainedScoped
    @Provides
    fun provideSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel()
    }
}