package name.alexkosarev.sandbox.web.handlers.contacts;

import name.alexkosarev.sandbox.Spring5CucumberTestsApplication;
import name.alexkosarev.sandbox.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockSnippet;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@SpringBootTest
@ContextConfiguration(classes = Spring5CucumberTestsApplication.class)
public abstract class Spring5CucumberIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @MockBean
    ContactRepository contactRepository;

    WebTestClient webTestClient;

    ManualRestDocumentation manualRestDocumentation;

    public void setUp(Class testClass, String testMethod, String outputDirectory) {
        manualRestDocumentation = new ManualRestDocumentation(outputDirectory);
        manualRestDocumentation.beforeTest(testClass, testMethod);

        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                // Applying Spring Security
                .apply(springSecurity())
                // Applying Spring Restdocs and Spring Cloud Contract
                .configureClient().baseUrl("http://api.example.com")
                .filter(documentationConfiguration(manualRestDocumentation).snippets().withAdditionalDefaults(new WireMockSnippet()))
                .build();
    }

    public void tearDown() {
        manualRestDocumentation.afterTest();
    }
}
