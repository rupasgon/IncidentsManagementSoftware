package cat.rupasgon.reten;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by uidq2310 on 27/06/2017.
 */

public class Incidence {

    private static int nextId = 1;


    private String textDescription;
    private String textSolution;
    private String textLineProduction;
    private String responsable;
    private String textIH;
    private String textFH;
    private Date createDate;
    private int id;
    private boolean presencial;

    private TypeProduction tProduction;





    public Incidence(String textDescription, String textSolution, String textLineProduction, String textIH, String textFH, String responsable) {
        this.textDescription = textDescription;
        this.textSolution = textSolution;
        this.textLineProduction = textLineProduction;
        this.responsable = responsable;
        this.textIH = textIH;
        this.textFH = textFH;
        this.tProduction=null;
        this.createDate = new Date();
        this.presencial = false;


        this.id = nextId;
        nextId++;

    }
    public Incidence(){

        this.textDescription = null;
        this.textSolution = null;
        this.textLineProduction = null;
        this.textIH = null;
        this.textFH = null;
        this.tProduction=null;
        this.createDate = new Date();
        this.presencial = false;


        this.id = nextId;
        nextId++;
    }

    //Comprueba el estadoi del atributo "presencial" para conmutar el modo de la resolución de la avería e imprime la incidencia con formato usando sb.

    public String toStringByView(){

        StringBuilder sb = new StringBuilder();

        String p = "remotamente";

        if(this.presencial==true){

            p = "fisicamente desde Continental";
        }

        sb.append("INCIDENCIAS RETEN IT" + "\n" + "-------------------------------------" + "\n" + "\n" + this.createDate.toString() + "\n" + "\n" + "LINIA: " +
                this.textLineProduction + "\n" + "TIEMPO: " + this.textIH + " - " + this.textFH + "\n" + "DESCRIPCIÓN: " + this.textDescription.toString() + "\n" + " SOLUCIÓN: "
                + this.textSolution.toString() + "\n" + "\n" + "Esta incidencia ha sido asistida " + p + " por " + this.responsable.toString() + "." + "\n");

        return sb.toString();

    }

    //Funciones Getters

    public String getTextDescription() {
        return this.textDescription;
    }

    public String getTextSolution() {
        return this.textSolution;
    }

    public String getTextLineProduction() {
        return this.textLineProduction;
    }

    public String getTextIH() {
        return this.textIH;
    }

    public String getTextFH() {
        return this.textFH;
    }

    public String getResponsable() {return this.responsable; }

    public int getId() {
        return this.id;
    }

    public TypeProduction gettProduction() {
        return this.tProduction;
    }

    //Genera en formato string la fecha de creación de la incidencia y la retorna.

    public String getCreateDate(){

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        sb.append(df.format(createDate));


        return sb.toString();
    }



    public boolean isPresencial() {
        return this.presencial;
    }

    //Según el estado boleano del atributo true/false retorna un string remota o presencial.

    public String stringIsPresencial(){

        String presencial = "Remoto";

        if(this.presencial==true){

            presencial="Presencial";
        }

        return  presencial;

    }

    //Funciones setters de la clase Incidence.

    public void setPresencial(boolean presencial) {
        this.presencial = presencial;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public void setTextSolution(String textSolution) {
        this.textSolution = textSolution;
    }

    public void setTextLineProduction(String textLineProduction) {
        this.textLineProduction = textLineProduction;
    }

    public void setTextIH(String textIH) {
        this.textIH = textIH;
    }

    public void setTextFH(String textFH) {
        this.textFH = textFH;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void settProduction(TypeProduction tProduction) {
        this.tProduction = tProduction;
    }
}
