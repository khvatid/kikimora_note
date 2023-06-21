package khvatid.kikimora.data.store.retrofit.models

sealed class ResultModel<out T>{

    data class Success<out T>(val data: T?): ResultModel<T>()
    data class Failure(val code: Int,val message : String): ResultModel<Nothing>()

}
