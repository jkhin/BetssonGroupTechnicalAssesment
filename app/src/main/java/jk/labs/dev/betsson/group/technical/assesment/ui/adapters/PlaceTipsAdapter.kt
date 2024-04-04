package jk.labs.dev.betsson.group.technical.assesment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.ItemPlaceTipBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils.PlaceTipsDiffUtils
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.loadImage
import jk.labs.dev.betsson.group.technical.assesment.utils.viewholders.BindViewHolder

class PlaceTipsAdapter :
    ListAdapter<PlaceTipModel, PlaceTipsAdapter.ViewHolder>(PlaceTipsDiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = ItemPlaceTipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ItemPlaceTipBinding
    ) : RecyclerView.ViewHolder(binding.root), BindViewHolder<PlaceTipModel> {
        override fun bind(model: PlaceTipModel): Unit = with(binding) {
            ivGridDetailCustomerReviewItemPhoto.loadImage(
                url = model.photo.url,
                placeHolder = R.drawable.ic_user,
                hasCornersRadius = true
            )
            tvGridDetailCustomerReviewItemReview.text = model.text
            tvGridDetailCustomerReviewItemDate.text = model.createdAt
        }
    }

}