package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.Comment;
import kz.bitlab.logistic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT comment FROM Comment comment WHERE comment.news.id LIKE :newsId ORDER BY comment.time ASC")
    List<Comment> findAllByNews(@Param("newsId") Long id);
}
