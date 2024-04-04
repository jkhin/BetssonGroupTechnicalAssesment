package jk.labs.dev.betsson.group.technical.assesment.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import jk.labs.dev.betsson.group.technical.assesment.BuildConfig
import jk.labs.dev.betsson.group.technical.assesment.R


fun ImageView.loadImage(
    url: String,
    hasCornersRadius: Boolean = false,
    placeHolder: Int
) {
    if (url.isNotEmpty()) {
        Glide
            .with(this.context)
            .load(url)
            .placeholder(placeHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(RoundedCorners(if (hasCornersRadius) 100 else 1))
            .into(this)
    }
}