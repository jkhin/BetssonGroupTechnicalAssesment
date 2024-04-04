package jk.labs.dev.betsson.group.technical.assesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jk.labs.dev.betsson.group.technical.assesment.BuildConfig
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.ItemGridPlaceBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils.NearbyPlacesDiffUtils
import jk.labs.dev.betsson.group.technical.assesment.ui.fragments.NearbyPlacesFragmentDirections
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel
import jk.labs.dev.betsson.group.technical.assesment.utils.viewholders.BindViewHolder

class NearbyPlacesAdapter :
    ListAdapter<PlaceGridItemModel, NearbyPlacesAdapter.ViewHolder>(NearbyPlacesDiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        ItemGridPlaceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: MutableList<PlaceGridItemModel>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ItemGridPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root), BindViewHolder<PlaceGridItemModel> {
        override fun bind(model: PlaceGridItemModel) = with(binding) {
            val flavorDrawable = when (BuildConfig.FLAVOR) {
                "cocktailBars" -> R.drawable.ic_cocktail
                "coffeeBars" -> R.drawable.ic_coffee
                else -> R.drawable.ic_coffee
            }

            Glide
                .with(itemView.context)
                .load(ContextCompat.getDrawable(itemView.context, flavorDrawable))
                .into(ivPlaceIconImageSample)

            tvPLaceName.text = model.name
            tvGridItemDistance.text = root.context.getString(
                R.string.grid_item_distance_label,
                model.distance
            )
            tvGridItemRating.text = root.context.getString(
                R.string.grid_item_rating_label,
                model.rating
            )
            tvGridItemPriceLabel.text = root.context.getString(
                R.string.grid_item_rating_label,
                model.pricingScale.rangeValue
            )
            itemView.setOnClickListener {
                it.findNavController().navigate(
                    NearbyPlacesFragmentDirections.actionNavigateToPlaceDetailFragment(model.id)
                )
            }
        }
    }
}