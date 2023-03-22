package backend.backend.application.common.interfaces;

public interface ICredentialGenerator {

    public String getGeneratedEmail(String firstName, String lastName);

    public String generateRandomPassword(int length);

}
