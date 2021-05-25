package com.example.remotejobs.ui.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.remotejobs.R
import com.example.remotejobs.data.model.ResponseItem
import kotlinx.android.synthetic.main.itemlist.view.*
import org.koin.core.KoinComponent
import org.koin.core.get

class HomeAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseItem>() {
        override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemlist,
                parent,
                false
            ),
            interaction
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<ResponseItem>) {
        differ.submitList(list)
    }
    class HomeViewHolder constructor(itemView: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(itemView), KoinComponent {
        private val glide: RequestManager = get()
        private var viewModel: HomeViewModel = get()
        fun bind(item: ResponseItem) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            
            glide.load(item.companyLogo)
                .into(companyImage)
            companyName.text = "${item.company}"
            authorNameTxt.text = item.title
            if (viewModel.isFavourite(item.id) == 1)
                add_to_fav.setImageResource(R.drawable.ic_star_job)
             else
                add_to_fav.setImageResource(R.drawable.ic_favorite)

            add_to_fav.setOnClickListener {
                if (viewModel.isFavourite(item.id) == 1) {
                    add_to_fav.setImageResource(R.drawable.ic_favorite)
                    viewModel.deleteJob(item)
                } else {
                    add_to_fav.setImageResource(R.drawable.ic_star_job)
                    viewModel.saveJob(item)
                }
            }
        }
    }
    interface Interaction {
        fun onItemSelected(position: Int, item: ResponseItem)
    }
}

