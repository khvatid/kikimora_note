package khvatid.androidAi.domain.models

sealed class AnswerModel {
    object Loading : AnswerModel()
    data class Error(val error: String) : AnswerModel()
    data class Success(val message: MessageModel) : AnswerModel()
}
