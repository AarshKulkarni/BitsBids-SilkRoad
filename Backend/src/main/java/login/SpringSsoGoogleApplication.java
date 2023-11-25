
package login;
import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSsoGoogleApplication {

    private static final String BITS_EMAIL_REGEX = "f20[0-9]{6}@hyderabad\\.bits-pilani\\.ac\\.in";
    private static final Pattern BITS_EMAIL_PATTERN = Pattern.compile(BITS_EMAIL_REGEX);

    @GetMapping
    public String welcome() {
        return "Welcome to the treasure trove of wonders!!";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        Matcher matcher = BITS_EMAIL_PATTERN.matcher(principal.getName());
        if (matcher.matches()) {
            System.out.println("Valid email: " + principal.getName());
            return principal;
        } else {
            System.out.println("Invalid email: " + principal.getName());
            throw new RuntimeException("Invalid email format!");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSsoGoogleApplication.class, args);
    }
}
