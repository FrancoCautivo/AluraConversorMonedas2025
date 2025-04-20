# Conversor de Monedas

Este proyecto es un conversor de monedas desarrollado en Java que permite realizar conversiones entre 40 diferentes divisas de todo el mundo. Utiliza una API externa para obtener las tasas de cambio actualizadas y ofrece una interfaz de línea de comandos sencilla para su uso.

## Estructura del Proyecto

El proyecto está dividido en 4 clases principales, cada una con una responsabilidad específica:

1. **Principal**: Es la clase principal del programa que contiene el método `main` y maneja la interfaz de usuario. Se encarga de mostrar los menús, recoger la entrada del usuario y coordinar el flujo del programa.

2. **ConversorMonedas**: Esta clase se encarga de la lógica de negocio relacionada con la conversión de monedas. Coordina la obtención de las tasas de cambio y realiza los cálculos necesarios para la conversión.

3. **APIClient**: Se ocupa de la comunicación con la API externa ([ExchangeRate-API](https://www.exchangerate-api.com/)). Realiza las peticiones HTTP, procesa las respuestas y maneja los posibles errores de conexión.

4. **Log**: Esta clase se encarga de registrar todas las acciones y eventos que ocurren durante la ejecución del programa. Guarda información detallada sobre cada consulta, tiempos de respuesta, errores y resultados en un archivo de texto.

## Funcionalidad

El conversor de monedas ofrece las siguientes características:

- Conversión entre 40 monedas diferentes de todo el mundo, incluyendo las principales divisas y monedas latinoamericanas.
- Obtención de tasas de cambio en tiempo real mediante la conexión a una API externa.
- Interfaz de usuario sencilla por línea de comandos.
- Sistema de registro (log) que guarda información detallada de cada operación.
- Manejo de errores para conexiones fallidas o entradas incorrectas.

## Monedas Disponibles

El programa permite convertir entre las siguientes 40 monedas:

1. Dólar estadounidense (USD)
2. Euro (EUR)
3. Peso argentino (ARS)
4. Boliviano (BOB)
5. Real brasileño (BRL)
6. Peso chileno (CLP)
7. Peso colombiano (COP)
8. Guaraní paraguayo (PYG)
9. Sol Peruano (PEN)
10. Peso uruguayo (UYU)
11. Bolívar venezolano (VES)
12. Lempira hondureño (HNL)
13. Quetzal guatemalteco (GTQ)
14. Peso dominicano (DOP)
15. Colon costarricense (CRC)
16. Peso mexicano (MXN)
17. Dólar canadiense (CAD)
18. Dólar jamaiquino (JMD)
19. Peso cubano (CUP)
20. Dólar bahameño (BSD)
21. Libra esterlina de Reino unido (GBP)
22. Franco Suizo (CHF)
23. Lira turca (TRY)
24. Rublo ruso (RUB)
25. Shekel israelí (ILS)
26. Libra egipcia (EGP)
27. Riyal de Arabia Saudita (SAR)
28. Dirham de Emiratos Árabes (AED)
29. Riyal de Qatar (QAR)
30. Rand sudafricano (ZAR)
31. Rupia indonesia (IDR)
32. Rupia india (INR)
33. Baht tailandés (THB)
34. Won surcoreano (KRW)
35. Dólar taiwanés (TWD)
36. Dólar de Hong Kong (HKD)
37. Yuan chino (CNY)
38. Yen japonés (JPY)
39. Dólar australiano (AUD)
40. Dólar neozelandés (NZD)

## Guía de Uso

### 1. Iniciar el programa

Al iniciar el programa, verás una pantalla de bienvenida que te indica cómo comenzar.

![Pantalla de inicio](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/inicio.png)

### 2. Ingresar el monto a convertir

Introduce la cantidad que deseas convertir y presiona Enter. Si deseas salir del programa, ingresa 0.

![Ingreso de monto](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/ingreso.png)

### 3. Seleccionar la moneda de origen

Elige la moneda que posees actualmente introduciendo el número correspondiente y presiona Enter.

![Selección de moneda origen](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/menu1.png)

### 4. Seleccionar la moneda de destino

Elige la moneda a la cual deseas convertir introduciendo el número correspondiente y presiona Enter.

![Selección de moneda destino](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/menu2.png)

### 5. Ver el resultado de la conversión

El programa mostrará el resultado de la conversión, incluyendo el monto convertido y la tasa de cambio aplicada.

![Resultado de conversión](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/resultado.png)

### 6. Continuar o salir

Presiona Enter para realizar otra conversión o introduce 0 en cualquier momento para salir del programa.

![Salida del programa](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/salida.png)

## Sistema de Registro (Log)

El programa incluye un sistema de registro que guarda información detallada sobre cada operación en un archivo llamado `conversiones_log.txt`. La información registrada incluye:

- Fecha y hora de inicio de la aplicación
- Detalles de cada consulta (moneda origen, destino, monto)
- Tiempos de conexión con la API
- Resultados de las conversiones
- Errores que puedan ocurrir
- Tiempo total de ejecución

Este registro es útil para auditar el uso del programa, identificar problemas y analizar patrones de uso.

![Ejemplo de log](https://github.com/FrancoCautivo/AluraConversorMonedas2025/blob/main/screenshots/log.png)

NOTA: Si por alguna razón el archivo `conversiones_log.txt` no estuviese creado al ejecutar por primera vez el proyecto, el mismo código se encarga de crearlo y completarlo.

## Requisitos Técnicos

- Java 11 o superior
- Conexión a Internet para acceder a la API de tasas de cambio
  Para este proyecto se ocupo ([ExchangeRate-API](https://www.exchangerate-api.com/)) pero es posible cambiarla por cualquier otra API
- Biblioteca GSON para el procesamiento de JSON (incluida en el proyecto)

## Descarga y Personalización

Puedes descargar el proyecto completo en formato ZIP desde el siguiente enlace:

[Descargar Conversor de Monedas](https://github.com/FrancoCautivo/AluraConversorMonedas2025/archive/refs/heads/main.zip)

El proyecto está disponible para ser modificado y ampliado según tus necesidades. Algunas ideas para mejoras futuras:

- Añadir una interfaz gráfica
- Implementar históricos de tasas de cambio
- Agregar más monedas
- Crear gráficos de evolución de las tasas

## Licencia

Este proyecto está disponible para uso libre. Puedes modificarlo y distribuirlo como desees.

## Desarrollador

Desarrollado por Francisco Cautivo como parte del programa ONE de Oracle y Alura Latam, 2025.
