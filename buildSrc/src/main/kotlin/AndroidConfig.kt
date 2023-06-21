import org.gradle.api.JavaVersion

object AndroidConfig {

    const val namespace : String = "khvatid.kikimora"

    object Sdk{
        const val compile : Int = 33
        const val target : Int = 33
        const val min : Int = 29
    }
    object Version{
        const val code = 1
        const val name = "1.0"
    }

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget : String = "17"
}