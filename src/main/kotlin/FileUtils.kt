import java.io.File

object FileUtils {

    fun readFile(filePath : String)
    = File(
        ClassLoader
            .getSystemClassLoader()
            .getResource(filePath)
            .toURI()
    )

}