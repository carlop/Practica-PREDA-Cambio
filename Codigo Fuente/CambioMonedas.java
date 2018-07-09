/**
 * @author Francisco Carlos López Porcel
 * @version 0.1
 */
public class CambioMonedas {
    // Cantidad a devolver
    private int cantidadADevolver;

    // Cantidad de monedas disponibles
    private int cantidadMonedas;

    // Array con el tipo de monedas que tenemos
    private int[] monedas;

    // Tabla donde se guardan los resultados
    private int[][] tablaCambio;
    
    // Array con la cantidad de cada tipo de monedas que compone la vuelta
    private int[] monedasCambio;
    
    // Resultado de la traza si se indica que existe
    private String trazaResultado;

    /**
     * Constructor
     */
    public CambioMonedas(int cantidadADevolver, int cantidadMonedas, int[] monedas) {
        this.cantidadADevolver = cantidadADevolver;
        this.cantidadMonedas = cantidadMonedas;
        this.monedas = monedas;
        tablaCambio = new int[monedas.length + 1][cantidadADevolver + 1];
        monedasCambio = new int[monedas.length];
        trazaResultado = "";
    }

    /**
     * Algoritmo de programación dinámica que calcula el número mínimo de monedas que resuelve el problema
     * @param traza
     */
    public void getCambio(Boolean traza) {
        if (traza) {
            trazaResultado += "Cantidad a devolver: " + cantidadADevolver + System.getProperty("line.separator");
            trazaResultado += "Nº de monedas: " + cantidadMonedas + System.getProperty("line.separator");
            trazaResultado += "Comienza el relleno de la tabla: " + System.getProperty("line.separator");
        }

        // Se pone a 0 la primera columna
        for (int i = 0; i <= monedas.length; i++) {
            tablaCambio[i][0] = 0;
        }
        if (traza) rellenaTraza();

        // Se pone en un número alto la primera fila
        for (int j = 1; j <= cantidadADevolver; j++) {
            tablaCambio[0][j] = Integer.MAX_VALUE;
        }
        if (traza) rellenaTraza();

        // Rellenamos la tabla
        for (int i = 1; i <= monedas.length; i++) {
            for (int j = 1; j <= cantidadADevolver; j++) {
                if (j < monedas[i-1]) {
                    tablaCambio[i][j] = tablaCambio[i-1][j];
                } else {
                    tablaCambio[i][j] = tablaCambio[i-1][j] < tablaCambio[i][j - monedas[i-1]] + 1
                            ? tablaCambio[i-1][j] : tablaCambio[i][j - monedas[i-1]] + 1;
                }
                if (traza) rellenaTraza();
            }
        }
        if (traza) trazaResultado += "Número mínimo de monedas: " + tablaCambio[monedasCambio.length][cantidadADevolver];
    }

    /**
     * Genera un array con la cantidad de monedas de cada tipo que devuelve la resolución del algoritmo anterior
     * @param traza
     */
    public void getMonedas() {
        int cantidadRestante = cantidadADevolver;
        int i = monedas.length;
        int j = cantidadADevolver;

        while (i > 0) {
            if (tablaCambio[i][j] == tablaCambio[i-1][j]) {
                i = i - 1;
            } else {
                monedasCambio[i - 1] = monedasCambio[i - 1] + 1;
                j = j - monedas[i - 1];
            }
        }
        monedasCambio[0] = tablaCambio[0][cantidadRestante];
    }

    /**
     * Devuelve el número mínimo de monedas
     * @return Solución
     */
    public String getCambioResultado() {
        int[] ja = this.monedasCambio;
        String sol = System.getProperty("line.separator");
        for (int i = 1; i < ja.length; i++) {
            if (ja[i] != 0) {
                sol += ja[i] + " monedas de " + monedas[i] + System.getProperty("line.separator");
            }
        }
        return trazaResultado + sol;
    }

    /**
     * Muestra el valor de cada tabla en la traza cada vez que se va rellenando
     */
    private void rellenaTraza() {
        for (int i = 0; i <= monedasCambio.length; i++) {
            //trazaResultado += i + ". ";
            for (int j = 1; j <= cantidadADevolver; j++) {
                trazaResultado += " " + tablaCambio[i][j];
                if (j == cantidadADevolver) {
                    trazaResultado += System.getProperty("line.separator");
                }
            }
        }
        trazaResultado += System.getProperty("line.separator");
    }
    /*
     * Comprueba que los datos son correctos
     */
    public void compruebaDatos() {
        Boolean monedasF = false;

        if (cantidadMonedas < monedas.length) {
            monedasF = true;
        }

        if (monedasF) {
            System.out.println("Error, la cantidad de monedas indicada");
            System.out.println("y la cantidad real es diferente");
            System.exit(0);
        }
    }
}