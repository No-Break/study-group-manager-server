package study.nobreak.studygroupmanagerserver.point

import org.springframework.stereotype.Repository
import java.io.File
import java.time.LocalDateTime

@Repository
class PointHistoryRepositoryImpl(
    private val pointHistoryDataFile: File
): PointHistoryRepository {
    private val data: LinkedHashMap<Long, PointHistory> = pointHistoryDataFile.readLines()
        .associate {
            PointHistory.fromCsvLine(it)
                .let { pointHistory -> pointHistory.id to pointHistory }
        } as LinkedHashMap<Long, PointHistory>
    
    override fun add(userId: Long, point: Int, reason: String, createdDateTime: LocalDateTime): PointHistory {
        val nextId = this.nextId()
        data[nextId] = PointHistory(nextId, userId, point, reason, createdDateTime)
        pointHistoryDataFile.bufferedWriter().use { writer ->
            data.forEach {
                writer.write(it.value.toCsvLine())
                writer.newLine()
            }
        }
        return data[nextId]!!
    }
    
    private fun nextId(): Long = data.keys.maxOrNull()?.plus(1) ?: 0L
}
