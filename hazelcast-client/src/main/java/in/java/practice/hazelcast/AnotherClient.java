package in.java.practice.hazelcast;

import in.java.practice.hazelcast.beans.Country;
import in.java.practice.hazelcast.services.CountryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AnotherClient {

    private static final String BASE_PACKAGE = "in.java.practice.hazelcast";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        CountryService service = context.getBean(CountryService.class);
        List<String> continents = service.getContinents();
        System.out.println(continents);
        context.close();
    }
}
