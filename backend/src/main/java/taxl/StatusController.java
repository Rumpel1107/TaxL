package taxl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Answers what is running: the version comes from the git tag through the build (slice zero). */
@RestController
public class StatusController {

    private final String version;

    public StatusController(@Value("${taxl.version}") String version) {
        this.version = version;
    }

    @GetMapping("/api/status")
    public Map<String, String> status() {
        return Map.of("version", version);
    }
}
