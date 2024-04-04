package jk.labs.dev.betsson.group.technical.assesment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.ItemPlacePhotoBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils.PlacePhotosDiffUtils
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.utils.extensions.loadImage
import jk.labs.dev.betsson.group.technical.assesment.utils.viewholders.BindViewHolder

class PlacePhotosAdapter : ListAdapter<PhotoModel, PlacePhotosAdapter.ViewHolder>(
    PlacePhotosDiffUtils()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = ItemPlacePhotoBinding.inflate(
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
        val binding: ItemPlacePhotoBinding
    ) : RecyclerView.ViewHolder(binding.root), BindViewHolder<PhotoModel> {
        override fun bind(model: PhotoModel): Unit = with(binding) {
            ivPlaceDetailGalleryPhotoItem.loadImage(
                url = model.url,
                placeHolder = R.drawable.ic_image,
                hasCornersRadius = false
                )
        }
    }
}