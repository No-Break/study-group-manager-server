package study.nobreak.studygroupmanagerserver

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mockStatic
import org.springframework.boot.ApplicationContextFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.context.ConfigurableApplicationContext

class StudyGroupManagerServerApplicationTests {
    @Test
    fun `main_no args_call run in SpringApplication`() {
        mockStatic(SpringApplication::class.java).use {
            it.`when`<ConfigurableApplicationContext> {
                SpringApplication.run(any())
            }.thenReturn(ApplicationContextFactory.DEFAULT.create(WebApplicationType.NONE))
            
            main(arrayOf())
            
            it.verify {
                SpringApplication.run(StudyGroupManagerServerApplication::class.java)
            }
        }
    }
}
