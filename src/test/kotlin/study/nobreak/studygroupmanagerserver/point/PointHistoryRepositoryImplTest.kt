package study.nobreak.studygroupmanagerserver.point

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.time.LocalDateTime
import java.time.Month

internal class PointHistoryRepositoryImplTest {
    private lateinit var pointHistoryRepositoryImpl: PointHistoryRepositoryImpl
    
    @TempDir
    lateinit var tempDir: File
    lateinit var pointHistoryTempDataFile: File
    
    @BeforeEach
    fun setup() {
        pointHistoryTempDataFile = File(tempDir, "point-history.txt").also { file ->
            file.bufferedWriter().use { it.flush() }
        }
        pointHistoryRepositoryImpl = PointHistoryRepositoryImpl(pointHistoryTempDataFile)
    }
    
    @Test
    fun `add__call first__return historyId is 0`() {
        //Given
        val userId = 1L
        val point = -1
        val reason = "액션리스트 불이행"
        val createdDateTime = LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0, 0)
        //When
        val result = pointHistoryRepositoryImpl.add(userId, point, reason, createdDateTime)
        //Then
        assertEquals(0L, result.id)
        assertEquals(userId, result.userId)
        assertEquals(point, result.point)
        assertEquals(reason, result.reason)
        assertEquals(createdDateTime, result.createdDateTime)
        assertEquals("0,1,-1,액션리스트 불이행,2021-01-01T12:00", pointHistoryTempDataFile.readLines()[0])
    }
    
    @Test
    fun `add__call 2 times__return historyId is 1`() {
        //Given
        val userId = 1L
        val point = -1
        val reason = "액션리스트 불이행"
        val createdDateTime = LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0, 0)
        //When
        pointHistoryRepositoryImpl.add(userId, point, reason, createdDateTime)
        val result = pointHistoryRepositoryImpl.add(userId, point, reason, createdDateTime)
        //Then
        assertEquals(1L, result.id)
        assertEquals("1,1,-1,액션리스트 불이행,2021-01-01T12:00", pointHistoryTempDataFile.readLines()[1])
    }
}
