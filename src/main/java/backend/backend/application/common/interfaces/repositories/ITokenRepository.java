package backend.backend.application.common.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import backend.backend.domain.entities.redis.Token;

public interface ITokenRepository extends CrudRepository<Token, Integer> {

    Optional<Token> findByValue(String value);

}