package cat.rupasgon.reten;

/**
 * Created by uidq2310 on 24/10/2017.
 */

public class IncidenceToSQL {

    private String Responsable;
    private String Descripcion;
    private String Solucion;
    private String H_Inicio;
    private String H_Final;
    private String Linea;
    private int Is_Presencial;
    private String thisDate;
    private String totalMinutos;


    public IncidenceToSQL(){
        this.Descripcion=null;
        this.Solucion=null;
        this.H_Inicio=null;
        this.H_Final=null;
        this.Linea=null;
        this.Is_Presencial=0;
        this.totalMinutos=null;
    }
    public IncidenceToSQL(String responsable,String descripcion, String solucion, String h_Inicio, String h_Final, String linea, int is_Presencial) {
        this.Responsable = responsable;
        this.Descripcion = descripcion;
        this.Solucion = solucion;
        this.H_Inicio = h_Inicio;
        this.H_Final = h_Final;
        this.Linea = linea;
        this.Is_Presencial = is_Presencial;
    }


    public String toStringByView(){

        StringBuilder sb = new StringBuilder();

        sb.append(this.Linea);

        return sb.toString();

    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String responsable) {
        Responsable = responsable;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getSolucion() {
        return Solucion;
    }

    public void setSolucion(String solucion) {
        Solucion = solucion;
    }

    public String getH_Inicio() {
        return H_Inicio;
    }

    public void setH_Inicio(String h_Inicio) {
        H_Inicio = h_Inicio;
    }

    public String getH_Final() {
        return H_Final;
    }

    public void setH_Final(String h_Final) {
        H_Final = h_Final;
    }

    public String getLinea() {
        return Linea;
    }

    public void setLinea(String linea) {
        Linea = linea;
    }

    public int getIs_Presencial() {
        return Is_Presencial;
    }

    public void setIs_Presencial(int is_Presencial) {
        Is_Presencial = is_Presencial;
    }

    public String getTotalMinutos(){
        return this.totalMinutos;
    }
    public void setTotalMinutos(String totalMinutos){

        this.totalMinutos=totalMinutos;

    }

    public String getThisDate() {
        return thisDate;
    }

    public void setThisDate(String thisDate) {
        this.thisDate = thisDate;
    }
}
