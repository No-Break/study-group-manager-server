package study.nobreak.studygroupmanagerserver.point

import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PointHistoryRepositoryImpl: PointHistoryRepository {
    private val history: HashMap<Long, PointHistory> = hashMapOf()
    
    override fun add(userId: Long, point: Int, reason: String, createdDateTime: LocalDateTime): PointHistory {
        val nextId = this.nextId()
        history[nextId] = PointHistory(nextId, userId, point, reason, createdDateTime)
        return history[nextId]!!
    }
    
    private fun nextId(): Long = history.keys.maxOrNull()?.plus(1) ?: 0L
}
