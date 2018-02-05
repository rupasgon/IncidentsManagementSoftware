package cat.rupasgon.reten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by uidq2310 on 23/10/2017.
 */

public class adminSQLiteHelper extends SQLiteOpenHelper{

    //Crea la base de datos
    static final String DB_NAME = "Incidencias.db";

    //Crea las tablas
    static final String QUERY_CREATE_TABLE = "CREATE TABLE Incidencias (Id INTEGER PRIMARY KEY AUTOINCREMENT, Responsable TEXT, Descripcion TEXT, Solucion TEXT, H_Inicio TEXT, H_Final TEXT, Linea TEXT, Is_Presencial INTEGER, FechaInicio TEXT,TotalMinutos TEXT )";
    static final String TABLE_NAME = "Incidencia";

    static final String QUERY_CREATE_TABLE_CONFIGURACION = "CREATE TABLE Configuracion (Id INTEGER PRIMARY KEY, uid TEXT, servidor TEXT, baseDatos TEXT, usuarioSQL TEXT, passwordSQL TEXT)";
    static final String TABLE_NAME_CONFIGURACION = "Configuracion";

    public adminSQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(this.QUERY_CREATE_TABLE);
        sqLiteDatabase.execSQL(this.QUERY_CREATE_TABLE_CONFIGURACION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
