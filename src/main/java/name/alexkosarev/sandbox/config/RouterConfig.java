package name.alexkosarev.sandbox.config;

import name.alexkosarev.sandbox.web.handlers.ContactsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ContactsHandler contactsHandler) {
        return route(GET("/contacts"), contactsHandler::getAllContacts);
    }
}
