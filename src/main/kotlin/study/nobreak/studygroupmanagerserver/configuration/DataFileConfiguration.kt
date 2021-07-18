package study.nobreak.studygroupmanagerserver.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResourceLoader
import org.springframework.core.io.WritableResource
import java.io.File

@Configuration
class DataFileConfiguration {
    private val fileSystemResourceLoader = FileSystemResourceLoader()
    
    @Bean
    fun pointHistoryDataFile(): File {
        val resource = fileSystemResourceLoader.getResource("file:src/main/resources/data/point-history.csv")
        if (resource is WritableResource) {
            return resource.file
        } else {
            throw Exception()
        }
    }
}
