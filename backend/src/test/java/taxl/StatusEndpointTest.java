package taxl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/** The database behind these tests is a real in-memory one, so "reachable" is a real query. */
@SpringBootTest(properties = {
        "taxl.version=1.2.3-test",
        "spring.datasource.url=jdbc:h2:mem:status",
        "db_password=unused-by-h2"
})
@AutoConfigureMockMvc
class StatusEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void statusReportsTheRunningVersion() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.2.3-test"));
    }

    @Test
    void statusReportsTheDatabaseAsReachable() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.database").value("reachable"));
    }
}
