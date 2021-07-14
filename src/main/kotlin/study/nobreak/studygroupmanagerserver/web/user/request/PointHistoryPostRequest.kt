package study.nobreak.studygroupmanagerserver.web.user.request

import java.time.LocalDateTime

data class PointHistoryPostRequest(
    val point: Int,
    val reason: String,
    val createdDateTime: LocalDateTime
)
