package study.nobreak.studygroupmanagerserver.point

import java.time.LocalDateTime

interface PointService {
    fun savePointHistory(userId: Long, point: Int, reason: String, createdDateTime: LocalDateTime): Long
}
