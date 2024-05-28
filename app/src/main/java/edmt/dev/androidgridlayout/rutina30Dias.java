package edmt.dev.androidgridlayout;

//SE USA ESTA VAINA?

public class rutina30Dias {
    public String[] ejercicios;
    public int millisEjercicio;

    public rutina30Dias(){

    }

    public rutina30Dias(String[] ejercicios, int millisEjercicio){
        this.ejercicios = ejercicios;
        this.millisEjercicio = millisEjercicio;
    }

    public String[] getEjercicios() {
        return ejercicios;
    }

    public int getMillisEjercicio() {
        return millisEjercicio;
    }

    public void setEjercicios(String[] ejercicios) {
        this.ejercicios = ejercicios;
    }

    public void setMillisEjercicio(int millisEjercicio) {
        this.millisEjercicio = millisEjercicio;
    }
}
