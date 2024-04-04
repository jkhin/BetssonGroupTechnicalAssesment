package jk.labs.dev.betsson.group.technical.assesment.utils.extensions

fun prepareAndGetPhotoUrl(imgDimension: String, prefix: String, suffix: String): String {
    return "${prefix}${imgDimension}${suffix}"
}
