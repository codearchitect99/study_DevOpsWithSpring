package codearchitect99.devOpsWithSpring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody String text) {
        Comment createdComment = commentService.createComment(text);
        return ResponseEntity.ok().body(createdComment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> allComments = commentService.getComments();
        return ResponseEntity.ok().body(allComments);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody String updateText) {
        Comment updateComment = commentService.updateComment(id, updateText);
        return ResponseEntity.ok().body(updateComment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
