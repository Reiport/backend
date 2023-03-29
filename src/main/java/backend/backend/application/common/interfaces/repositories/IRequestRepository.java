package backend.backend.application.common.interfaces.repositories;

import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.Request;

public interface IRequestRepository {

    void linkGuest(Guest guest, Request request);

    Request save(Request request);

}
