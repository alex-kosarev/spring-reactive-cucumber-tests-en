package name.alexkosarev.sandbox.web.handlers.contacts;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.alexkosarev.sandbox.entities.Contact;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

public class FindAllStepDefs extends Spring5CucumberIntegrationTest {

    private WebTestClient.ResponseSpec exchange;

    @Before
    public void setUp() {
        super.setUp(FindAllTest.class, "setUp", "target/generated-snippets/contacts");
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Given("^contacts stored in the storage:$")
    public void contacts_stored_in_the_storage(List<Contact> contacts) throws Throwable {
        doReturn(contacts).when(contactRepository)
                .findAll();
    }

    @When("^client sends GET \"([^\"]*)\"$")
    public void client_sends_GET(String path) throws Throwable {
        exchange = webTestClient.mutateWith(mockUser()).get().uri(path).exchange();
    }

    @Then("^service should return response with status (\\d+)$")
    public void service_should_return_response_with_status(int status) throws Throwable {
        exchange.expectStatus().isEqualTo(status);
    }

    @Then("^response body should be compatible with \"([^\"]*)\"$")
    public void response_body_should_be_compatible_with(String contentType) throws Throwable {
        exchange.expectHeader().contentType(MediaType.parseMediaType(contentType));
    }

    @Then("^response body should be an array and contain (\\d+) entries and should be documented as \"([^\"]*)\"$")
    public void response_body_should_be_an_array_and_contain_entries(int entriesCount, String documentationIdentifier) throws Throwable {
        exchange.expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(entriesCount)
                .consumeWith(document(documentationIdentifier));
    }
}
