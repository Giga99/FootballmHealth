package football.mhealth.app.footballmhealth.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import football.mhealth.app.footballmhealth.databinding.AllPlayersFragmentBinding
import football.mhealth.app.footballmhealth.presentation.view.adapters.PlayersRecyclerViewAdapter
import football.mhealth.app.footballmhealth.presentation.viewmodels.PlayersViewModel
import football.mhealth.app.footballmhealth.utils.view_binding.ViewBindingFragment

@AndroidEntryPoint
class AllPlayersFragment : ViewBindingFragment<AllPlayersFragmentBinding>({
    AllPlayersFragmentBinding.inflate(it)
}) {

    private val playersViewModel: PlayersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val playersRecyclerViewAdapter = PlayersRecyclerViewAdapter {

            }
            rvAllPlayers.adapter = playersRecyclerViewAdapter
            rvAllPlayers.layoutManager = GridLayoutManager(requireContext(), 2)

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }

            playersViewModel.allPlayers.observe(viewLifecycleOwner, {
                playersRecyclerViewAdapter.submitList(it)
            })
        }
    }
}
