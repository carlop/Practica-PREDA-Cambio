import java.io.IOException;
import java.util.List;

/**
 * Clase principal.
 *
 * @author Francisco Carlos López Porcel
 * @version 0.1
 */
public class Cambio
{
    /**
     * M�todo main
     * @throws IOException 
     */
    public static void main (String[] args) throws IOException
    {
        // Lee los argumentos de entrada
        ProcesadorArgumentos argumentos = new ProcesadorArgumentos(args);
        // Procesa los argumentos
        argumentos.procesarArgumentos();
        // Archivo de entrada
        String archivoEntrada = argumentos.getArchivoEntrada();
        // Archivo de salida
        String archivoSalida = argumentos.getArchivoSalida();
        
        // Muestra la ayuda
        if (argumentos.getAyuda()) {
            argumentos.mostrarAyuda();
        }
        
        // Si hay archivos de entrada se opera
        if (archivoEntrada != null) {
            // Lee el archivo de entrada
            List<String> contenidoArchivo = ManejadorArchivos.leerArchivo(archivoEntrada);
            
            // Lee el contenido del archivo de entrada
            int[] monedas = new int[contenidoArchivo.size() - 2];
            for(int i = 2; i < contenidoArchivo.size(); i++) {
                monedas[i-2] = Integer.valueOf(contenidoArchivo.get(i));
            }
            
            // Inicia el algoritmo
            CambioMonedas cambioMonedas = new CambioMonedas(Integer.valueOf(contenidoArchivo.get(0)), Integer.valueOf(contenidoArchivo.get(1)), monedas);
            // Comprueba los datos
            cambioMonedas.compruebaDatos();
            // Calcula el número mínimo de monedas
            cambioMonedas.getCambio(argumentos.getTraza());
            cambioMonedas.getMonedas();
            // Si hay hay archivo de salida guarda del resultado en él si no lo muestra en pantalla
            if (archivoSalida != null) {
                String contenidoArchivoAGuardar = cambioMonedas.getCambioResultado();
                ManejadorArchivos.guardarArchivo(archivoSalida, contenidoArchivoAGuardar);
            } else {
                System.out.println(cambioMonedas.getCambioResultado());
            }
        }
    }
}