package football.mhealth.app.footballmhealth.data.db

import androidx.room.*
import football.mhealth.app.footballmhealth.data.entities.Game

@Dao
interface GameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("SELECT * FROM GAME_TABLE")
    suspend fun getAllGames(): List<Game>

    @Query("SELECT * FROM GAME_TABLE WHERE id=:gameId")
    suspend fun getGameInfo(gameId: String): Game
}