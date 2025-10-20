package si;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RatesApi {

    private List<Country> vatRates = new ArrayList<>();
    @ConfigProperty(name = "rates.url")
    String dataUrl;


    @PostConstruct
    void init() {
        HttpResponse<String> response;
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(dataUrl)).GET().build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new RuntimeException("Source isn't good" + response.statusCode());
                }
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                JsonNode ratesNode = root.get("rates");
                Map<String, Country> ratesMap = mapper.convertValue(ratesNode, new TypeReference<>() {
                });

                vatRates = ratesMap.entrySet().stream()
                        .filter(c -> ratesNode.get(c.getKey()).get("iso_duplicate_of") == null)
                        .map(Map.Entry::getValue)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
            catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Failed to load...", e);
        }}

    @GET
    @Path("/highest-standard-vat")
    public List<Country> getHighestStandardVat() {
        List<Country> sort = vatRates.stream()
                .sorted(Comparator.comparingDouble(Country::getStandard_rate).reversed())
                .collect(Collectors.toList());

        if (sort.size() <= 3) return sort;

        return sort.stream()
                .filter(c -> c.getStandard_rate() >= sort.get(2).getStandard_rate())
                .collect(Collectors.toList());
    }

    @GET
    @Path("/lowest-reduced-vat")
    public List<Country> getLowestReducedVat() {
        List<Country> sort = vatRates.stream().filter(c -> c.getReduced_rate() != null)
                .sorted(Comparator.comparingDouble(Country::getReduced_rate))
                .collect(Collectors.toList());

        if (sort.size() <= 3) return sort;

        return sort.stream()
                .filter(c -> c.getReduced_rate() <= sort.get(2).getReduced_rate())
                .collect(Collectors.toList());
    }
}