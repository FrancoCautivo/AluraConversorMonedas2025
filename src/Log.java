import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class Log {
    private static final String LOG_FILE = "conversiones_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String monedaOrigen;
    private String monedaDestino;
    private double montoOrigen;
    private double montoResultado;
    private double tasaCambio;
    private LocalDateTime horaInicio;
    private LocalDateTime horaInicioConsulta;
    private LocalDateTime horaConexionAPI;
    private Duration tiempoConexion;
    private boolean respuestaExitosa;
    private String mensajeError;

    public Log() {
        File archivo = new File(LOG_FILE);
        boolean archivoExiste = archivo.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, archivoExiste))) {
            if (!archivoExiste) {
                writer.println("====================== REGISTRO DE CONVERSIONES ======================");
                writer.println("Fecha de creación del log: " + LocalDateTime.now().format(formatter));
                writer.println("===================================================================");
            } else {
                writer.println("\n\n====================== NUEVA SESIÓN ======================");
                writer.println("Fecha de inicio de sesión: " + LocalDateTime.now().format(formatter));
                writer.println("=======================================================");
            }
        } catch (IOException e) {
            System.err.println("Error al inicializar el archivo de log: " + e.getMessage());
        }
    }

    public void registrarInicio(LocalDateTime tiempo) {
        this.horaInicio = tiempo;
        escribirLog("INICIO DE APLICACIÓN: " + tiempo.format(formatter));
    }

    public void registrarInicioConsulta(LocalDateTime tiempo, String monedaOrigen, String monedaDestino, double monto) {
        this.horaInicioConsulta = tiempo;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.montoOrigen = monto;

        escribirLog("\n----- NUEVA CONSULTA -----");
        escribirLog("Hora de inicio de consulta: " + tiempo.format(formatter));
        escribirLog("Moneda origen: " + monedaOrigen);
        escribirLog("Moneda destino: " + monedaDestino);
        escribirLog("Monto a convertir: " + monto);
    }

    public void registrarConexionAPI(LocalDateTime tiempo) {
        this.horaConexionAPI = tiempo;
        escribirLog("Hora de conexión con API: " + tiempo.format(formatter));
    }

    public void registrarTiempoConexion(Duration tiempo) {
        this.tiempoConexion = tiempo;
        escribirLog("Tiempo de conexión con API: " + tiempo.toMillis() + " ms");
    }

    public void registrarRespuestaAPI(boolean exitosa, String mensaje) {
        this.respuestaExitosa = exitosa;
        this.mensajeError = mensaje;

        if (exitosa) {
            escribirLog("Respuesta API: EXITOSA");
        } else {
            escribirLog("Respuesta API: ERROR - " + mensaje);
        }
    }

    public void registrarResultado(double resultado, double tasa) {
        this.montoResultado = resultado;
        this.tasaCambio = tasa;

        escribirLog("Monto resultante: " + resultado);
        escribirLog("Tasa de cambio aplicada: " + tasa);
    }

    public void registrarError(String mensaje) {
        escribirLog("ERROR: " + mensaje);
    }

    public void registrarSalida(LocalDateTime tiempo) {
        escribirLog("\nHora de salida de la aplicación: " + tiempo.format(formatter));
        escribirLog("Tiempo total de ejecución: " + Duration.between(horaInicio, tiempo).toSeconds() + " segundos");
        escribirLog("===================================================================");
    }

    private void escribirLog(String mensaje) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(mensaje);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}