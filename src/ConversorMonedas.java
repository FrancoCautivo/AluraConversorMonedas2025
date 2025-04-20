import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.JsonObject;

public class ConversorMonedas {

    private final APIClient apiClient;

    public ConversorMonedas() {
        this.apiClient = new APIClient();
    }

    public double realizarConversion(String monedaBase, String monedaDestino, double cantidad, Log log, LocalDateTime inicioConsulta) {
        log.registrarInicioConsulta(inicioConsulta, monedaBase, monedaDestino, cantidad);

        try {
            System.out.println(" Consultando tasas de cambio actuales...");
            double tasaCambio = obtenerTasaDeCambio(monedaBase, monedaDestino, log, inicioConsulta);

            if (tasaCambio > 0) {
                double resultado = cantidad * tasaCambio;
                log.registrarResultado(resultado, tasaCambio);
                return resultado;
            } else {
                System.out.println(" No se pudo obtener la tasa de cambio en este momento.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println(" Error al realizar la conversión: " + e.getMessage());
            log.registrarError("Error en conversión: " + e.getMessage());
            return -1;
        }
    }

    private double obtenerTasaDeCambio(String monedaBase, String monedaDestino, Log log, LocalDateTime inicioConsulta) {
        try {
            JsonObject jsonResponse = apiClient.obtenerTasasDeCambio(monedaBase, log, inicioConsulta);

            if (jsonResponse != null) {
                JsonObject tasas = jsonResponse.getAsJsonObject("conversion_rates");
                if (tasas.has(monedaDestino)) {
                    return tasas.get(monedaDestino).getAsDouble();
                } else {
                    log.registrarError("Moneda destino no encontrada en la respuesta API");
                }
            }

            return -1;
        } catch (IOException | InterruptedException e) {
            System.out.println(" Error al conectar con la API: " + e.getMessage());
            log.registrarError("Error al conectar con la API: " + e.getMessage());
            return -1;
        }
    }
}
