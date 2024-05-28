package edmt.dev.androidgridlayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.R.color.darker_gray;
import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        ((ClaseGlobal) this.getApplication()).setMainGrid(mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
        cargarPreferencias(this);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int indiceDia = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (indiceDia <= getDiasCompletados()){
                        Intent pantallaIntermedia = new Intent(MainActivity.this,PantallaIntermedia.class);
                        pantallaIntermedia.putExtra("indiceDia", ""+indiceDia);
                        startActivity(pantallaIntermedia);
                    } else {
                        String textoMensaje = "No puedes acceder a este entrenamiento aun";
                        Toast toast = Toast.makeText(getApplicationContext(), textoMensaje, Toast.LENGTH_LONG);
                        toast.show();
                    }



                    /**
                     * Marcamos el botón diferente
                     * Esto sería conveniente hacerlo una vez haya finalizado el entreno
                     * (PantallaEjercicios --> Línea 121 aprox, después de "FIN DEL ENTRENO"
                     * Pero no sé como mierdas hacer una puta variable global para que llegue hasta hí el grid
                     */
                    //cardView.setCardBackgroundColor(Color.DKGRAY);
                }
            });
        }
    }

    private void cargarPreferencias(MainActivity mainActivity){
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> datosPreferencias = preferencias.getAll();
        //Evaluamos la dificultad y cambiamos los segundos y el valor del TV de dificultad

        //evaluamos cuantos dias han sido completamos y cambiamos el color de las celdas del grid
        SharedPreferences.Editor editor = preferencias.edit();
        int i = preferencias.getInt("diasCompletados",0);        //El segundo parámetro es lo que se devuelve si el objeto no existe
        PantallaEjercicios pantallaEjercicios = new PantallaEjercicios();
        pantallaEjercicios.cambiarColorCardView(i, mainActivity);
        /** IDEA: ES MEJOR HACER UN STRING QUE CUENTE LOS DIAS COMPLETADOS (SE SUMARÁ 1 CUANDO SE COMPLETE UN DIA)
         *      o HACER UN MAP CON LOS INDICES DE LOS DIAS COMPLETADOS?
         * 1.- Crear las variables si no existen (dificultad = normal, diasCompletados = nada)
         * 2.- Leer las variables si existen
         *      - Cambiar la dificultad (tanto TV como segundos)
         *      - Pintar los dias que ya estén completados y bloquear los que no se pueden hacer
         * 3.- Cuando se complete un dia --> Añadir el indice de ese día a las preferencias y desbloquear el siguiente dia
         *
         */
    }

    /**
     * Método que devuelve el número de dias completados
     * @return diasCompletados
     */
    public int getDiasCompletados(){
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferencias.edit();
        int diasCompletados = preferencias.getInt("diasCompletados",0);
        return diasCompletados;
    }
}

/*
    Las rutinas deberían ser simétricas entre sí, es decir, empezar todas con X ejercicios faciles, Y intermedios y Z difíciles,
    para que cuadren los tiempos de ejercicio y que sea un array UNIdimensional, de otra manera tendría hacer un array BIdimensional
    y pensar un tiempo para cada día.

    Otra opción que se me ocurre es el clasificar los ejercicios por tipo (dificultad) y establecer un tiempo estándar para cada tipo de ejercicio
    siendo, por ejemplo, un ejercicio de tipo 1 un ejercicio facil, con un tiempo de ejecución de 30", y un ejercicio tipo 3 un ejercicio
    más exigente, con un tiempo de ejecución de 10", por ejemplo.
    Supongo que lo más óptimo para esto es un array TRIdimensional (Xd), añadiendo el tipo de cada ejercicio, sería más limpio hacer una base de datos,
    pero creo que se vería afectado el rendimiento al tener que lanzar una query cada vez que haya que cargar el tiempo de un ejercicio. De manera
    contraria, con un array tridimensional habría que comerse un switch-case (¿Un switch es más óptimo que hacer N condicionales para saber el tipo?)

    - IDEA. Hacer un array bidimensional con TODOS los ejercicios y su tipo/tiempo, por ejemplo:
        ejercicios[0][0] = Leg raises;
        ejercicios[0][1] = 15;      //15"

        Y el ACTUAL array de ejercicios, contendrá los diferentes ejercicios de este array que sean para ese día, por ejemplo:
        ejerciciosDia[] = {
            ejercicios[0],
            ejercicios[1],
            ...
            ejercicios[n]
        }
 */

/*
    ¿Cómo de buena idea es setear como variables globales las variables que se pasan continuamente en los métodos?
    Esto serviría para hacer rl código más limpio y simple.
    ¿Cuál es el ciclo de vida de las variables globales?
 */