package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.Request;
import kz.bitlab.logistic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "SELECT request FROM Request request WHERE request.user.id LIKE :userId ORDER BY request.id ASC")
    List<Request> findAllByUser(@Param("userId") Long id);

//    @Query(value = "SELECT request FROM Request request WHERE request.receiver.receiver_postalCode LIKE :nameParam order by request.id  asc")
//    List<Request> searchRequest( String nameParam);
}
