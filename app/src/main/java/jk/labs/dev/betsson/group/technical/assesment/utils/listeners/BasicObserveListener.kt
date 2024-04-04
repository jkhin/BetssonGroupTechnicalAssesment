package jk.labs.dev.betsson.group.technical.assesment.utils.listeners

interface BasicObserveListener<T>{
    fun doOnSuccess(model: T)
    fun doOnLoading(isLoading: Boolean)
    fun doOnError(errorMessage: String)
}