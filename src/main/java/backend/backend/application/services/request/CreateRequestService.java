package backend.backend.application.services.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IHistoricStateRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.HistoricStates;
import backend.backend.domain.entities.PostalCode;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.request.ContentRequest;
import backend.backend.presentation.errors.client.ClientNotFoundException;

@Service
public class CreateRequestService {

    @Autowired
    private IAuthorizationFacade _authorizationFacade;

    @Autowired
    private IRequestRepository _requestRepository;

    @Autowired
    private IPostalCodeRepository _postalCodeRepository;

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IHistoricStateRepository _historicStateRepository;

    // TODO: Verificar se este funciona corretamente
    @Transactional
    public void handle(ContentRequest request) {

        PostalCode postalCodeDest = null;
        PostalCode postalCodeOri = null;

        // Must Be Authenticated
        Guest authUser = _authorizationFacade.getAuthenticatedUser();

        // Create Request without vehicles
        Optional<Guest> foundClient = _userRepository.findClientById(request.getClientId());

        if (foundClient.isEmpty()) {
            throw new ClientNotFoundException();
        }

        // TODO: Most likely change this, to assigning the postal code manually
        PostalCode foundPostalCodeDest = _postalCodeRepository.findByCode(request.getPostalCodeDest());

        // if (foundPostalCodeDest.isEmpty())
        // postalCodeDest = _postalCodeRepository.save(new
        // PostalCode(request.getPostalCodeDest(), "Lost River",
        // _countryRepository.findByCountry("Portugal")));

        PostalCode foundPostalCodeOri = _postalCodeRepository.findByCode(request.getPostalCodeOri());

        // if (foundPostalCodeOri.isEmpty())
        // postalCodeOri = _postalCodeRepository.save(new
        // PostalCode(request.getPostalCodeOri(), "Lost River",
        // _countryRepository.findByCountry("Portugal")));

        Request createdRequest = _requestRepository.save(
                new Request(
                        BigDecimal.valueOf(request.getCargoWeight()),
                        LocalDate.parse(request.getDeadline(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        request.getPortDest(),
                        request.getStreetDest(),
                        request.getPortOri(),
                        request.getStreetOri(),
                        BigDecimal.valueOf(request.getDeliveryPrice()),
                        postalCodeDest == null ? foundPostalCodeDest : postalCodeDest, // If not Exist Create's
                                                                                       // the new postal Code if
                                                                                       // the validation is
                                                                                       // correct
                        postalCodeOri == null ? foundPostalCodeOri : postalCodeOri,
                        foundClient.get()));
        ;

        // Update Historic States - Worker Started Request
        _historicStateRepository.save(
                new HistoricStates(
                        State.SCHEDULED,
                        createdRequest,
                        foundClient.get()));

        // TODO: Este codigo não verifica se o manager já está num request!
        // TODO: De alguma forma avisar que este request está disponivel para
        _requestRepository.linkGuest(
                authUser,
                createdRequest);

    }

}
