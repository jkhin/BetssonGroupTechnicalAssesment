package jk.labs.dev.betsson.group.technical.assesment.data.remote.sources.base

import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): T {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            }
            throw Error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            throw  Error(e.message ?: e.toString())
        }
    }

}