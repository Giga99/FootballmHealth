package football.mhealth.app.footballmhealth.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import football.mhealth.app.footballmhealth.data.entities.Game
import football.mhealth.app.footballmhealth.data.entities.Player

@Database(
    entities = [Player::class, Game::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HealthDatabase : RoomDatabase() {

    abstract fun getPlayerDAO(): PlayerDAO

    abstract fun getGameDAO(): GameDAO
}
