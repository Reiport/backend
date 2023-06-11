package backend.backend.application.services.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.ICredentialGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.config.settings.FrontEndSettings;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterWorkerRequest;
import backend.backend.presentation.mappers.GuestMapper;

@Service
public class RegisterWorkerService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IGuestTypeRepository guestTypeRepository;

    @Autowired
    private ICredentialGenerator credentialGenerator;

    @Autowired
    private FrontEndSettings frontEndSettings;

    @Autowired
    private IMailSender mailSender;

    public void handle(RegisterWorkerRequest request) {

        if (request.getGuestType().equals("Cliente"))
            throw new RuntimeException("You cannot register an Client!");

        if (guestTypeRepository.findByName(request.getGuestType()).isEmpty())
            throw new RuntimeException("You cannot register an worker with an role not avaiable");

        // Option 1 - Gerar Automaticamente Email
        var email = credentialGenerator.getGeneratedEmail(
                request.getFirstName(),
                request.getLastName());

        // Option 2 - Inserir Email com o domineo

        // Sistema Gerar Automaticamente Password
        var password = credentialGenerator.generateRandomPassword(16);
        // Atribuição da password

        Guest guest = GuestMapper.INSTANCE.registerWorkerRequestToGuest(request);
        guest.setEnabled(true);
        guest.setEmail(email);
        guest.setPassword(passwordEncoder.encode(password));
        guest.setGuestType(guestTypeRepository.findByName(request.getGuestType()).get());

        this.userRepository.save(guest);

        Map<String, Object> options = new HashMap<>();

        options.put("email", guest.getEmail());
        options.put("password", password);
        options.put("web", frontEndSettings.getUrl() + "/auth/login");

        // Send Email
        mailSender.sendEmail(
                "Registro na Plataforma Reiport - Credenciais",
                email,
                "worker-welcome",
                options);

    }

}
