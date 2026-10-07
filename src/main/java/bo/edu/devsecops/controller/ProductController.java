package bo.edu.devsecops.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "JdbcTemplate es un bean thread-safe de Spring y no se expone externamente.")
public class ProductController {

    private final JdbcTemplate jdbcTemplate;

    public ProductController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/search")
    public List<Map<String, Object>> search(@RequestParam(defaultValue = "") String name) {
        String sql = "SELECT id, name, price FROM products WHERE name LIKE ?";
        return jdbcTemplate.queryForList(sql, "%" + name + "%");
    }
}
