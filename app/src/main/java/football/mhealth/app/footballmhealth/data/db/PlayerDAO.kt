package football.mhealth.app.footballmhealth.data.db

import androidx.room.*
import football.mhealth.app.footballmhealth.data.entities.Player

@Dao
interface PlayerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("SELECT * FROM PLAYER_TABLE")
    suspend fun getAllPlayers(): List<Player>
}
