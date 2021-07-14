package study.nobreak.studygroupmanagerserver.point

import java.time.LocalDateTime

data class PointHistory(
    val id: Long,
    val userId: Long,
    val point: Int,
    val reason: String,
    val createdDateTime: LocalDateTime
)
