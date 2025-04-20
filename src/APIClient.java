import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class APIClient {

    private static final String API_KEY = "280034449e52da7b54344c14";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public JsonObject obtenerTasasDeCambio(String monedaBase, Log log, LocalDateTime tiempoInicio) throws IOException, InterruptedException {
        String url = BASE_URL + monedaBase;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        LocalDateTime conexionAPI = LocalDateTime.now();
        log.registrarConexionAPI(conexionAPI);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        LocalDateTime respuestaAPI = LocalDateTime.now();

        Duration tiempoConexion = Duration.between(conexionAPI, respuestaAPI);
        log.registrarTiempoConexion(tiempoConexion);

        if (response.statusCode() == 200) {
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (jsonResponse.get("result").getAsString().equals("success")) {
                log.registrarRespuestaAPI(true, "");
                return jsonResponse;
            } else {
                String error = "Error en respuesta: " + jsonResponse.get("result").getAsString();
                log.registrarRespuestaAPI(false, error);
            }
        } else {
            String error = "Error HTTP: " + response.statusCode();
            log.registrarRespuestaAPI(false, error);
            System.out.println("Error en la respuesta de la API: " + response.statusCode());
            System.out.println(response.body());
        }

        return null;
    }
}