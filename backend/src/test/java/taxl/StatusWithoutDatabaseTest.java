package taxl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/** Port 1 answers to nobody: the database is unreachable, and the API must start and say so. */
@SpringBootTest(properties = {
        "taxl.version=1.2.3-test",
        "spring.datasource.url=jdbc:postgresql://127.0.0.1:1/taxl",
        "spring.datasource.hikari.connection-timeout=250",
        "db_password=nobody-listens-on-port-1"
})
@AutoConfigureMockMvc
class StatusWithoutDatabaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void statusReportsTheDatabaseAsUnreachableAndStillAnswers() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.2.3-test"))
                .andExpect(jsonPath("$.database").value("unreachable"));
    }
}
