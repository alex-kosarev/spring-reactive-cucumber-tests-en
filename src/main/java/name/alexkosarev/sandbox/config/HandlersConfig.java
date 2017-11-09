package name.alexkosarev.sandbox.config;

import name.alexkosarev.sandbox.web.handlers.ContactsHandler;
import name.alexkosarev.sandbox.repositories.ContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfig {

    @Bean
    public ContactsHandler contactsHandler(ContactRepository contactRepository) {
        return new ContactsHandler(contactRepository);
    }
}
