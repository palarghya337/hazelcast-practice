package in.java.practice.hazelcast.caches;

import com.hazelcast.client.Client;
import com.hazelcast.client.ClientListener;
import com.hazelcast.client.ClientService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;
import com.hazelcast.map.IMap;
import in.java.practice.hazelcast.listeners.CustomListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CountryServiceCache {

    private HazelcastInstance hazelcastInstance;
    private Map<String, Object> countryServiceCache;
    private static final String CONTINENT_CACHE_KEY = "CONTINENTS";

//    public CountryServiceCache() {}
    public CountryServiceCache(HazelcastInstance hazelcastInstance) {
        if (Objects.isNull(hazelcastInstance)) {
            countryServiceCache = new ConcurrentHashMap<>();
        } else {
            getClientStateListener(hazelcastInstance);
            this.hazelcastInstance = hazelcastInstance;
            updateCache(true);
            /*
            Map<String, Object> tempMap = null;
            try {
                tempMap = hazelcastInstance.getMap(CacheConstants.COUNTRY_SERVICE.getKey());
            } catch (Exception e) {
                tempMap = new ConcurrentHashMap<>();
            }
            countryServiceCache = tempMap;*/
        }
    }
    public List<String> getContinents() {
        return (List<String>) countryServiceCache.get(CONTINENT_CACHE_KEY);
    }
    public void setContinents(List<String> continents) {
        if (countryServiceCache instanceof IMap) {
            System.out.println("Hazelcast Map");
        }
        countryServiceCache.put(CONTINENT_CACHE_KEY, continents);
    }
    public void updateCache(boolean isConnected) {

        if (isConnected) {
            try {
                IMap<String, Object> tempMap = hazelcastInstance.getMap(CacheConstants.COUNTRY_SERVICE.getKey());
                if (Objects.nonNull(countryServiceCache) && !countryServiceCache.isEmpty()) {
                    tempMap.setAll(this.countryServiceCache);
                }
                this.countryServiceCache = tempMap;
            } catch (Exception e) {
                countryServiceCache = new ConcurrentHashMap<>();
            }
        } else {
            countryServiceCache = new ConcurrentHashMap<>();
        }
    }
    public void getClientStateListener(HazelcastInstance hazelcastInstance) {

        hazelcastInstance.getLifecycleService().addLifecycleListener(new LifecycleListener() {
            @Override
            public void stateChanged(LifecycleEvent lifecycleEvent) {
                System.out.println(lifecycleEvent.getState() + "**********");
                if (LifecycleEvent.LifecycleState.CLIENT_CONNECTED.equals(lifecycleEvent.getState())) {
                    updateCache(true);
                }
            }
        });
        /*
        ClientService clientService = hazelcastInstance.getClientService();
        clientService.addClientListener(new ClientListener() {
            @Override
            public void clientConnected(Client client) {
                updateCache(true);
            }

            @Override
            public void clientDisconnected(Client client) {
                updateCache(false);
            }
        });*/
    }
}
