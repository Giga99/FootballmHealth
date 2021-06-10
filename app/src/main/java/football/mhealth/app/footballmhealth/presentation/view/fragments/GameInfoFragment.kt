package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.GameInfoFragmentBinding
import football.mhealth.app.footballmhealth.presentation.view.adapters.TeamPlayer
import football.mhealth.app.footballmhealth.presentation.view.adapters.TeamPlayerRecyclerViewAdapter
import football.mhealth.app.footballmhealth.presentation.viewmodels.GameInfoViewModel
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class GameInfoFragment : ViewBindingFragment<GameInfoFragmentBinding>({
    GameInfoFragmentBinding.inflate(it)
}) {

    private val gameInfoFragmentArgs: GameInfoFragmentArgs by navArgs()
    private val gameInfoViewModel: GameInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameInfoViewModel.init(gameInfoFragmentArgs.gameId)

        with(viewBinding) {
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            gameInfoViewModel.gameInfo.observe(viewLifecycleOwner, {
                tvTeamOneName.text = it.team1Name
                tvTeamOneResult.text = it.team1NumberOfGoals.toString()
                tvTeamTwoName.text = it.team2Name
                tvTeamTwoResult.text = it.team2NumberOfGoals.toString()
                rvTeamOnePlayers.adapter = TeamPlayerRecyclerViewAdapter().apply { submitList(it.team1PlayerNames!!.map { TeamPlayer(0, it.substringBefore(" "), it.substringAfter(" ")) }) }
                rvTeamTwoPlayers.adapter = TeamPlayerRecyclerViewAdapter().apply { submitList(it.team2PlayerNames!!.map { TeamPlayer(0, it.substringBefore(" "), it.substringAfter(" ")) }) }
            })
        }
    }
}
