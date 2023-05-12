package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend.domain.entities.HistoricStates;

@Repository
public interface IHistoricStateRepository extends JpaRepository<HistoricStates, Integer> {

}
