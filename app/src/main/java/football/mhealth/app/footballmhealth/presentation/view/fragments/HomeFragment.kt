package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.HomeFragmentBinding
import football.mhealth.app.footballmhealth.presentation.viewmodels.Players2ViewModel
import football.mhealth.app.footballmhealth.utils.safeNavigate
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<HomeFragmentBinding>({
    HomeFragmentBinding.inflate(it)
}) {

    private val players2ViewModel: Players2ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        players2ViewModel.init()

        with(viewBinding) {
            btnViewAllGames.setOnClickListener {
                findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToAllGamesFragment())
            }

            btnViewAllPlayers.setOnClickListener {
                findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToAllPlayersFragment())
            }
        }
    }
}
