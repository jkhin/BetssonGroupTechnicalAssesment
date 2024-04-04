package jk.labs.dev.betsson.group.technical.assesment.ui.adapters.utils

import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel


class PlaceTipsDiffUtils: BaseDiffUtils<PlaceTipModel>() {
    override fun areItemsTheSame(
        oldItem: PlaceTipModel,
        newItem: PlaceTipModel
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: PlaceTipModel,
        newItem: PlaceTipModel
    ): Boolean = oldItem == newItem

}
