package football.mhealth.app.footballmhealth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import football.mhealth.app.footballmhealth.data.entities.Gender
import football.mhealth.app.footballmhealth.data.entities.Player
import football.mhealth.app.footballmhealth.data.repositories.PlayerRepository
import football.mhealth.app.footballmhealth.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playersRepository: PlayerRepository
) : ViewModel() {

    private val _allPlayers = MutableLiveData<List<Player>>()
    val allPlayers: LiveData<List<Player>> = _allPlayers

    fun getAllPlayers() {
        viewModelScope.launch {
            when (val response = playersRepository.getAllPlayers()) {
                is Resource.Success -> {
                    _allPlayers.value = response.data!!
                }

                is Resource.Error -> {
                    println(response.error)
                }
            }
        }
    }
}
