package football.mhealth.app.footballmhealth.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import football.mhealth.app.footballmhealth.databinding.TeamPlayerLayoutItemBinding
import football.mhealth.app.footballmhealth.utils.view_binding.RecyclerViewHolderBinding

class TeamPlayerRecyclerViewAdapter :
    ListAdapter<TeamPlayer, RecyclerViewHolderBinding<TeamPlayerLayoutItemBinding>>(
        object : DiffUtil.ItemCallback<TeamPlayer>() {
            override fun areItemsTheSame(oldItem: TeamPlayer, newItem: TeamPlayer): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TeamPlayer, newItem: TeamPlayer): Boolean {
                return oldItem == newItem
            }

        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecyclerViewHolderBinding(
        TeamPlayerLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<TeamPlayerLayoutItemBinding>,
        position: Int
    ) {
        val teamPlayer = currentList[position]

        with(holder.viewBinding) {
            tvPlayerName.text = "${teamPlayer.firstName} ${teamPlayer.lastName}"
        }
    }
}

data class TeamPlayer(
    val id: Int,
    val firstName: String,
    val lastName: String
)
