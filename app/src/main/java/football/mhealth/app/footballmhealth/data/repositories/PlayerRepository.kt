package football.mhealth.app.footballmhealth.data.repositories

import football.mhealth.app.footballmhealth.data.db.PlayerDAO
import football.mhealth.app.footballmhealth.data.entities.Player
import football.mhealth.app.footballmhealth.utils.Resource
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDAO: PlayerDAO
) {

    suspend fun insertPlayer(player: Player): Resource<Unit> {
        try {
            playerDAO.insertPlayer(player)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(Unit)
    }

    suspend fun deletePlayer(player: Player): Resource<Unit> {
        try {
            playerDAO.deletePlayer(player)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(Unit)
    }

    suspend fun getAllPlayers(): Resource<List<Player>> {
        val response = try {
            playerDAO.getAllPlayers()
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(response)
    }
}
