package name.alexkosarev.sandbox.web.handlers;

import lombok.RequiredArgsConstructor;
import name.alexkosarev.sandbox.entities.Contact;
import name.alexkosarev.sandbox.repositories.ContactRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Flux.fromIterable;

@RequiredArgsConstructor
public class ContactsHandler {

    private final ContactRepository contactRepository;

    public Mono<ServerResponse> getAllContacts(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromIterable(contactRepository.findAll()), Contact.class);
    }
}
