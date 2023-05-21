package backend.backend.application.services.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IHistoricStateRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IRequestRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.ContainerService;
import backend.backend.application.services.TruckService;
import backend.backend.domain.entities.Guest;
import backend.backend.domain.entities.HistoricStates;
import backend.backend.domain.entities.PostalCode;
import backend.backend.domain.entities.Request;
import backend.backend.domain.entities.State;
import backend.backend.presentation.contracts.request.ContentRequest;

@Service
public class CreateSuspendedRequest {

    @Autowired
    private IAuthorizationFacade _authorizationFacade;

    @Autowired
    private TruckService truckService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private IRequestRepository _requestRepository;

    @Autowired
    private IPostalCodeRepository _postalCodeRepository;

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IHistoricStateRepository _historicStateRepository;

    @Autowired
    private IMailSender mailSender;

    @Transactional
    public void handle(ContentRequest request) {

        PostalCode postalCodeDest = null;
        PostalCode postalCodeOri = null;

        // Must Be Authenticated
        Guest authUser = _authorizationFacade.getAuthenticatedUser();

        // Create Request without vehicles
        Guest foundClient = _userRepository.findClientById(request.getClientId());

        // TODO: Most likely change this, to assigning the postal code manually
        PostalCode foundPostalCodeDest = _postalCodeRepository.findByCode(request.getPostalCodeDest());

        PostalCode foundPostalCodeOri = _postalCodeRepository.findByCode(request.getPostalCodeOri());

        Request createdRequest = _requestRepository.save(
                new Request(
                        request.getCompanyName(),
                        request.isHasVehicleClient() ? truckService.getTruckById(request.getVehicleLicense()) : null,
                        request.isHasContainerClient()
                                ? containerService.getContainerByLicense(request.getContainerLicense())
                                : null,
                        BigDecimal.valueOf(request.getCargoWeight()),
                        LocalDate.parse(request.getDeadline(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        request.getPortDest(),
                        request.getStreetDest(),
                        request.getPortOri(),
                        request.getStreetOri(),
                        postalCodeDest == null ? foundPostalCodeDest : postalCodeDest, // If not Exist Create's
                                                                                       // the new postal Code if
                                                                                       // the validation is
                                                                                       // correct
                        postalCodeOri == null ? foundPostalCodeOri : postalCodeOri,
                        foundClient));
        ;

        // Update Historic States - Worker Started Request
        _historicStateRepository.save(
                new HistoricStates(
                        State.SUSPENDED,
                        createdRequest,
                        foundClient));

        Guest randomManager = _userRepository.getRandomManager();

        _requestRepository.linkGuest(authUser, createdRequest);
        _requestRepository.linkGuest(foundClient, createdRequest);
        _requestRepository.linkGuest(randomManager, createdRequest);

        Map<String, Object> options = new HashMap<>();
        options.put("request", createdRequest.getId());
        options.put("name", foundClient.getFirstName() + " " + foundClient.getLastName());

        mailSender.sendEmail(
                "Pedido submetido para ser aprovado",
                foundClient.getEmail(),
                "requestSentVerify",
                options);

        options.put("name", randomManager.getFirstName() + " " + randomManager.getLastName());

        mailSender.sendEmail(
                "Pedido submetido para ser aprovado",
                randomManager.getEmail(),
                "requestSentVerify",
                options);

    }

}
