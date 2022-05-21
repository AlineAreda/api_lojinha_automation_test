package api.lojinha.core;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:properties/${ENV}.properties",
        "classpath:properties/lojinha.api.properties"})
public interface ServerConfig extends Config {

    @Key("BASE_URL")
    String BASE_URL();

    @Key("BASE_PATH")
    String BASE_PATH();

    @Key("MAX_TIMEOUT")
    Long MAX_TIMEOUT();
}
