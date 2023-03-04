package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
}

