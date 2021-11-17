package in.java.practice.hazelcast.caches;

public enum CacheConstants {

    COUNTRY_SERVICE("COUNTRY_SERVICE_CACHE");

    private String key;
    CacheConstants(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
