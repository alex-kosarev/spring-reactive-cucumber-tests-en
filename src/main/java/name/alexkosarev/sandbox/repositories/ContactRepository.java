package name.alexkosarev.sandbox.repositories;

import name.alexkosarev.sandbox.entities.Contact;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ContactRepository extends ReactiveCrudRepository<Contact, String> {
}
