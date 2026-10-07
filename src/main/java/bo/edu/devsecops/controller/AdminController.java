package bo.edu.devsecops.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "JdbcTemplate es un bean thread-safe de Spring y no se expone externamente.")
public class AdminController {

    private final JdbcTemplate jdbcTemplate;

    public AdminController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users/{id}")
    public Map<String, Object> findUser(@PathVariable Long id) {
        return jdbcTemplate.queryForMap(
                "SELECT id, username, email, role FROM users WHERE id = ?", id);
    }
}
