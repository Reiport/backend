package backend.backend.application.services.authentication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.application.common.interfaces.IMailSender;
import backend.backend.application.common.interfaces.repositories.IGuestTypeRepository;
import backend.backend.application.common.interfaces.repositories.IPostalCodeRepository;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.application.services.authentication.common.AuthenticationResult;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.authentication.RegisterRequest;
import backend.backend.presentation.errors.authentication.UserAlreadyRegisteredException;

@Service
public class RegisterUserService {

	private final IGuestTypeRepository guestTypeRepository;
	private final IPostalCodeRepository postalCodeRepository;
	private final IUserRepository userRepository;
	private final IJwtGenerator jwtGenerator;
	private final IMailSender mailSender;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public RegisterUserService(
			IGuestTypeRepository guestTypeRepository,
			IPostalCodeRepository postalCodeRepository,
			IUserRepository userRepository,
			IJwtGenerator jwtGenerator,
			IMailSender mailSender,
			PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.guestTypeRepository = guestTypeRepository;
		this.postalCodeRepository = postalCodeRepository;
		this.userRepository = userRepository;
		this.jwtGenerator = jwtGenerator;
		this.mailSender = mailSender;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResult handle(RegisterRequest request) {

		var guest_type = this.guestTypeRepository.findById(1);

		var postalCode = this.postalCodeRepository.findById("3520-039");

		var user = this.userRepository.findByEmail(request.getEmail());

		if (user.isPresent()) {
			throw new UserAlreadyRegisteredException();
		}

		var createdUser = this.userRepository.save(
				new Guest(
						request.getEmail(),
						passwordEncoder.encode(request.getPassword()),
						"Fernado",
						"La Ventura",
						LocalDate.of(2014, Month.JANUARY, 1),
						BigDecimal.valueOf(999999999),
						"Rua das Flores",
						150,
						BigDecimal.valueOf(789456123),
						postalCode.get(),
						guest_type.get()));

		Map<String, Object> options = new HashMap<>();

		options.put("verifyLink", "");

		// Send Email
		mailSender.sendEmail(
				"Registro na plantaforma Reiport",
				request.getEmail(),
				"welcome",
				options);

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(createdUser, request.getPassword()));

		var token = jwtGenerator.generateToken(
				createdUser.getId().toString(),
				request.getEmail());

		var refresh_token = jwtGenerator.generateToken(
				createdUser.getId().toString(),
				request.getEmail());

		return new AuthenticationResult(
				token,
				refresh_token);

	}

}
