package in.java.practice.hazelcast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import in.java.practice.hazelcast.beans.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Converter {

    public static void main(String[] args) {

        List<Integer> abc = Arrays.asList(new Integer[]{1,2,3,4,5});
        if (abc instanceof ArrayList) {
            System.out.println("ArrayList");
        } else {
            System.out.println(abc.getClass().getName());
        }
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", "Asia"));
        countries.add(new Country("Nepal", "Asia"));
        countries.add(new Country("Bangladesh", "Asia"));

        String data = printToJson(countries);
        System.out.println("Json: " + data);

        List<Country> countryList = (List<Country>) convertJsonToCollection(data, List.class, Country.class);
        System.out.println(countryList);
    }
    public static String printToJson(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> Collection<T> convertJsonToCollection(
            String jsonString, Class<? extends Collection> collectionType, Class<T> javaType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(collectionType, javaType));
        } catch (IOException e) {
            System.out.println("Exception- " + e.getMessage());
        }
        return null;
    }
}
