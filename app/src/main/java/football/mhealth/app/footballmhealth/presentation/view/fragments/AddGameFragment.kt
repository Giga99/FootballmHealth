package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.AddGameFragmentBinding
import football.mhealth.app.footballmhealth.presentation.view.adapters.TeamPlayer
import football.mhealth.app.footballmhealth.presentation.view.adapters.TeamPlayerRecyclerViewAdapter
import football.mhealth.app.footballmhealth.presentation.viewmodels.AddGameViewModel
import football.mhealth.app.footballmhealth.utils.Resource
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class AddGameFragment : ViewBindingFragment<AddGameFragmentBinding>({
    AddGameFragmentBinding.inflate(it)
}) {

    private val addGameViewModel: AddGameViewModel by viewModels()

    private lateinit var list: List<TeamPlayer>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val teamOnePlayersRecyclerViewAdapter = TeamPlayerRecyclerViewAdapter()
            rvTeamOnePlayers.adapter = teamOnePlayersRecyclerViewAdapter
            val teamTwoPlayersRecyclerViewAdapter = TeamPlayerRecyclerViewAdapter()
            rvTeamTwoPlayers.adapter = teamTwoPlayersRecyclerViewAdapter

            btnAddTeamOnePlayer.setOnClickListener {
                addPlayer(it, 1)
            }

            btnAddTeamTwoPlayer.setOnClickListener {
                addPlayer(it, 2)
            }

            btnFinish.setOnClickListener {
                val teamOneName = etTeamOneName.text.toString()
                val teamOneScore = etTeamOneResult.text.toString().toInt()
                val teamTwoName = etTeamTwoName.text.toString()
                val teamTwoScore = etTeamTwoResult.text.toString().toInt()

                addGameViewModel.addGame(teamOneScore, teamOneName, teamTwoScore, teamTwoName)
            }

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            with(addGameViewModel) {
                allPlayers.observe(viewLifecycleOwner, {
                    list = it
                })

                teamOnePlayers.observe(viewLifecycleOwner, {
                    teamOnePlayersRecyclerViewAdapter.submitList(it)
                    teamOnePlayersRecyclerViewAdapter.notifyDataSetChanged()
                })

                teamTwoPlayers.observe(viewLifecycleOwner, {
                    teamTwoPlayersRecyclerViewAdapter.submitList(it)
                    teamTwoPlayersRecyclerViewAdapter.notifyDataSetChanged()
                })

                addGameResponse.observe(viewLifecycleOwner, {
                    when (it) {
                        is Resource.Success -> findNavController().navigateUp()
                    }
                })
            }
        }
    }

    private fun addPlayer(view: View, team: Int) {
        val menu = PopupMenu(requireContext(), view)
        for (player in list) {
            menu.menu.add("${player.firstName} ${player.lastName}")
        }
        menu.show()

        menu.setOnMenuItemClickListener {
            menu.menu.forEachIndexed { index, item ->
                if(item.title == it.title)
                    addGameViewModel.insertPlayerInTeam(list[index], team)
            }
            false
        }
    }
}
