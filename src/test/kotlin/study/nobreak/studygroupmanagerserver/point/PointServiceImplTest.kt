package study.nobreak.studygroupmanagerserver.point

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import java.time.Month

internal class PointServiceImplTest {
    private val pointHistoryRepository = mock<PointHistoryRepository>()
    private val pointServiceImpl = PointServiceImpl(pointHistoryRepository)
    
    @Test
    fun `savePointHistory__to be successful__return saved PointHistory`() {
        //Given
        val historyId = 1L
        val userId = 1L
        val point = -1
        val reason = "액션리스트 불이행"
        val createdDateTime = LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0, 0)
        whenever(pointHistoryRepository.add(eq(userId), eq(point), eq(reason), eq(createdDateTime)))
            .thenReturn(PointHistory(historyId, userId, point, reason, createdDateTime))
        //When
        val result = pointServiceImpl.savePointHistory(userId, point, reason, createdDateTime)
        //Then
        assertEquals(historyId, result.id)
        assertEquals(userId, result.userId)
        assertEquals(point, result.point)
        assertEquals(reason, result.reason)
        assertEquals(createdDateTime, result.createdDateTime)
    }
}
