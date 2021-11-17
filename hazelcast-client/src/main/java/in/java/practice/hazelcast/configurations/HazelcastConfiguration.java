package in.java.practice.hazelcast.configurations;

import com.hazelcast.client.Client;
import com.hazelcast.client.ClientListener;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.client.util.ClientStateListener;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;
import in.java.practice.hazelcast.caches.CountryServiceCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URL;

@Configuration
@PropertySource("application.properties")
public class HazelcastConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(HazelcastConfiguration.class);

    @Bean(value = "hazelcast-client-config")
    @Value("${hazelcast.configuration.file}")
    public ClientConfig getConfiguration(String configurationFile) {

        ClientConfig config = null;
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(configurationFile);
            config = new XmlClientConfigBuilder(resource).build();
        } catch (IOException e) {
            LOGGER.error("Exception while creating hazelcast configuration.", e);
        }
        return config;
    }
    @Bean(value = "hazelcastInstance")
    @Autowired
    @Qualifier(value = "hazelcast-client-config")
    public HazelcastInstance getHazelcastInstance(ClientConfig config) {
        ClientStateListener clientStateListener = new ClientStateListener(config);
//        config.addListenerConfig()
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);
        if (clientStateListener.isConnected()) {
            System.out.println("Client is Connected");
        }
/*
        instance.getLifecycleService().addLifecycleListener(new LifecycleListener() {
            @Override
            public void stateChanged(LifecycleEvent lifecycleEvent) {
            }
        });*/
        return instance;
    }
    public void getClientStateListener(HazelcastInstance hazelcastInstance, CountryServiceCache cache) {

        hazelcastInstance.getClientService().addClientListener(new ClientListener() {
            @Override
            public void clientConnected(Client client) {
                cache.updateCache(true);
            }

            @Override
            public void clientDisconnected(Client client) {
                cache.updateCache(false);
            }
        });
    }
}
