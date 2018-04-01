package sk.tuke.gamestudio.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest")
public class RestServiceConfig extends ResourceConfig {
    public RestServiceConfig() {
        packages("sk.tuke.gamestudio.server");
    }
}
