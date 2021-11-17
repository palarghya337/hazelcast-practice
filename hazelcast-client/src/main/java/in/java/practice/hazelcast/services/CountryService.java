package in.java.practice.hazelcast.services;

import com.hazelcast.core.HazelcastInstance;
import in.java.practice.hazelcast.beans.Country;
import in.java.practice.hazelcast.caches.CountryServiceCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService {

    private static final Logger LOGGER = LogManager.getLogger(CountryService.class);
    private CountryServiceCache cache;

    public CountryService(CountryServiceCache cache) {
        this.cache = cache;
    }
    public List<String> getContinents() {
        List<String> continents = cache.getContinents();
        if (Objects.isNull(continents)) {
            continents = new ArrayList<>();
            continents.add("Asia");
            continents.add("Europe");
            continents.add("Africa");
            cache.setContinents(continents);
            System.out.println("Get continents from DB");
        }
        return continents;
    }
}
