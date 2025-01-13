package codearchitect99.devOpsWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(String text) {
        Comment newComment = new Comment();
        newComment.setText(text);
        return commentRepository.save(newComment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(Long id, String updateText) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setText(updateText);
            return commentRepository.save(comment);
        }
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
