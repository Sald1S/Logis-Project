package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PriceRepository extends JpaRepository<Price, Long> {
}
