package jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils

import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel

class NearbyPlacesDiffUtils : BaseDiffUtils<PlaceGridItemModel>() {
    override fun areItemsTheSame(
        oldItem: PlaceGridItemModel,
        newItem: PlaceGridItemModel
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: PlaceGridItemModel,
        newItem: PlaceGridItemModel
    ): Boolean = oldItem == newItem

}