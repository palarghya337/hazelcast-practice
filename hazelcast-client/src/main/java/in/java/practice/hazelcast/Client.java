package in.java.practice.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import in.java.practice.hazelcast.beans.Country;
import in.java.practice.hazelcast.services.CountryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class Client {

    private static final String BASE_PACKAGE = "in.java.practice.hazelcast";

    public static void main(String[] args) {

//        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());/*
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        CountryService service = context.getBean(CountryService.class);
        List<String> continents = service.getContinents();
        System.out.println(continents);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.getContinents());
        context.close();
    }
}
