package backend.backend.application.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.domain.entities.HistoricStates;

public interface IHistoricStateRepository extends JpaRepository<HistoricStates, Integer> {

}
