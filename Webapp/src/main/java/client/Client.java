package client;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.core.convert.converter.GenericConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Computer;

public class Client {
    static ClientConfig cfg = new ClientConfig(GenericConverter.class);
    static org.glassfish.jersey.client.JerseyClient client = (org.glassfish.jersey.client.JerseyClient) ClientBuilder.newClient(cfg);
    static WebTarget target = client.target(getBaseURI());
    private static Logger LOGGER = Logger.getLogger(Client.class.getName());
    static ObjectMapper mapper = new ObjectMapper();

    // public static final URI BASE_URI = getBaseURI();

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/Webapp/wsdashboard/").port(8080).build();
    }

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        LOGGER.warning(target.request(MediaType.APPLICATION_JSON).get(String.class));

        List<Computer> participantJsonList = mapper.readValue(target.path("page/0/search/ /numberOfComputerByPage/3/sort/name/order/ASC").request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<List<Computer>>() {
                });
        // http://localhost:8080/Webapp/wsdashboard/page/0/search/
        // /numberOfComputerByPage/3/sort/name/order/ASC
        // target.path("http://localhost:8080/Webapp/wsdashboard/page/0/search/
        // /numberOfComputerByPage/3/sort/name/order/ASC");
        LOGGER.warning(participantJsonList.get(1).getName());
        // JSONParser parser = new JSONParser();
        // parser.addTypeHint(".Example[]", Computer.class);
        // String json = "{" + "\"Example\": [" + "{" + "\"foo\": \"a1\"," + "\"bar\":
        // \"b1\"," + "\"fubar\": \"c1\"" + "}," + "{" + "\"foo\": \"a2\"," + "\"bar\":
        // \"b2\"," + "\"fubar\": \"c2\"" + "},"
        // + "{" + "\"foo\": \"a3\"," + "\"bar\": \"b3\"," + "\"fubar\": \"c3\"" + "}" +
        // "]" + "}\"";
        // Map<String, List<Example>> result1 = parser.parse(Map.class,
        // target.request(MediaType.APPLICATION_JSON).get(String.class));
        // for (Entry<String, List<Example>> entry : result1.entrySet()) {
        // for (Example example : entry.getValue()) {
        // System.out.println("VALUE :->" + example.getFoo());
        // }
        // }
        // Type listType = new TypeToken<List<Computer>>() {
        // }.getType();
        //
        // List<Computer> yourList = new
        // Gson().fromJson(target.request(MediaType.APPLICATION_JSON).get(String.class),
        // listType);
        // LOGGER.warning(yourList.get(0).getName());
        // LOGGER.warning((Supplier<String>)
        // mapper.readValue(target.request(MediaType.APPLICATION_JSON).get(String.class).getAsJsonArray(),
        // Computer.class));
        // ResourceConfig rc = new ResourceConfig();
        // rc.registerClasses(Computer.class);
        //
        // try {
        // HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
        // server.start();
        //
        // System.out.println(String.format("Jersey app started with WADL available at "
        // + "%sapplication.wadl\nHit enter to stop it...", BASE_URI, BASE_URI));
        //
        // System.in.read();
        // server.shutdownNow();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
    //
    // private final WebTarget target =
    // ClientBuilder.newClient().target("http://localhost:8080/Webapp/wsdashboard/");
    //
    // public List<Computer> getAllComputers() {
    // return (List<Computer>) target.request().get(Computer.class);
    // }

}
