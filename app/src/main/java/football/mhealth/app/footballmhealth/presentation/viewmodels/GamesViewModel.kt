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
class GamesViewModel @Inject constructor(
    private val gamesRepository: GameRepository
) : ViewModel() {

    private val _allGames = MutableLiveData<List<Game>>()
    val allGames: LiveData<List<Game>> = _allGames

    fun getAllGames() {
        viewModelScope.launch {
            when (val response = gamesRepository.getAllGames()) {
                is Resource.Success -> {
                    _allGames.value = response.data!!
                }

                is Resource.Error -> {
                    println(response.error)
                }
            }
        }
    }
}
