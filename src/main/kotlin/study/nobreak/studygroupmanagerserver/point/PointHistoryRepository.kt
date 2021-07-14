package study.nobreak.studygroupmanagerserver.point

import java.time.LocalDateTime

interface PointHistoryRepository {
    fun add(userId: Long, point: Int, reason: String, createdDateTime: LocalDateTime): PointHistory
}
