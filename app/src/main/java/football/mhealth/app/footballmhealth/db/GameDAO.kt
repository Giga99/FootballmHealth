package football.mhealth.app.footballmhealth.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import football.mhealth.app.footballmhealth.entities.Game

@Dao
interface GameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)
}