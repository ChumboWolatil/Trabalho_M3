package com.example.calendario.di

import android.content.Context
import androidx.room.Room
import com.example.calendario.data.local.db.AppDatabase
import com.example.calendario.domain.repository.AgendaRepository
import com.example.calendario.data.repository.AgendaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "agenda.db"
        ).build()

    @Provides
    @Singleton
    fun provideAgendaRepository(
        db: AppDatabase
    ): AgendaRepository = AgendaRepositoryImpl(db.eventDao())
}
