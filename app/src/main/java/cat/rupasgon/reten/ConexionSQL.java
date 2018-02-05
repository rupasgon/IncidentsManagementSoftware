package cat.rupasgon.reten;

/**
 * Created by uidq2310 on 26/10/2017.
 */


import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {




    public static Connection ConnectionHelper(Configuracion config){



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;



        try {



            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String ConnectionURL = "jdbc:jtds:sqlserver://" + config.getServidor() + ";databaseName=" + config.getBaseDatos() + ";user=" + config.getUsuarioSQL() + ";password=" + config.getPasswordSQL();
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            System.out.println(Log.e("ERROR", se.getMessage()));
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return connection;
    }



}
