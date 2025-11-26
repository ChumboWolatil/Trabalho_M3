package com.example.calendario.di

import android.content.Context
import androidx.room.Room
import com.example.calendario.data.local.db.AppDatabase
import com.example.calendario.data.repository.AgendaRepositoryImpl
import com.example.calendario.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "events.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideEventDao(db: AppDatabase) = db.eventDao()

    @Provides
    @Singleton
    fun provideAgendaRepository(db: AppDatabase): AgendaRepository =
        AgendaRepositoryImpl(db.eventDao())
}
