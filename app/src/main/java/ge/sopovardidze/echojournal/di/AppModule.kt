package ge.sopovardidze.echojournal.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sopovardidze.echojournal.data.local.database.RecordsDatabase
import ge.sopovardidze.echojournal.data.local.repository.RecordsRepositoryImpl
import ge.sopovardidze.echojournal.domain.repository.RecordsRepository
import ge.sopovardidze.echojournal.domain.usecases.GetAllRecordsUseCase
import ge.sopovardidze.echojournal.domain.usecases.GetFilteredRecordsUseCase
import ge.sopovardidze.echojournal.domain.usecases.InsertRecordUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Database
    @Provides
    @Singleton
    fun provideTranslateDatabase(app: Application): RecordsDatabase {
        return Room
            .databaseBuilder(app, RecordsDatabase::class.java, "records_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Repository
    @Provides
    @Singleton
    fun provideRecordsRepository(db: RecordsDatabase): RecordsRepository =
        RecordsRepositoryImpl(recordsDao = db.recordDao())

    // UseCases
    @Provides
    @Singleton
    fun provideGetAllRecordsUseCase(repository: RecordsRepository) = GetAllRecordsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFilteredRecordsUseCase(repository: RecordsRepository) = GetFilteredRecordsUseCase(repository)

    @Provides
    @Singleton
    fun provideInsertRecordUseCase(repository: RecordsRepository) = InsertRecordUseCase(repository)
}