package cat.rupasgon.reten;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by uidq2310 on 22/11/2017.
 */

public class ConfiguracionContiReten extends Activity {


    private EditText uid;
    private EditText servidor;
    private EditText baseDatos;
    private EditText usuarioSQL;
    private EditText passwordSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        uid = (EditText) findViewById(R.id.uid);
        servidor = (EditText) findViewById(R.id.servidor);
        baseDatos = (EditText) findViewById(R.id.baseDatos);
        usuarioSQL = (EditText) findViewById(R.id.usuarioSQL);
        passwordSQL = (EditText) findViewById(R.id.passwordSQL);


        getConfiguration();

    }


    public void getConfiguration(){

        try {

            adminSQLiteHelper admin = new adminSQLiteHelper(this);
            SQLiteDatabase db = admin.getReadableDatabase();
            String selectQuery = "SELECT * FROM Configuracion";

            Cursor cursor = db.rawQuery(selectQuery,null);

            if(cursor.moveToFirst()){

                do {

                        this.uid.setText(cursor.getString(1));
                        this.servidor.setText(cursor.getString(2));
                        this.baseDatos.setText(cursor.getString(3));
                        this.usuarioSQL.setText(cursor.getString(4));
                        this.passwordSQL.setText(cursor.getString(5));

                } while (cursor.moveToNext());

            }
            db.close();


        }catch (SQLiteFullException ex){

            Toast.makeText(ConfiguracionContiReten.this, "ERROR: No se ha podido recuperar los datos de la configuración SQLite", Toast.LENGTH_SHORT).show();

        }

    }

    public void setConfiguration(){

        try {

            ContentValues values = new ContentValues();
            values.put("Id",1);
            values.put("uid",this.uid.getText().toString());
            values.put("servidor",this.servidor.getText().toString());
            values.put("baseDatos",this.baseDatos.getText().toString());
            values.put("usuarioSQL",this.usuarioSQL.getText().toString());
            values.put("passwordSQL",this.passwordSQL.getText().toString());

            adminSQLiteHelper admin = new adminSQLiteHelper(this);
            SQLiteDatabase db = admin.getWritableDatabase();
            db.execSQL("DELETE FROM Configuracion WHERE Id=1");
            db.insert("Configuracion",null,values);
            db.close();

            this.uid.setText("");
            this.servidor.setText("");
            this.baseDatos.setText("");
            this.usuarioSQL.setText("");
            this.passwordSQL.setText("");

        }catch (SQLiteFullException ex){

            Toast.makeText(ConfiguracionContiReten.this, "ERROR: No se insertaron los datos en SQLite", Toast.LENGTH_SHORT).show();

        }


        Toast.makeText(this, "Configuración aplicada", Toast.LENGTH_SHORT).show();


    }


    public void insertConfig(View view){

        setConfiguration();

        getConfiguration();



    }









}
