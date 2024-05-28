package edmt.dev.androidgridlayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaIntermedia extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_intermedia);

        myDialog = new Dialog (this);

        if(getIntent() != null)
        {
            //final String[] ejercicios = getIntent().getStringArrayExtra("ejercicios");
            final String indiceDia = getIntent().getStringExtra("indiceDia");                        //Es array porque si se pone como int peta :)
            final String[] ejercicios = ((ClaseGlobal) this.getApplication()).getEjercicios(Integer.parseInt(indiceDia));
            final String[] descripciones = ((ClaseGlobal) this.getApplication()).getDescripciones(Integer.parseInt(indiceDia));
            ((ClaseGlobal) this.getApplication()).setIndice(Integer.parseInt(indiceDia));

            Log.d("indiceDia (length): ", "" + indiceDia);

            final int[] contador = {0};
            final int millisEjercicio = 5000;
            final int millisDescanso = 3000;    //60000 - millisEjercicio;
            final int numEjercicios = ejercicios.length;

            //Seteamos los ejercicios en los TextView
            TextView ejercicio1 = (TextView)findViewById(R.id.ejercicio1);
            TextView ejercicio2 = (TextView)findViewById(R.id.ejercicio2);
            TextView ejercicio3 = (TextView)findViewById(R.id.ejercicio3);
            TextView ejercicio4 = (TextView)findViewById(R.id.ejercicio4);
            TextView ejercicio5 = (TextView)findViewById(R.id.ejercicio5);
            TextView ejercicio6 = (TextView)findViewById(R.id.ejercicio6);

            ejercicio1.setText(ejercicios[0]);
            ejercicio2.setText(ejercicios[1]);
            ejercicio3.setText(ejercicios[2]);
            ejercicio4.setText(ejercicios[3]);
            ejercicio5.setText(ejercicios[4]);

            Button empezar = (Button)findViewById(R.id.EmpezarBtn);
            empezar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pantallaEntrenamiento = new Intent(PantallaIntermedia.this, PantallaEjercicios.class);

                    pantallaEntrenamiento.putExtra("ejercicios", ejercicios);
                    pantallaEntrenamiento.putExtra("indiceDia", indiceDia);
                    Log.i("indicedia", indiceDia);

                    startActivity(pantallaEntrenamiento);
                    finish();
                }
            });

            //eventos onClick del TV de los ejercicios para abrir el PopUp
            ejercicio1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { datosEjercicioParaPopup(ejercicios[0], descripciones[0]); }
            });
            ejercicio2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { datosEjercicioParaPopup(ejercicios[1], descripciones[1]); }
            });
            ejercicio3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { datosEjercicioParaPopup(ejercicios[2], descripciones[2]); }
            });
            ejercicio4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { datosEjercicioParaPopup(ejercicios[3], descripciones[3]); }
            });
            ejercicio5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { datosEjercicioParaPopup(ejercicios[4], descripciones[4]); }
            });
        }
    }



    /**
     * Método que envia los datos del ejercicio al PopUp
     * @param nomEjercicio
     * @param descEjercicio
     */
    private void datosEjercicioParaPopup(String nomEjercicio, String descEjercicio){
        String nombreEjercicio = nomEjercicio;
        String descripcionEjercicio = descEjercicio;
        abrirPopup(nombreEjercicio, descripcionEjercicio);
    }

    /**
     * Método para crear e informar el PopUp
     * @param nombreEjercicio
     * @param descripcionEjercicio
     */
    private void abrirPopup(final String nombreEjercicio, final String descripcionEjercicio){

        myDialog.setContentView(R.layout.popup);
        TextView tvCerrar = myDialog.findViewById(R.id.txtCerrar);

        TextView nomEjercicio = myDialog.findViewById(R.id.nombreEjercicio1);
        nomEjercicio.setText(nombreEjercicio);

        TextView descEjercicio = myDialog.findViewById(R.id.descripcionEjercicio1);
        descEjercicio.setText(descripcionEjercicio);

        tvCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
