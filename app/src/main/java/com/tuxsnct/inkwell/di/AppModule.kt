package com.tuxsnct.inkwell.di

import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SearchViewModel
import com.tuxsnct.inkwell.ui.viewmodels.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    @Provides
    @Singleton
    fun provideEditorViewModel(): EditorViewModel {
        return EditorViewModel()
    }

    @Provides
    @Singleton
    fun provideManagerViewModel(): ManagerViewModel {
        return ManagerViewModel()
    }

    @Provides
    @Singleton
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    @Provides
    @Singleton
    fun provideSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel()
    }
}