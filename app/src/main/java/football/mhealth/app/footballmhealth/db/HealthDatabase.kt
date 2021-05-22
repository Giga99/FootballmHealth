package football.mhealth.app.footballmhealth.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import football.mhealth.app.footballmhealth.entities.Player

@Database(
    entities = [Player::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun getPlayerDAO(): PlayerDAO
}
