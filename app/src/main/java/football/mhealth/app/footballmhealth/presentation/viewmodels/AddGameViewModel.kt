package football.mhealth.app.footballmhealth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import football.mhealth.app.footballmhealth.data.entities.Game
import football.mhealth.app.footballmhealth.data.repositories.GameRepository
import football.mhealth.app.footballmhealth.data.repositories.PlayerRepository
import football.mhealth.app.footballmhealth.presentation.view.adapters.TeamPlayer
import football.mhealth.app.footballmhealth.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    private val playersRepository: PlayerRepository,
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _allPlayers = MutableLiveData<List<TeamPlayer>>()
    val allPlayers: LiveData<List<TeamPlayer>> = _allPlayers

    private val _addGameResponse = MutableLiveData<Resource<Unit>>()
    val addGameResponse: LiveData<Resource<Unit>> = _addGameResponse

    private val teamOnePlayerNames = mutableListOf<String>()
    private val teamOnePlayersList = mutableListOf<TeamPlayer>()
    private val _teamOnePlayers = MutableLiveData<List<TeamPlayer>>()
    val teamOnePlayers: LiveData<List<TeamPlayer>> = _teamOnePlayers

    private val teamTwoPlayerNames = mutableListOf<String>()
    private val teamTwoPlayersList = mutableListOf<TeamPlayer>()
    private val _teamTwoPlayers = MutableLiveData<List<TeamPlayer>>()
    val teamTwoPlayers: LiveData<List<TeamPlayer>> = _teamTwoPlayers

    init {
        viewModelScope.launch {
            when (val response = playersRepository.getAllPlayers()) {
                is Resource.Success -> {
                    _allPlayers.value =
                        response.data.map { TeamPlayer(it.id ?: 0, it.firstName, it.lastName) }
                }

                is Resource.Error -> {
                    println(response.error)
                }
            }
        }
    }

    fun insertPlayerInTeam(player: TeamPlayer, team: Int) {
        if (team == 1) {
            teamOnePlayerNames.add("${player.firstName} ${player.lastName}")
            teamOnePlayersList.add(player)
            _teamOnePlayers.value = teamOnePlayersList
        } else {
            teamTwoPlayerNames.add("${player.firstName} ${player.lastName}")
            teamTwoPlayersList.add(player)
            _teamTwoPlayers.value = teamTwoPlayersList
        }
    }

    fun addGame(teamOneScore: Int, teamOneName: String, teamTwoScore: Int, teamTwoName: String) {
        viewModelScope.launch {
            val response = gameRepository.insertGame(
                Game(
                    team1Name = teamOneName,
                    team1NumberOfGoals = teamOneScore,
                    team1PlayerNames = teamOnePlayerNames,
                    team2Name = teamTwoName,
                    team2NumberOfGoals = teamTwoScore,
                    team2PlayerNames = teamTwoPlayerNames
                )
            )

            _addGameResponse.value = response
        }
    }
}
