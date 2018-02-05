package cat.rupasgon.reten;

/**
 * Created by uidq2310 on 24/11/2017.
 */

public class Configuracion {



    private Integer Id;
    private String uid;
    private String servidor;
    private String baseDatos;
    private String usuarioSQL;
    private String passwordSQL;


    public Configuracion(){

        this.Id = 1;
        this.uid = "ERROR";
        this.servidor = "ERROR";
        this.baseDatos = "ERROR";
        this.usuarioSQL = "ERROR";
        this.passwordSQL = "ERROR";


    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getUsuarioSQL() {
        return usuarioSQL;
    }

    public void setUsuarioSQL(String usuarioSQL) {
        this.usuarioSQL = usuarioSQL;
    }

    public String getPasswordSQL() {
        return passwordSQL;
    }

    public void setPasswordSQL(String passwordSQL) {
        this.passwordSQL = passwordSQL;
    }
}
