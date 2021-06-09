package football.mhealth.app.footballmhealth.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import football.mhealth.app.footballmhealth.data.entities.Game
import football.mhealth.app.footballmhealth.databinding.GameItemLayoutBinding
import football.mhealth.app.footballmhealth.utils.view_binding.RecyclerViewHolderBinding

class GamesRecyclerViewAdapter(private val callback: (String) -> Unit) :
    ListAdapter<Game, RecyclerViewHolderBinding<GameItemLayoutBinding>>(
        object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecyclerViewHolderBinding(
        GameItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<GameItemLayoutBinding>,
        position: Int
    ) {
        val game = currentList[position]

        with(holder.viewBinding) {
            tvTeamOneResult.text = game.team1NumberOfGoals.toString()
            tvTeamTwoResult.text = game.team2NumberOfGoals.toString()

            clGameItemLayout.setOnClickListener {
                callback.invoke(game.id.toString())
            }
        }
    }
}
