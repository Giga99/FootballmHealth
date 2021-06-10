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
class AddPlayerViewModel @Inject constructor(
    private val playersRepository: PlayerRepository,
) : ViewModel() {

    private val _addPlPayerResponse = MutableLiveData<Resource<Unit>>()
    val addPlPayerResponse: LiveData<Resource<Unit>> = _addPlPayerResponse

    fun addPlayer(
        firstName: String,
        lastName: String,
        numberOfYears: Int,
        gender: Gender,
        numberOfYearsPlaying: Int,
        averageHoursWeekly: Double
    ) {
        viewModelScope.launch {
            val response = playersRepository.insertPlayer(
                Player(
                    firstName = firstName,
                    lastName = lastName,
                    numOfYears = numberOfYears,
                    gender = gender,
                    numOfYearsPlaying = numberOfYearsPlaying,
                    averageHoursWeekly = averageHoursWeekly
                )
            )

            _addPlPayerResponse.value = response
        }
    }
}
