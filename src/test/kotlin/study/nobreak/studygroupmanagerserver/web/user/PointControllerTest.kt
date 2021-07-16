package study.nobreak.studygroupmanagerserver.web.user

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import study.nobreak.studygroupmanagerserver.point.PointHistory
import study.nobreak.studygroupmanagerserver.point.PointService
import java.time.LocalDateTime
import java.time.Month

internal class PointControllerTest {
    private val pointService = mock<PointService>()
    private val mockMvc = MockMvcBuilders
        .standaloneSetup(PointController(pointService))
        .build()
    
    @Test
    fun `POST point-histories__to be successful__CREATED historyId`() {
        val userId = 1L
        val point = -1
        val reason = "\"액션리스트 불이행\""
        val createdDateTime = LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0, 0)
        val historyId = 1L
        whenever(pointService.savePointHistory(eq(userId), eq(point), any(), any()))
            .thenReturn(PointHistory(historyId, userId, point, reason, createdDateTime))
        
        mockMvc.perform(
            post("/users/{userId}/point-histories", userId)
                .contentType(MediaType.APPLICATION_JSON)
                //language=JSON
                .content(
                    "{\n  \"point\": $point,\n  \"reason\": $reason,\n  \"createdDateTime\": \"2021-01-01 12:00:00\"\n}"
                )
        )
            .andExpect(status().isCreated)
            .andExpect(content().string(userId.toString()))
    }
}
