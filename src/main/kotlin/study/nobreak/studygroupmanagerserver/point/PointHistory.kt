package study.nobreak.studygroupmanagerserver.point

import java.time.LocalDateTime

data class PointHistory(
    val id: Long,
    val userId: Long,
    val point: Int,
    val reason: String,
    val createdDateTime: LocalDateTime
) {
    companion object {
        fun fromCsvLine(line: String): PointHistory {
            return line.split(',').let {
                PointHistory(it[0].toLong(), it[1].toLong(), it[2].toInt(), it[3], LocalDateTime.parse(it[4]))
            }
        }
    }
    
    fun toCsvLine(): String {
        return "$id,$userId,$point,$reason,$createdDateTime"
    }
}
