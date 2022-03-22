package no.egde.hotelbooking.acceptanceTests;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasspathResource("scenarios")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "no.egde.hotelbooking.repository")
public class CucumberIntegrationTest {
}
