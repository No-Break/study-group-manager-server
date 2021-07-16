package study.nobreak.studygroupmanagerserver.point

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PointServiceImpl(
    private val pointHistoryRepository: PointHistoryRepository
): PointService {
    override fun savePointHistory(
        userId: Long,
        point: Int,
        reason: String,
        createdDateTime: LocalDateTime
    ): PointHistory {
        return pointHistoryRepository.add(userId, point, reason, createdDateTime)
    }
}
