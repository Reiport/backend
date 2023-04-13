package backend.backend.application.common.interfaces.repositories;

import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;

public interface IGuestGroupRepository {

    void linkGuest(Guest user, Request request);

}
