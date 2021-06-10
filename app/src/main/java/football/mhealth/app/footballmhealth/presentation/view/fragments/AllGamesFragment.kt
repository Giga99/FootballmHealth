package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.AllGamesFragmentBinding
import football.mhealth.app.footballmhealth.presentation.view.adapters.GamesRecyclerViewAdapter
import football.mhealth.app.footballmhealth.presentation.viewmodels.GamesViewModel
import football.mhealth.app.footballmhealth.utils.safeNavigate
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class AllGamesFragment : ViewBindingFragment<AllGamesFragmentBinding>({
    AllGamesFragmentBinding.inflate(it)
}) {

    private val gamesViewModel: GamesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesViewModel.getAllGames()

        with(viewBinding) {
            val gamesRecyclerViewAdapter = GamesRecyclerViewAdapter {
                findNavController().safeNavigate(AllGamesFragmentDirections.actionAllGamesFragmentToGameInfoFragment(it))
            }
            rvAllGames.adapter = gamesRecyclerViewAdapter

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            fabAdd.setOnClickListener {
                findNavController().safeNavigate(AllGamesFragmentDirections.actionAllGamesFragmentToAddGameFragment())
            }

            gamesViewModel.allGames.observe(viewLifecycleOwner, {
                gamesRecyclerViewAdapter.submitList(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        gamesViewModel.getAllGames()
    }
}
