package backend.backend.infrastructure.providers.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.backend.application.common.interfaces.ICredentialGenerator;
import backend.backend.config.settings.CredentialsSettings;

@Component
public class CredentialsGenerator implements ICredentialGenerator {

    @Autowired
    private CredentialsSettings credentialsSettings;

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    public String getGeneratedEmail(String firstName, String lastName) {

        StringBuilder email = new StringBuilder();

        email.append(firstName);
        email.append(".");
        email.append(lastName);
        email.append("@");
        email.append(credentialsSettings.getName());

        return email.toString();

    }

    @Override
    public String generateRandomPassword(int length) {

        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        // Collect the categories to use.
        List<String> charCategories = new ArrayList<>(4);
        charCategories.add(LOWER);
        charCategories.add(UPPER);
        charCategories.add(DIGITS);
        charCategories.add(PUNCTUATION);

        // Build the password.
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }

        return new String(password);

    }

}
