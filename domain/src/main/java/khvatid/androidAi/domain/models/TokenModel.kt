package khvatid.androidAi.domain.models

sealed class TokenModel(private val token: String) {
   data class Token(val token: String) : TokenModel(token = token)
   data class TokenWithProvider(val token: String, val apiProvider: ApiProvider) : TokenModel(token)

   sealed class ApiProvider(){
      object OpenAiApi: ApiProvider()
   }
}

