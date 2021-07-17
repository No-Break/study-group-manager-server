package study.nobreak.studygroupmanagerserver.point

import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository
import java.io.File
import java.time.LocalDateTime

@Repository
class PointHistoryRepositoryImpl(
    resourceLoader: ResourceLoader
): PointHistoryRepository {
    private val file: File = resourceLoader.getResource("classpath:data/point-history.csv").file
    private val data: LinkedHashMap<Long, PointHistory> = file.readLines().associate {
        PointHistory.fromCsvLine(it)
            .let { pointHistory -> pointHistory.id to pointHistory }
    } as LinkedHashMap<Long, PointHistory>
    
    override fun add(userId: Long, point: Int, reason: String, createdDateTime: LocalDateTime): PointHistory {
        val nextId = this.nextId()
        data[nextId] = PointHistory(nextId, userId, point, reason, createdDateTime)
        file.bufferedWriter().use { writer ->
            data.forEach {
                writer.write(it.value.toCsvLine())
            }
        }
        return data[nextId]!!
    }
    
    private fun nextId(): Long = data.keys.maxOrNull()?.plus(1) ?: 0L
}
