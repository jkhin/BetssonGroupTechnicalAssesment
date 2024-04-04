package jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils

import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel

class PlacePhotosDiffUtils: BaseDiffUtils<PhotoModel>() {
    override fun areItemsTheSame(
        oldItem: PhotoModel,
        newItem: PhotoModel
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: PhotoModel,
        newItem: PhotoModel
    ): Boolean = oldItem == newItem
}