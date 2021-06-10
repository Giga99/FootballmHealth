package football.mhealth.app.footballmhealth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import football.mhealth.app.footballmhealth.data.entities.Game
import football.mhealth.app.footballmhealth.data.repositories.GameRepository
import football.mhealth.app.footballmhealth.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameInfoViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _gameInfo = MutableLiveData<Game>()
    val gameInfo: LiveData<Game> = _gameInfo

    fun init(gameId: String) {
        viewModelScope.launch {
            when(val response = gameRepository.getGameInfo(gameId)) {
                is Resource.Success -> {
                    _gameInfo.value = response.data!!
                }

                is Resource.Error -> {
                    println(response.error)
                }
            }
        }
    }
}
