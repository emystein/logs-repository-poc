package ar.com.flow.platform.log.adapters.`in`.web.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import ar.com.flow.platform.log.domain.AppLogEntryFactory.logEntry1
import ar.com.flow.platform.log.ports.out.AppLog
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest
@ContextConfiguration(classes = [TestApiConfiguration::class])
class AppLogControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var appLog: AppLog

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    internal fun addEntry() {
        justRun { appLog.addEntry(logEntry1) }

        val requestContent = objectMapper.writeValueAsString(logEntry1)

        mockMvc.perform(post("/log/app/entries")
            .content(requestContent)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated)

        verify { appLog.addEntry(logEntry1) }
    }

    @Test
    internal fun entriesByContext() {
        val entries = listOf(logEntry1)

        every { appLog.entriesByContext(logEntry1.context!!) } returns entries

        val expectedJson = objectMapper.writeValueAsString(entries)

        mockMvc.perform(get("/log/app/entries/by-context/{context}", logEntry1.context))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJson))

        verify { appLog.entriesByContext(logEntry1.context!!) }
    }
}