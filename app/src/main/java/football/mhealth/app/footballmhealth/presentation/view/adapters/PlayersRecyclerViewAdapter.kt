package football.mhealth.app.footballmhealth.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import football.mhealth.app.footballmhealth.data.entities.Player
import football.mhealth.app.footballmhealth.databinding.PlayerItemLayoutBinding
import football.mhealth.app.footballmhealth.utils.view_binding.RecyclerViewHolderBinding

class PlayersRecyclerViewAdapter(private val callback: (String) -> Unit) :
    ListAdapter<Player, RecyclerViewHolderBinding<PlayerItemLayoutBinding>>(
        object : DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }

        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecyclerViewHolderBinding(
        PlayerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerViewHolderBinding<PlayerItemLayoutBinding>,
        position: Int
    ) {
        val player = currentList[position]

        with(holder.viewBinding) {
            tvPlayerName.text = "${player.firstName} ${player.lastName}"


            clPlayerItemLayout.setOnClickListener {
                callback.invoke(player.id.toString())
            }
        }
    }
}
