package edmt.dev.androidgridlayout;

import android.app.Application;
import android.util.Log;
import android.widget.GridLayout;

public class ClaseGlobal extends Application {

    private GridLayout mainGrid;
    private int indice;

    //Ejercicios
    private String [] listaEjercicios = {"Jumping Jack's", "High Knee Taps", "Mountain Climbers", "Seated In & Outs", "Chair Sit Ups",
            "Bicycles", "Plank Knees to Elbow", "Plank", "Low to High Plank", "Leg Raises", "Leg Flutters", "Boat Hold", "Leg Hold",
            "Side Plank Up & Down", "Side Plank", "Russian Twists", "Heel Touch"};

    //Descripciones
    private String [] descEjercicios = {
            "Saltar separando las piernas y levantando los brazos, volviéndolos a juntar en el siguiente salto.",
            "Levantar las rodillas de manera rápida y alternada hasta la altura de la cadera.",
            "En posicion de flexión, llevar las rodillas hacia el pecho de manera rápida y alternada.",
            "Estando sentado/a en el suelo con los pies elevados y el torso algo reclinado, recoger y estirar las piernas. (Puedes usar las manos para apoyarte).",
            "Tumbado/a con las piernas subidas con un ángulo de 90º en las rodillas, levanta el torso como si quisieras tocar el cielo.",
            "Tumbado/a con las piernas estiradas, de manera alternada lleva una rodilla hacia el pecho a la vez que intentas tocarla con el codo del lado opuesto.",
            "En posición de plancha (plank), de manera alternada, lleva la rodilla hacia el codo del mismo lado.",
            "Túmbate en el suelo y apóyate en tus antebrazos, solo deben tocar el suelo tus antebrazos y tus pies. Mantén una línea recta, no subas mucho la cadera ni la dejes caer.",
            "En posición de flexión, apóyate con un antebrazo en el suelo, luego con el otro y vuelve a la posición inicial levantandote primero con un brazo y luego con el otro.",
            "Tumbado/a con las piernas estiradas, levantalas sin doblar las rodillas hasta que tus pies apunten al cielo. Bájalas de manera controlada.",
            "Tumbado/a con las piernas estiradas, levanta una pierna sin doblar la rodilla, sube la otra pierna a la vez que bajas la primera.",
            "Tumbado/a con las piernas estiradas, levanta ligeramente los pies y el torso. Aguanta en esta posición.",
            "Tumbado/a con las piernas estiradas, levanta ligeramente los pies. Aguanta en esta posición.",
            "Túmbate en el suelo de lado, apoyándote con el antebrazo y manteniendo una línea recta de tus pies a tus hombros. Baja y sube la cadera.",
            "Túmbate en el suelo de lado, apoyándote con el antebrazo y manteniendo una línea recta de tus pies a tus hombros.",
            "Siéntate en el suelo, reclina ligeramente el torso y toca con las manos el suelo de los lados de la cadera rotando el torso. Puedes levantar los pies para más dificultad.",
            "Túmbate en el suelo con las piernas dobladas y levanta ligeramente el torso, tócate los tobillos con la mano del mismo lado, de manera alternada."
    };

    private String[][] ejercicios = {
        {listaEjercicios[1], listaEjercicios[7], listaEjercicios[10], listaEjercicios[12], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[5], listaEjercicios[7], listaEjercicios[10], listaEjercicios[15]},
        {listaEjercicios[1], listaEjercicios[9], listaEjercicios[11], listaEjercicios[14], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[2], listaEjercicios[4], listaEjercicios[8], listaEjercicios[12]},
        {listaEjercicios[1], listaEjercicios[2], listaEjercicios[12], listaEjercicios[13], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[5], listaEjercicios[10], listaEjercicios[12], listaEjercicios[15]},
        {listaEjercicios[1], listaEjercicios[7], listaEjercicios[9], listaEjercicios[11], listaEjercicios[14]},
        {listaEjercicios[0], listaEjercicios[3], listaEjercicios[5], listaEjercicios[14], listaEjercicios[15]},
        {listaEjercicios[1], listaEjercicios[4], listaEjercicios[8], listaEjercicios[11], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[7], listaEjercicios[10], listaEjercicios[12], listaEjercicios[13]},
        {listaEjercicios[1], listaEjercicios[4], listaEjercicios[6], listaEjercicios[9], listaEjercicios[15]},
        {listaEjercicios[0], listaEjercicios[3], listaEjercicios[8], listaEjercicios[12], listaEjercicios[14]},
        {listaEjercicios[1], listaEjercicios[2], listaEjercicios[4], listaEjercicios[15], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[7], listaEjercicios[8], listaEjercicios[11], listaEjercicios[13]},
        {listaEjercicios[1], listaEjercicios[6], listaEjercicios[10], listaEjercicios[12], listaEjercicios[15]},
        {listaEjercicios[0], listaEjercicios[4], listaEjercicios[7], listaEjercicios[1], listaEjercicios[16]},
        {listaEjercicios[1], listaEjercicios[3], listaEjercicios[5], listaEjercicios[9], listaEjercicios[14]},
        {listaEjercicios[0], listaEjercicios[6], listaEjercicios[8], listaEjercicios[10], listaEjercicios[15]},
        {listaEjercicios[1], listaEjercicios[7], listaEjercicios[11], listaEjercicios[12], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[2], listaEjercicios[4], listaEjercicios[9], listaEjercicios[13]},
        {listaEjercicios[1], listaEjercicios[5], listaEjercicios[8], listaEjercicios[10], listaEjercicios[15]},
        {listaEjercicios[0], listaEjercicios[6], listaEjercicios[12], listaEjercicios[14], listaEjercicios[16]},
        {listaEjercicios[1], listaEjercicios[2], listaEjercicios[3], listaEjercicios[5], listaEjercicios[13]},
        {listaEjercicios[0], listaEjercicios[4], listaEjercicios[8], listaEjercicios[9], listaEjercicios[15]},
        {listaEjercicios[1], listaEjercicios[6], listaEjercicios[11], listaEjercicios[12], listaEjercicios[14]},
        {listaEjercicios[0], listaEjercicios[2], listaEjercicios[6], listaEjercicios[8], listaEjercicios[13]},
        {listaEjercicios[1], listaEjercicios[3], listaEjercicios[5], listaEjercicios[10], listaEjercicios[15]},
        {listaEjercicios[0], listaEjercicios[9], listaEjercicios[11], listaEjercicios[14], listaEjercicios[16]},
        {listaEjercicios[1], listaEjercicios[7], listaEjercicios[10], listaEjercicios[12], listaEjercicios[16]},
        {listaEjercicios[0], listaEjercicios[2], listaEjercicios[3], listaEjercicios[9], listaEjercicios[13]}
    };
    private String[][] descripciones = {
            {descEjercicios[1], descEjercicios[7], descEjercicios[10], descEjercicios[12], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[5], descEjercicios[7], descEjercicios[10], descEjercicios[15]},
            {descEjercicios[1], descEjercicios[9], descEjercicios[11], descEjercicios[14], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[2], descEjercicios[4], descEjercicios[8], descEjercicios[12]},
            {descEjercicios[1], descEjercicios[2], descEjercicios[12], descEjercicios[13], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[5], descEjercicios[10], descEjercicios[12], descEjercicios[15]},
            {descEjercicios[1], descEjercicios[7], descEjercicios[9], descEjercicios[11], descEjercicios[14]},
            {descEjercicios[0], descEjercicios[3], descEjercicios[5], descEjercicios[14], descEjercicios[15]},
            {descEjercicios[1], descEjercicios[4], descEjercicios[8], descEjercicios[11], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[7], descEjercicios[10], descEjercicios[12], descEjercicios[13]},
            {descEjercicios[1], descEjercicios[4], descEjercicios[6], descEjercicios[9], descEjercicios[15]},
            {descEjercicios[0], descEjercicios[3], descEjercicios[8], descEjercicios[12], descEjercicios[14]},
            {descEjercicios[1], descEjercicios[2], descEjercicios[4], descEjercicios[15], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[7], descEjercicios[8], descEjercicios[11], descEjercicios[13]},
            {descEjercicios[1], descEjercicios[6], descEjercicios[10], descEjercicios[12], descEjercicios[15]},
            {descEjercicios[0], descEjercicios[4], descEjercicios[7], descEjercicios[1], descEjercicios[16]},
            {descEjercicios[1], descEjercicios[3], descEjercicios[5], descEjercicios[9], descEjercicios[14]},
            {descEjercicios[0], descEjercicios[6], descEjercicios[8], descEjercicios[10], descEjercicios[15]},
            {descEjercicios[1], descEjercicios[7], descEjercicios[11], descEjercicios[12], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[2], descEjercicios[4], descEjercicios[9], descEjercicios[13]},
            {descEjercicios[1], descEjercicios[5], descEjercicios[8], descEjercicios[10], descEjercicios[15]},
            {descEjercicios[0], descEjercicios[6], descEjercicios[12], descEjercicios[14], descEjercicios[16]},
            {descEjercicios[1], descEjercicios[2], descEjercicios[3], descEjercicios[5], descEjercicios[13]},
            {descEjercicios[0], descEjercicios[4], descEjercicios[8], descEjercicios[9], descEjercicios[15]},
            {descEjercicios[1], descEjercicios[6], descEjercicios[11], descEjercicios[12], descEjercicios[14]},
            {descEjercicios[0], descEjercicios[2], descEjercicios[6], descEjercicios[8], descEjercicios[13]},
            {descEjercicios[1], descEjercicios[3], descEjercicios[5], descEjercicios[10], descEjercicios[15]},
            {descEjercicios[0], descEjercicios[9], descEjercicios[11], descEjercicios[14], descEjercicios[16]},
            {descEjercicios[1], descEjercicios[7], descEjercicios[10], descEjercicios[12], descEjercicios[16]},
            {descEjercicios[0], descEjercicios[2], descEjercicios[3], descEjercicios[9], descEjercicios[13]}
    };

    //Tiempo de ejercicio de cada día, en millis (tiempo descanso = 60000 - tiempo de ejercicio)
    private int[] tiempos = {
            6000,
            5000,
            8000

    };

    // G E T T E R S    Y   S E T T E R S
    public GridLayout getMainGrid() {return mainGrid;}

    public void setMainGrid(GridLayout mainGrid) {this.mainGrid = mainGrid;}

    public int getIndice() {return indice;}

    public void setIndice(int indice) {
        this.indice = indice;
        Log.i("VALOR INDICE", ""+indice);
    }

    public String[] getEjercicios(int i) {return ejercicios[i];}

    public String[] getDescripciones(int i) {return descripciones[i];}

    public int[] getTiempos() {return tiempos;}
}
