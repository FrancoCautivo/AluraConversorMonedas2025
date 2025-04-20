import java.util.Scanner;
import java.time.LocalDateTime;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ConversorMonedas conversor = new ConversorMonedas();
    private static final Log log = new Log();
    private static LocalDateTime inicioAplicacion;

    public static void main(String[] args) {
        inicioAplicacion = LocalDateTime.now();
        log.registrarInicio(inicioAplicacion);

        boolean continuar = true;

        while (continuar) {
            mostrarBienvenida();
            double monto = obtenerMonto();
            if (monto <= 0) {
                if (monto == 0) {
                    mostrarDespedida();
                    log.registrarSalida(LocalDateTime.now());
                    break;
                }
                continue;
            }

            mostrarMenuMonedas("origen");
            int opcionOrigen = obtenerOpcion();
            if (opcionOrigen == 0) {
                mostrarDespedida();
                log.registrarSalida(LocalDateTime.now());
                break;
            } else if (opcionOrigen < 0 || opcionOrigen > 40) {
                System.out.println("Opción inválida. Intente nuevamente.");
                continue;
            }

            mostrarMenuMonedas("destino");
            int opcionDestino = obtenerOpcion();
            if (opcionDestino == 0) {
                mostrarDespedida();
                log.registrarSalida(LocalDateTime.now());
                break;
            } else if (opcionDestino < 0 || opcionDestino > 40) {
                System.out.println("Opción inválida. Intente nuevamente.");
                continue;
            }

            String monedaOrigen = obtenerCodigoMoneda(opcionOrigen);
            String monedaDestino = obtenerCodigoMoneda(opcionDestino);
            String nombreMonedaOrigen = obtenerNombreMoneda(opcionOrigen);
            String nombreMonedaDestino = obtenerNombreMoneda(opcionDestino);

            System.out.println("\n*****************************************************************************************************************************************");
            System.out.println("\n Procesando la conversión solicitada\n");

            LocalDateTime inicioConsulta = LocalDateTime.now();
            double resultado = conversor.realizarConversion(monedaOrigen, monedaDestino, monto, log, inicioConsulta);

            if (resultado >= 0) {
                System.out.printf(" %.2f %s equivalen hoy a %.2f %s\n\n",
                        monto, nombreMonedaOrigen, resultado, nombreMonedaDestino);

                double tasaCambioDirecta = resultado / monto;

                if (tasaCambioDirecta >= 0.01) {
                    System.out.printf(" La tasa de cambio aplicada es : 1 %s equivale a %.2f %s\n\n",
                            monedaOrigen, tasaCambioDirecta, monedaDestino);
                } else {
                    double tasaInversa = monto / resultado;
                    System.out.printf(" La tasa de cambio aplicada es : %.2f %s equivalen a 1 %s\n\n",
                            tasaInversa, monedaOrigen, monedaDestino);
                }
            }

            System.out.println(" Presione Enter para volver al inicio.");
            scanner.nextLine();
        }

        scanner.close();
    }

    private static void mostrarBienvenida() {
        String mensaje =
                "\n****************************************************************************************************************************************" +
                        "\n  Bienvenid@ al conversor de monedas" +
                        "\n\n  Este conversor puede convertir el valor de su moneda entre 40 opciones de diversos países." +
                        "\n\n*****************************************************************************************************************************************" +
                        "\n\n  Para comenzar ingrese el monto de lo que desea convertir y presione Enter" +
                        "\n\n  El monto a convertir es : ";

        System.out.println(mensaje);
    }

    private static void mostrarMenuMonedas(String tipo) {
        String tipoMensaje = tipo.equals("origen") ? "que posee actualmente." : "a la cual desea convertir.";

        String mensaje =
                "\n*****************************************************************************************************************************************" +
                        "\n\n  Ahora elija una opción correspondiente al tipo de moneda " + tipoMensaje +
                        "\n\n  Tiene 40 opciones para elegir" +
                        "\n  " +
                        "\n  1)Dólar estadounidense (USD)   11)Bolívar venezolano (VES)     21)Libra esterlina de Reino unido (GBP)   31)Rupia indonesia (IDR)" +
                        "\n  2)Euro (EUR)                   12)Lempira hondureño (HNL)      22)Franco Suizo (CHF)                     32)Rupia india (INR)" +
                        "\n  3)Peso argentino (ARS)         13)Quetzal guatemalteco (GTQ)   23)Lira turca (TRY)                       33)Baht tailandés (THB)" +
                        "\n  4)Boliviano (BOB)              14)Peso dominicano (DOP)        24)Rublo ruso (RUB)                       34)Won surcoreano (KRW)" +
                        "\n  5)Real brasileño (BRL)         15)Colon costarricense (CRC)    25)Shekel israelí (ILS)                   35)Dólar taiwanés (TWD)" +
                        "\n  6)Peso chileno (CLP)           16)Peso mexicano (MXN)          26)Libra egipcia (EGP)                    36)Dólar de Hong Kong (HKD)" +
                        "\n  7)Peso colombiano (COP)        17)Dólar canadiense (CAD)       27)Riyal de Arabia Saudita (SAR)          37)Yuan chino (CNY)" +
                        "\n  8)Guaraní paraguayo (PYG)      18)Dólar jamaiquino (JMD)       28)Dirham de Emiratos Árabes (AED)        38)Yen japonés (JPY)" +
                        "\n  9)Sol Peruano (PEN)            19)Peso cubano (CUP)            29)Riyal de Qatar (QAR)                   39)Dólar australiano (AUD)" +
                        "\n  10)Peso uruguayo (UYU)         20)Dólar bahameño (BSD)         30)Rand sudafricano (ZAR)                 40)Dólar neozelandés (NZD)" +
                        "\n\n  Escriba el numero de su opción elegida y presione Enter" +
                        "\n  Si desea salir del conversor de monedas escriba 0 y luego presione enter" +
                        "\n\n  Su opción elegida es la : ";

        System.out.println(mensaje);
    }

    private static double obtenerMonto() {
        try {
            String entrada = scanner.nextLine();
            if (entrada.equals("0")) return 0;
            double monto = Double.parseDouble(entrada);
            if (monto <= 0) {
                System.out.println("Error: El monto debe ser mayor que cero. Intente nuevamente.");
                return -1;
            }
            return monto;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
            return -1;
        }
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void mostrarDespedida() {
        System.out.println("\n¡Gracias por usar el conversor de monedas!");
    }

    private static String obtenerCodigoMoneda(int opcion) {
        switch (opcion) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "ARS";
            case 4: return "BOB";
            case 5: return "BRL";
            case 6: return "CLP";
            case 7: return "COP";
            case 8: return "PYG";
            case 9: return "PEN";
            case 10: return "UYU";
            case 11: return "VES";
            case 12: return "HNL";
            case 13: return "GTQ";
            case 14: return "DOP";
            case 15: return "CRC";
            case 16: return "MXN";
            case 17: return "CAD";
            case 18: return "JMD";
            case 19: return "CUP";
            case 20: return "BSD";
            case 21: return "GBP";
            case 22: return "CHF";
            case 23: return "TRY";
            case 24: return "RUB";
            case 25: return "ILS";
            case 26: return "EGP";
            case 27: return "SAR";
            case 28: return "AED";
            case 29: return "QAR";
            case 30: return "ZAR";
            case 31: return "IDR";
            case 32: return "INR";
            case 33: return "THB";
            case 34: return "KRW";
            case 35: return "TWD";
            case 36: return "HKD";
            case 37: return "CNY";
            case 38: return "JPY";
            case 39: return "AUD";
            case 40: return "NZD";
            default: return "";
        }
    }

    private static String obtenerNombreMoneda(int opcion) {
        switch (opcion) {
            case 1: return "Dólar estadounidense (USD)";
            case 2: return "Euro (EUR)";
            case 3: return "Peso argentino (ARS)";
            case 4: return "Boliviano (BOB)";
            case 5: return "Real brasileño (BRL)";
            case 6: return "Peso chileno (CLP)";
            case 7: return "Peso colombiano (COP)";
            case 8: return "Guaraní paraguayo (PYG)";
            case 9: return "Sol Peruano (PEN)";
            case 10: return "Peso uruguayo (UYU)";
            case 11: return "Bolívar venezolano (VES)";
            case 12: return "Lempira hondureño (HNL)";
            case 13: return "Quetzal guatemalteco (GTQ)";
            case 14: return "Peso dominicano (DOP)";
            case 15: return "Colon costarricense (CRC)";
            case 16: return "Peso mexicano (MXN)";
            case 17: return "Dólar canadiense (CAD)";
            case 18: return "Dólar jamaiquino (JMD)";
            case 19: return "Peso cubano (CUP)";
            case 20: return "Dólar bahameño (BSD)";
            case 21: return "Libra esterlina de Reino unido (GBP)";
            case 22: return "Franco Suizo (CHF)";
            case 23: return "Lira turca (TRY)";
            case 24: return "Rublo ruso (RUB)";
            case 25: return "Shekel israelí (ILS)";
            case 26: return "Libra egipcia (EGP)";
            case 27: return "Riyal de Arabia Saudita (SAR)";
            case 28: return "Dirham de Emiratos Árabes (AED)";
            case 29: return "Riyal de Qatar (QAR)";
            case 30: return "Rand sudafricano (ZAR)";
            case 31: return "Rupia indonesia (IDR)";
            case 32: return "Rupia india (INR)";
            case 33: return "Baht tailandés (THB)";
            case 34: return "Won surcoreano (KRW)";
            case 35: return "Dólar taiwanés (TWD)";
            case 36: return "Dólar de Hong Kong (HKD)";
            case 37: return "Yuan chino (CNY)";
            case 38: return "Yen japonés (JPY)";
            case 39: return "Dólar australiano (AUD)";
            case 40: return "Dólar neozelandés (NZD)";
            default: return "";
        }
    }
}