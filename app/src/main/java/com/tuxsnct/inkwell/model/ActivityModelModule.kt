package com.tuxsnct.inkwell.model

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
    @Provides
    @ActivityRetainedScoped
    fun provideEditorViewModel(): EditorViewModel {
        return EditorViewModel()
    }

    @Provides
    @ActivityRetainedScoped
    fun provideManagerViewModel(): ManagerViewModel {
        return ManagerViewModel()
    }

    @Provides
    @ActivityRetainedScoped
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    @Provides
    @ActivityRetainedScoped
    fun provideSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel()
    }
}