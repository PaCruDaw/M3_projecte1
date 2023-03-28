public class Estadistiques {

    public static float suma (float[] v_entrada) {
        float suma_total = 0;
        for (int i = 0; i < v_entrada.length; i++) {
            suma_total =+ v_entrada[i];
        }
        return suma_total;
    }
    
    public static float media (float[] v_entrada ) {
        float media = 0;
        float suma_total = suma(v_entrada);
        media = (suma_total/v_entrada.length);
        return media;
    }

    public static float major (float[] v_entrada) {
     
        //inicialitzem el maxim valor amb el primer valor del vector
        float max_valor = v_entrada[0];
    
        for (int i = 1; i < v_entrada.length; i++) {
            if (max_valor < v_entrada[i]) {
                max_valor = v_entrada[i];
            }
        }
        return max_valor;
    }

    public static float menor (float[] v_entrada) {
     
        //inicialitzem el maxim valor amb el primer valor del vector
        float min_valor = v_entrada[0];
    
        for (int i = 1; i < v_entrada.length; i++) {
            if (min_valor > v_entrada[i]) {
                min_valor = v_entrada[i];
            }
        }
        return min_valor;
    }

}
