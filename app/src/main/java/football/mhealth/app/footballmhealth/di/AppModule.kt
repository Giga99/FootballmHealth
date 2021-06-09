package football.mhealth.app.footballmhealth.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import football.mhealth.app.footballmhealth.data.db.GameDAO
import football.mhealth.app.footballmhealth.data.db.HealthDatabase
import football.mhealth.app.footballmhealth.data.db.PlayerDAO
import football.mhealth.app.footballmhealth.utils.HEALTH_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHealthDatabase(
        @ApplicationContext context: Context
    ): HealthDatabase =
        Room.databaseBuilder(context, HealthDatabase::class.java, HEALTH_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePlayerDao(db: HealthDatabase): PlayerDAO = db.getPlayerDAO()

    @Singleton
    @Provides
    fun provideGameDao(db: HealthDatabase): GameDAO = db.getGameDAO()
}
