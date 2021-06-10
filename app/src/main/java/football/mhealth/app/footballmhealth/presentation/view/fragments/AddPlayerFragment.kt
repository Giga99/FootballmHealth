package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.data.entities.Gender
import football.mhealth.app.footballmhealth.databinding.AddPlayerFragmentBinding
import football.mhealth.app.footballmhealth.presentation.viewmodels.AddPlayerViewModel
import football.mhealth.app.footballmhealth.utils.Resource
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class AddPlayerFragment : ViewBindingFragment<AddPlayerFragmentBinding>({
    AddPlayerFragmentBinding.inflate(it)
}) {

    private val addPlayerViewModel: AddPlayerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSubmit.setOnClickListener {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val numberOfYears = etNumberOfYears.text.toString().toInt()
                val gender = if (rbMale.id == rgGender.checkedRadioButtonId) Gender.Male else Gender.Female
                val numberOfYearsPlaying = etNumberOfYearsPlaying.text.toString().toInt()
                val averageHoursWeekly = etAverageHoursWeekly.text.toString().toDouble()

                addPlayerViewModel.addPlayer(
                    firstName,
                    lastName,
                    numberOfYears,
                    gender,
                    numberOfYearsPlaying,
                    averageHoursWeekly
                )
            }

            addPlayerViewModel.addPlPayerResponse.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Success -> findNavController().navigateUp()
                }
            })
        }
    }
}
