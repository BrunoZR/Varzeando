package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Quadra;

import java.util.Optional;

@Repository
public interface QuadrasRepository extends JpaRepository<Quadra,Long> {

}