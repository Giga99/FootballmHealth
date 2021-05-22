package football.mhealth.app.footballmhealth.utils.view_binding

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class RecyclerViewHolderBinding<T : ViewBinding>(val viewBinding: T) :
    RecyclerView.ViewHolder(viewBinding.root)
