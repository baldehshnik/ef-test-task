package com.sparkfusion.data.di

import android.content.Context
import androidx.room.Room
import com.sparkfusion.data.source.CourseDatabase
import com.sparkfusion.data.source.CoursesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCourseDatabase(
        @ApplicationContext
        context: Context
    ): CourseDatabase {
        return Room.databaseBuilder(
            context,
            CourseDatabase::class.java,
            "course_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCoursesDao(database: CourseDatabase): CoursesDao {
        return database.coursesDao
    }
}
























