package lamb.rebecca.data

import java.io.InputStream

class ResourceException(message: String) : Exception(message)

class ResourceUtils {

    companion object {
        fun readResourceAsString(filePath: String): String {
            val resourceUrl = this::class.java.getResource(filePath)
            resourceUrl?.run {
                return readText()
            }
            throw ResourceException("Could not read file: $filePath")
        }

        fun readResourceAsStream(filePath: String): InputStream {
            val resourceStream = this::class.java.getResourceAsStream(filePath)
            resourceStream?.run {
                return this
            }
            throw ResourceException("Could not read file: $filePath")
        }

    }
}