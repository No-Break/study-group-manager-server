package study.nobreak.studygroupmanagerserver.web.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import study.nobreak.studygroupmanagerserver.point.PointService
import study.nobreak.studygroupmanagerserver.web.user.request.PointHistoryPostRequest

@Controller
class PointController(
    private val pointService: PointService
) {
    @PostMapping("/users/{userId}/point-histories")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun savePointHistory(@PathVariable userId: Long, @RequestBody request: PointHistoryPostRequest): Long {
        return pointService.savePointHistory(userId, request.point, request.reason, request.createdDateTime).id
    }
}
