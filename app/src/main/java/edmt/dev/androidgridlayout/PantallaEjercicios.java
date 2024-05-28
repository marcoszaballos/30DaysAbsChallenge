package edmt.dev.androidgridlayout;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import static android.R.color.darker_gray;

public class PantallaEjercicios extends AppCompatActivity {

    private boolean preparate = true;
    private long millisParaAcabar;
    private CountDownTimer cuentaAtras;
    private String contadorActual;
    private TextView segundero;
    private Button reanudarBtn;
    private boolean finalEntrenamiento;
    private TextView ejercicioActualTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ejercicios);

        TextView ejercicioActual = (TextView)findViewById(R.id.ejercicioActual);
        segundero = (TextView)findViewById(R.id.segundero);
        reanudarBtn = (Button) findViewById(R.id.reanudarButton);
        if(getIntent() != null)
        {
            String[] ejercicios = getIntent().getStringArrayExtra("ejercicios");

            int[] contador = {0};
            int numEjercicios = ejercicios.length;

            rutina(ejercicios, contador, numEjercicios, false);
        }
    }

    /**
     * Método que cambia modifica los TextView para ir mostrando los diferentes ejercicios
     * @param ejercicios      Colección de String con los ejercicios
     * @param contador        Contador de ejercicios restantes
     * @param numEjercicios   Cantidad de ejercicios del entreno (y número de iteraciones del método)
     * @param descanso        Boolean para saber si la pantalla actual es descanso o rutina
     */
    public void rutina(final String[] ejercicios, final int[] contador, final int numEjercicios, final boolean descanso){
        //TextViews
        ejercicioActualTV = (TextView)findViewById(R.id.ejercicioActual);
        final TextView segundero = (TextView)findViewById(R.id.segundero);
        final TextView ejercicioSiguienteTV = (TextView)findViewById(R.id.ejercicioSiguiente);
        final int millisEjercicio = 3000;
        final int millisDescanso = 3000;    //60000 - millisEjercicio;

        //Mensaje de "PREPÁRATE 3,2,1..."
        if(contador[0] == 0 && preparate){
            preparate = false;
            contadorActual = "preparate";

            ejercicioActualTV.setText("PREPÁRATE");
            ejercicioSiguienteTV.setText(ejercicios[contador[0]]);

            cuentaAtras = new CountDownTimer(5000,1000){
                //Código que se ejecuta cada segundo
                public void onTick(long millisUntilFinished){
                    segundero.setText("" + ((millisUntilFinished) / 1000));
                    millisParaAcabar = millisUntilFinished;
                }
                //Código que se ejecuta cuando acaba onTick
                public void onFinish(){
                    reproducirSonido();
                    rutina(ejercicios, contador, numEjercicios, false);
                }
            }.start();
            reanudarBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    reanudarContador(ejercicios, contador, numEjercicios, descanso, ejercicioSiguienteTV);
                    reanudarBtn.setVisibility(View.INVISIBLE);
                }
            });
        }else{
            //Código que se ejecuta si la pantalla actual es la de descanso
            if(descanso){
                contadorActual = "descanso";
                ejercicioActualTV.setText("DESCANSO");
                try{
                    ejercicioSiguienteTV.setText(ejercicios[contador[0]+1]);
                }catch(Exception patata){                                       //try-catch para evitar Out Of Bounds en la última vuelta
                    ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");
                    cambiarColorCardView();
                    Log.i("CAMBIO DE COLOR", "CATCH");
                }
                cuentaAtras = new CountDownTimer(millisDescanso,1000){
                    //Código que se ejecuta cada segundo
                    public void onTick(long millisUntilFinished){
                        segundero.setText("" + ((millisUntilFinished) / 1000));
                        millisParaAcabar = millisUntilFinished;
                    }
                    //Código que se ejecuta cuando acaba onTick
                    public void onFinish(){
                        if (contador[0]<=numEjercicios){
                            ++contador[0];
                            reproducirSonido();
                            rutina(ejercicios, contador, numEjercicios, false);
                        }
                    }
                }.start();
                reanudarBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        reanudarContador(ejercicios, contador, numEjercicios, descanso, ejercicioSiguienteTV);
                        reanudarBtn.setVisibility(View.INVISIBLE);
                    }
                });
            }
            //Código que se ejecuta si la pantalla actual es de rutina
            else{
                finalEntrenamiento = contador[0] == numEjercicios-1;
                //Set de los TextView del ejercicios actual y siguiente
                if(finalEntrenamiento){
                    ejercicioActualTV.setText(ejercicios[contador[0]]);
                    ejercicioSiguienteTV.setText("FIN DEL ENTRENO");
                    cambiarColorCardView();
                }else{
                    ejercicioActualTV.setText(ejercicios[contador[0]]);
                    try{
                        ejercicioSiguienteTV.setText(ejercicios[contador[0]+1]);
                    }catch(Exception patata){                                       //try-catch para evitar Out Of Bounds en la última vuelta
                        ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");
                        cambiarColorCardView();
                        Log.i("CAMBIO DE COLOR", "CATCH");
                    }
                }

                contadorActual = "ejercicio";
                cuentaAtras = new CountDownTimer(millisEjercicio,1000){
                    //Código que se ejecuta cada segundo
                    public void onTick(long millisUntilFinished){
                        segundero.setText("" + ((millisUntilFinished) / 1000));
                        millisParaAcabar = millisUntilFinished;
                    }
                    //Código que se ejecuta cuando acaba onTick
                    public void onFinish(){
                        reproducirSonido();
                        if(finalEntrenamiento){
                            ejercicioActualTV.setText("FINAL DEL ENTRENO");
                            segundero.setText("0");
                            ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");
                            //cambiarColorCardView();
                            Log.i("CAMBIO DE COLOR", "ON FINISH");

                        }else if (contador[0]<numEjercicios){
                            rutina(ejercicios, contador, numEjercicios, true);
                        }
                    }
                }.start();
                reanudarBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        reanudarContador(ejercicios, contador, numEjercicios, descanso, ejercicioSiguienteTV);
                        reanudarBtn.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
    }

    public PantallaEjercicios() {
        super();
    }

    /**
     * Métodos para pedir confirmación para salir de la pantalla de ejercicios.     BOTONES DE ATRÁS, HOME Y APPS RECIENTES
     */
    //Para cuando se pulsa el botón de ir atrás
    @Override
    public void onBackPressed() {
        if (!finalEntrenamiento) {
            cuentaAtras.cancel();
            new AlertDialog.Builder(this)
                .setMessage("Eres fuerte \uD83D\uDCAA ¿Seguro que quieres salir?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Button reanudarBtn = (Button) findViewById(R.id.reanudarButton);
                        reanudarBtn.setVisibility(View.VISIBLE);
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        PantallaEjercicios.super.onBackPressed();
                        finish();
                    }
                }).create().show();
        } else {
            finish();
        }
    }
    //Para cuando se pulsan los botones HOME o APLICACIONES RECIENTES
    @Override
    protected void onUserLeaveHint(){
        if (!finalEntrenamiento) {
            cuentaAtras.cancel();
            new AlertDialog.Builder(this)
                    .setMessage("Eres fuerte \uD83D\uDCAA ¿Seguro que quieres salir?")
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Button reanudarBtn = (Button) findViewById(R.id.reanudarButton);
                            reanudarBtn.setVisibility(View.VISIBLE);
                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            PantallaEjercicios.super.onUserLeaveHint();
                            finish();
                        }
                    }).create().show();
        } else {
            finish();
        }
    }

    /**
     * Método para reanudar el contador
     * @param ejercicios
     * @param contador
     * @param numEjercicios
     * @param descanso
     * @param ejercicioSiguienteTV
     */
    private void reanudarContador(final String[] ejercicios, final int[] contador, final int numEjercicios, final boolean descanso,
                                  final TextView ejercicioSiguienteTV) {
        //Hacer un switch case y un string para cada uno de los contadores que pueden haber en pantalla,
        // y ejecutar un codigo para cada contador

        switch (contadorActual) {
            case "preparate":
                cuentaAtras = new CountDownTimer(millisParaAcabar, 1000) {
                    //Código que se ejecuta cada segundo
                    public void onTick(long millisUntilFinished) {
                        segundero.setText("" + ((millisUntilFinished) / 1000));
                        millisParaAcabar = millisUntilFinished;
                    }

                    //Código que se ejecuta cuando acaba onTick
                    public void onFinish() {
                        rutina(ejercicios, contador, numEjercicios, false);
                    }
                }.start();
                break;


            case "descanso":
                try {
                    ejercicioSiguienteTV.setText(ejercicios[contador[0] + 1]);
                } catch (Exception patata) {                                       //try-catch para evitar Out Of Bounds en la última vuelta
                    ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");
                    cambiarColorCardView();
                    Log.i("CAMBIO DE COLOR", "CATCH");
                }
                cuentaAtras = new CountDownTimer(millisParaAcabar, 1000) {
                    //Código que se ejecuta cada segundo
                    public void onTick(long millisUntilFinished) {
                        segundero.setText("" + ((millisUntilFinished) / 1000));
                        millisParaAcabar = millisUntilFinished;
                    }

                    //Código que se ejecuta cuando acaba onTick
                    public void onFinish() {
                        if (contador[0] <= numEjercicios) {
                            ++contador[0];
                            rutina(ejercicios, contador, numEjercicios, false);
                        }
                    }
                }.start();
                break;


            case "ejercicio":
                finalEntrenamiento = contador[0] == numEjercicios - 1;
                if (finalEntrenamiento) {
                    ejercicioActualTV.setText(ejercicios[contador[0]]);
                    ejercicioSiguienteTV.setText("FIN DEL ENTRENO");

                    cambiarColorCardView();
                } else {
                    ejercicioActualTV.setText(ejercicios[contador[0]]);
                    try {
                        ejercicioSiguienteTV.setText(ejercicios[contador[0] + 1]);
                    } catch (Exception patata) {                                       //try-catch para evitar Out Of Bounds en la última vuelta
                        ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");
                        cambiarColorCardView();
                        Log.i("CAMBIO DE COLOR", "CATCH");
                    }
                }

                cuentaAtras = new CountDownTimer(millisParaAcabar, 1000) {
                    //Código que se ejecuta cada segundo
                    public void onTick(long millisUntilFinished) {
                        segundero.setText("" + ((millisUntilFinished) / 1000));
                        millisParaAcabar = millisUntilFinished;
                    }

                    //Código que se ejecuta cuando acaba onTick
                    public void onFinish() {
                        if (finalEntrenamiento) {
                            ejercicioActualTV.setText("FINAL DEL ENTRENO");
                            segundero.setText("0");
                            ejercicioSiguienteTV.setText("FINAL DEL ENTRENO");

                            cambiarColorCardView();
                            Log.i("CAMBIO DE COLOR", "ON FINISH");
                        } else if (contador[0] <= numEjercicios) {
                            rutina(ejercicios, contador, numEjercicios, true);
                        }
                    }
                }.start();
                break;
        }
    }

    /**
     * Cambiar el color del CardView
     */
    public void cambiarColorCardView(){
        GridLayout mainGrid = ((ClaseGlobal) this.getApplication()).getMainGrid();
        int indiceDia = ((ClaseGlobal) this.getApplication()).getIndice();
        //Obtenemos los dias completados. Llamando al método getDiasCompletados de MainActivity se obtiene nullPointer >:(
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        int diasCompletados = preferencias.getInt("diasCompletados",0);

        if(indiceDia == diasCompletados) {
            CardView cardView = (CardView) mainGrid.getChildAt(indiceDia);

            cardView.setCardBackgroundColor(getResources().getColor(darker_gray));
            SharedPreferences.Editor editor = preferencias.edit();
            editor.clear();
            editor.putInt("diasCompletados", diasCompletados + 1);
            editor.commit();
        }
    }

    /**
     * Método para cambiar el color del grid de los dias completados
     * @param diasCompletados
     * @param mainActivity
     */
    public void cambiarColorCardView(int diasCompletados, MainActivity mainActivity){
        GridLayout mainGrid = ((ClaseGlobal) mainActivity.getApplication()).getMainGrid();

        for(int i = 0; i<diasCompletados; ++i){
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setCardBackgroundColor(Color.parseColor("#AAAAAA"));
        }
    }

    private void reproducirSonido(){
        MediaPlayer sonido = MediaPlayer.create(this, R.raw.campana);
        sonido.start();
    }
}
