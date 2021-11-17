package in.java.practice.hazelcast.configurations;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

@Configuration
public class HazelcastConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(HazelcastConfiguration.class);
    @Bean
    @Value("${hazelcast.configuration.file}")
    public Config getConfiguration(String configurationFileName) {

        URL resource = Thread.currentThread().getContextClassLoader().getResource(configurationFileName);
        try {
            return new XmlConfigBuilder(resource).build();
        } catch (IOException e) {
            LOGGER.error("Exception while creating hazelcast configuration.", e);
        }
        return null;
    }
}
