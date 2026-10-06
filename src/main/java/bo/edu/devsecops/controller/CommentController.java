package bo.edu.devsecops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @PostMapping("/preview")
    public ResponseEntity<String> preview(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(body.getOrDefault("comment", ""));
    }
}
