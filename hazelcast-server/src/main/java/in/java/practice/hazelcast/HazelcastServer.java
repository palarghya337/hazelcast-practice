package in.java.practice.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HazelcastServer {
    public static void main(String[] args) {
        SpringApplication.run(HazelcastServer.class, args);
    }
}
