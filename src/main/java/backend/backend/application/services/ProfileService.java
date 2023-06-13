package backend.backend.application.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.common.interfaces.repositories.IUserRepository;
import backend.backend.domain.entities.Guest;
import backend.backend.presentation.contracts.UpdateProfile;
import backend.backend.presentation.errors.authentication.CannotUpdateException;

@Service
public class ProfileService {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private IUserRepository userRepository;

    public Guest updateProfile(UpdateProfile updateProfile) {

        Guest authenticatedUser = authorizationFacade.getAuthenticatedUser();

        if (!updateProfile.getEmail().equals(authenticatedUser.getEmail())) {
            throw new CannotUpdateException();
        }

        authenticatedUser.setEmail(updateProfile.getEmail());
        authenticatedUser.setPassword(authenticatedUser.getPassword());
        authenticatedUser.setFirstName(updateProfile.getFirstName());
        authenticatedUser.setLastName(updateProfile.getLastName());
        authenticatedUser
                .setBirthDate(LocalDate.parse(updateProfile.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        authenticatedUser.setNif(updateProfile.getNif());
        authenticatedUser.setStreet(updateProfile.getStreet());
        authenticatedUser.setPort(updateProfile.getPort());
        authenticatedUser.setTelephone(updateProfile.getTelephone());

        return userRepository.save(authenticatedUser);

    }

}
