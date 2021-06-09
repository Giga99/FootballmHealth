package football.mhealth.app.footballmhealth.data.repositories

import football.mhealth.app.footballmhealth.data.db.GameDAO
import football.mhealth.app.footballmhealth.data.entities.Game
import football.mhealth.app.footballmhealth.utils.Resource
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameDAO: GameDAO
) {

    suspend fun insertGame(game: Game): Resource<Unit> {
        try {
            gameDAO.insertGame(game)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(Unit)
    }

    suspend fun deleteGame(game: Game): Resource<Unit> {
        try {
            gameDAO.deleteGame(game)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(Unit)
    }

    suspend fun getAllGames(): Resource<List<Game>> {
        val response = try {
            gameDAO.getAllGames()
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(response)
    }
}
