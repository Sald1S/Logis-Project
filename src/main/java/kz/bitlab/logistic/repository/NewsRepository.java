package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * from News news order by news.id desc limit 1", nativeQuery = true)
    News findLast();
}
