package fa.training.impsystem.repository;

import fa.training.impsystem.entities.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidates, Integer> {

    Optional<Candidates> findByCandidateIdAndIsDelete(Integer id, Boolean isDelete);

    List<Candidates> findByIsDelete(Boolean isDelete);

    boolean existsByCandidateIdAndIsDelete(Integer id, Boolean isDelete);
}
