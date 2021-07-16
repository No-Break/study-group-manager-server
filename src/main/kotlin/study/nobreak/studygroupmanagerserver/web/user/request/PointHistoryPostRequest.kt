package study.nobreak.studygroupmanagerserver.web.user.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class PointHistoryPostRequest(
    val point: Int,
    val reason: String,
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    val createdDateTime: LocalDateTime
)
