package taxl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Answers what is running: the version comes from the git tag through the build (D63), and the
 * database is asked on every call, so the answer is what is true now and not what was true at start.
 */
@RestController
public class StatusController {

    private final String version;
    private final JdbcTemplate jdbc;

    public StatusController(@Value("${taxl.version}") String version, JdbcTemplate jdbc) {
        this.version = version;
        this.jdbc = jdbc;
    }

    @GetMapping("/api/status")
    public Map<String, String> status() {
        return Map.of("version", version, "database", databaseState());
    }

    private String databaseState() {
        try {
            jdbc.queryForObject("SELECT 1", Integer.class);
            return "reachable";
        } catch (DataAccessException e) {
            return "unreachable";
        }
    }
}
