package study.nobreak.studygroupmanagerserver

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mockStatic
import org.springframework.boot.ApplicationContextFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.SpringVersion

class StudyGroupManagerServerApplicationTests {
    @Test
    fun `check spring version`() {
        Assertions.assertEquals("5.3.8", SpringVersion.getVersion());
    }
    
    @Test
    fun `main__no args__call run in SpringApplication`() {
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
