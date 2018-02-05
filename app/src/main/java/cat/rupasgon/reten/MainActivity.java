package cat.rupasgon.reten;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private final String NL = System.getProperty("line.separator");


    private EditText textDescription;
    private EditText textSolution;
    private EditText textIH;
    private EditText textFH;

    private CheckBox checkCHUD;
    private CheckBox checkFA;
    private CheckBox checkEM;
    private CheckBox checkRUBI2;
    private CheckBox checkPresencial;

    private Spinner textLineProduction;
    private Spinner responsable;

    private Incidence auxIncidence;

    private Date dateNow;
    private String fileSearch;


    public MainActivity(){

        auxIncidence = null;

        //incidence = null;
        //dateNow = new Date();
        //SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        //SimpleDateFormat hf = new SimpleDateFormat("hhmmss");
        //fileSearch = df.format(dateNow) + "_" + hf.format(dateNow) + ".txt";



    }


    /*
        Función que asocia los componentes del activity_main.xml
        asocia los botones y textview de la interface gráfica
        con los atributos creados en esta clase. Se encarga de iniciarlos.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDescription = (EditText) findViewById(R.id.textDescription);
        textSolution = (EditText) findViewById(R.id.textSolution);
        textIH = (EditText) findViewById(R.id.textIH);
        textFH = (EditText) findViewById(R.id.textFH);

        checkCHUD = (CheckBox) findViewById(R.id.checkCHUD);
        checkEM = (CheckBox) findViewById(R.id.checkEM);
        checkFA = (CheckBox) findViewById(R.id.checkFA);
        checkRUBI2 = (CheckBox) findViewById(R.id.checkRUBI2);
        checkPresencial = (CheckBox) findViewById(R.id.checPresencial);

        textLineProduction = (Spinner) findViewById(R.id.textLineProduction);
        String[] liniaP = {"Selecciona una línia","SMD1","SMD2","SMD3","SMD4","SMD5","SMD6","SMD7","SMD8","SMD9","ROBOT1","ROBOT3","ROBOT4","ROBOT5","ROBOT6","ROBOT7","ROBOT8","ROBOT9","ROBOT10","ROBOT11","ROBOT12",
        "CHUD FA","CHUD H1","CHUD H2","CHUD l2","FORD L1","FORD L2","DGT L1","DGT L2","PQ26 L1","PQ26 L2","PQ26 L3","UKL","LK L1","LK L2","LK L3","LK L4","MOTORES","ALFA 952","ALFA 940","MQB A0 L1","MQB A0 L2",
        "GEN4 L1","GEN4 L2","GEN3.1 L1","GEN3.1 L2","B7","A51","FIREWALL","ALMACEN","FLEJADORA","RUBI2","SERVIDORES","OTROS"};

        textLineProduction.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liniaP));

        responsable = (Spinner) findViewById(R.id.responsable);
        String[] usuarioR = {"Selecciona un responsable","Alex Rovira","David Regidor","Goyo Rodriguez","Adrián Cabello","Ruben Pascual","David Fierro"};
        responsable.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usuarioR));



    }




    //Llama al menú creado res/menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menuConfig:

                Intent j = new Intent(this,ConfiguracionContiReten.class);
                startActivity(j);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*
        Función que se activa con el boton "Enviar incidencia" de la
        interface gráfica.

     */

    public void addIncidence(View view){


        //Algoritmo encargado de dar el formato del .TXT fecha_hora.txt y se encarga
        //de que todos los campos de la interface gráfica estén debidamente completados.

        dateNow = new Date();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat hf = new SimpleDateFormat("hhmmss");
        fileSearch = df.format(dateNow) + "_" + hf.format(dateNow) + ".txt";

        if(textDescription.getText().toString().compareTo("")==0 ){

            Toast.makeText(getBaseContext(), "Rellenar el campo Descripción incidencia", Toast.LENGTH_SHORT).show();

        }
        else if (textSolution.getText().toString().compareTo("")==0){

            Toast.makeText(getBaseContext(), "Rellenar el campo Solución incidencia", Toast.LENGTH_SHORT).show();

        }
        else if(textLineProduction.toString().compareTo("")==0){

            Toast.makeText(getBaseContext(), "Rellenar el campo Línea de producción", Toast.LENGTH_SHORT).show();

        }
        else if(textIH.getText().toString().compareTo("")==0){

            Toast.makeText(getBaseContext(), "Rellenar el campo H. Inicio", Toast.LENGTH_SHORT).show();

        }
        else if(textFH.getText().toString().compareTo("")==0){

            Toast.makeText(getBaseContext(), "Rellenar el campo H. Final", Toast.LENGTH_SHORT).show();

        }

        //COMPROBACION DEL MARCADO UNICO DE UN TIPO DE PRODUCCION

        else if(checkEM.isChecked() && checkFA.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();


        }
        else if(checkEM.isChecked() && checkCHUD.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();

        }
        else if(checkEM.isChecked() && checkRUBI2.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();

        }
        else if(checkFA.isChecked() && checkCHUD.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();

        }

        else if(checkRUBI2.isChecked() && checkCHUD.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();

        }
        else if(checkRUBI2.isChecked() && checkFA.isChecked()){

            Toast.makeText(getBaseContext(), "No se puede marcar mas de una línea", Toast.LENGTH_SHORT).show();

        }
        else if(checkCHUD.isChecked()==false && checkFA.isChecked()==false && checkEM.isChecked()==false && checkRUBI2.isChecked()==false){

            Toast.makeText(getBaseContext(), "Debe marcar una línea de producción", Toast.LENGTH_SHORT).show();
        }
        else if(checkCHUD.isChecked()==false && checkFA.isChecked()==false && checkEM.isChecked()==false && checkRUBI2.isChecked()==false){

            Toast.makeText(getBaseContext(), "Debe marcar una línea de producción", Toast.LENGTH_SHORT).show();
        }

        /*
            Si todos los campos han sido completados y cumple
            con las reglas de marcado de lineas de producción
            se genera una instancia de la clase Incidence con
            los datos obtenidos en los campos, esta instancia
            unicamente se utiliza para los datos de envío del
            mail.
         */

        else {


            auxIncidence = new Incidence(textDescription.getText().toString(), textSolution.getText().toString(),
                    textLineProduction.getSelectedItem().toString(), textIH.getText().toString(), textFH.getText().toString(),
                    responsable.getSelectedItem().toString());

            if(checkEM.isChecked()){

                auxIncidence.settProduction(TypeProduction.EM);

            }
            if(checkCHUD.isChecked()){

                auxIncidence.settProduction(TypeProduction.CHUD);

            }
            if(checkFA.isChecked()){

                auxIncidence.settProduction(TypeProduction.FA);

            }
            if(checkRUBI2.isChecked()){

                auxIncidence.settProduction(TypeProduction.RUBI2);

            }
            if (checkPresencial.isChecked()){

                auxIncidence.setPresencial(true);

            }


            //Insercción en la base de datos SQLite
            addToSQLite();

            try {

                getDataToSQLite();

            }catch (Exception ex){

                Toast.makeText(getBaseContext(), "ERROR"+ ex.getMessage(), Toast.LENGTH_SHORT).show();

            }


            //Se genera el archivo en la memoria del dispositivo
            //con el formato obtenido de la clase Incidence.
            //Resetea todos los campos para preparar
            //la siguiente incidencia.

            //writeInFile(auxIncidence);   PENDIENTE DE BORRAR

            //textListIncidences.setText(auxIncidence.toStringByView());

            textDescription.setText(null);
            textSolution.setText(null);
            textIH.setText(null);
            textFH.setText(null);

            checkEM.setChecked(false);
            checkFA.setChecked(false);
            checkCHUD.setChecked(false);
            checkRUBI2.setChecked(false);
            checkPresencial.setChecked(false);

            sendEmail();

            Toast.makeText(getBaseContext(), "Archivo de incidencia creado y listo para enviar", Toast.LENGTH_SHORT).show();

        }


    }

    /*  Función que chequea el si la avería es de
    *   FA,SMT,CHUD,RUBI2 y dependiendo envía a un remitente o a otro
    */

    public void sendEmail() {

        //ENVIO DEL CORREO A FA

        if(checkFA.isChecked()){

            //File path_file = getExternalFilesDir(null);
            //File archivo = new File(path_file.getAbsolutePath(), fileSearch);
            //Uri uri = Uri.fromFile(archivo);

            Date date = new Date();
            String[] TO = {"rupasgon@gmail.com;FA@continental-corporation.com"}; //Correo a quines quieres enviar
            String[] CC = {"goyo.rodriguez@continental-corporation.com"};   //Con copia a...
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Incidencias reten: " + date.toString() );
            emailIntent.putExtra(Intent.EXTRA_TEXT, auxIncidence.toStringByView() ); //Cuerpo del mensaje el cual le paso un string con formato StringBuilder
            //emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            //Una vez creado el mensaje con toda su configuración se envias mediante un try and cast
            //heredado de la clase exception.

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }


        }

        //ENVIO DEL CORREO A RUBI2

        else if(checkRUBI2.isChecked()){

            //File path_file = getExternalFilesDir(null);
            //File archivo = new File(path_file.getAbsolutePath(), fileSearch);
            //Uri uri = Uri.fromFile(archivo);

            Date date = new Date();
            String[] TO = {"rupasgon@gmail.com;RUBI2@continental-corporation.com"}; //Correo a quines quieres enviar
            String[] CC = {"goyo.rodriguez@continental-corporation.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Incidencias reten: " + date.toString() );
            emailIntent.putExtra(Intent.EXTRA_TEXT, auxIncidence.toStringByView() );
            //emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }


        }

        //ENVIO DEL CORREO A CHUD

        else if(checkCHUD.isChecked()){

            //File path_file = getExternalFilesDir(null);
            //File archivo = new File(path_file.getAbsolutePath(), fileSearch);
            //Uri uri = Uri.fromFile(archivo);

            Date date = new Date();
            String[] TO = {"rupasgon@gmail.com;CHUD@continental-corporation.com"}; //Correo a quines quieres enviar
            String[] CC = {"goyo.rodriguez@continental-corporation.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Incidencias reten: " + date.toString() );
            emailIntent.putExtra(Intent.EXTRA_TEXT, auxIncidence.toStringByView() );
            //emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }


        }

        //ENVIO DEL CORREO A EM

        else {

            //File path_file = getExternalFilesDir(null);
            //File archivo = new File(path_file.getAbsolutePath(), fileSearch);
            //Uri uri = Uri.fromFile(archivo);

            Date date = new Date();
            String[] TO = {"rupasgon@gmail.com;EM@continental-corporation.com"}; //Correo a quines quieres enviar
            String[] CC = {"goyo.rodriguez@continental-corporation.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Incidencias reten: " + date.toString() );
            emailIntent.putExtra(Intent.EXTRA_TEXT, auxIncidence.toStringByView() );
            //emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }


        }

    }


    //Metodo para insertar los datos recogidos desde el metodo toContentValues
    //para insertarlo en la base de datos SQLite

    public void addToSQLite(){

        try {

            //Recoge datos de la interface MainActivity y crear un contenedor, posteriormente
            //inserta los datos del contenedor en la base de datos SQLite.

            ContentValues values = new ContentValues();
            values.put("Responsable",responsable.getSelectedItem().toString());
            values.put("Descripcion",textDescription.getText().toString());
            values.put("Solucion", textSolution.getText().toString());
            values.put("H_Inicio", textIH.getText().toString());
            values.put("H_Final", textFH.getText().toString());
            values.put("Linea", textLineProduction.getSelectedItem().toString());

            if(checkPresencial.isChecked()){

                values.put("Is_Presencial",1);

            }else {

                values.put("Is_Presencial",0);

            }

            //Coge la fecha actual del sistema y la formatea en un String
            //para luego insertarla en SQLite.

            StringBuilder sb = new StringBuilder();
            Date thisDate = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            sb.append(df.format(thisDate));

            values.put("FechaInicio", sb.toString());

            //Inserta el valor de TotalMinutos calculados en la funcion calculateHoursToMinutos en base a H_Final - H_Inicio
            values.put("TotalMinutos",calculateHoursToMinutos(textIH.getText().toString(),textFH.getText().toString()));


            //Inserccion de datos en la base de datos SQLite.

            adminSQLiteHelper admin = new adminSQLiteHelper(this);
            SQLiteDatabase db = admin.getWritableDatabase();
            db.insert("Incidencias",null,values);
            db.close();
            Toast.makeText(MainActivity.this,"Se insertaron los datos correctamente en SQLite",Toast.LENGTH_SHORT).show();

        }catch (SQLiteFullException ex){

            Toast.makeText(MainActivity.this, "ERROR: No se insertaron los datos en SQLite", Toast.LENGTH_SHORT).show();

        }




    }

    //Funcion que recupera los datos de la base de datos SQLite y retorna un ArrayList.

    public ArrayList<IncidenceToSQL> getDataToSQLite(){

        adminSQLiteHelper admin = new adminSQLiteHelper(this);
        SQLiteDatabase db = admin.getReadableDatabase();
        ArrayList<IncidenceToSQL> incidences = new ArrayList<IncidenceToSQL>();
        String selectQuery = "SELECT * FROM Incidencias";

        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){

            do {

                IncidenceToSQL incidence = new IncidenceToSQL();

                incidence.setResponsable(cursor.getString(1));
                incidence.setDescripcion(cursor.getString(2));
                incidence.setSolucion(cursor.getString(3));
                incidence.setH_Inicio(cursor.getString(4));
                incidence.setH_Final(cursor.getString(5));
                incidence.setLinea(cursor.getString(6));
                incidence.setIs_Presencial(Integer.parseInt(cursor.getString(7)));
                incidence.setThisDate(cursor.getString(8));
                incidence.setTotalMinutos(cursor.getString(9));

                incidences.add(incidence);

                //Toast.makeText(getBaseContext(),incidence.getDescripcion(), Toast.LENGTH_SHORT).show();

            }while (cursor.moveToNext());


        }
        admin.close();


        return incidences;


    }

    //Inserta los datos con la base de datos externa.
    public void syncToMSQL(View view){

                ArrayList<IncidenceToSQL> arrayAux;
                arrayAux = getDataToSQLite(); //recupera los datos de la Base de Datos SQLite mediante la funcion getDataToSQLite() que retorna un arraylist.
                IncidenceToSQL auxIncidence;

                Configuracion config;
                config = getConfiguration();

                Iterator<IncidenceToSQL> itAux = arrayAux.iterator();

                while (itAux.hasNext()){

                    auxIncidence = itAux.next();

                    try {

                        Connection cn = ConexionSQL.ConnectionHelper(config);

                        if (cn == null){

                            Toast.makeText(getApplicationContext(), "Parece que no se ha podido realizar la conexión con la base de datos. Comprueba que estés conectado a la red corporativa.", Toast.LENGTH_LONG).show();
                            cn.close();

                        }
                        else {

                            Toast t1 =
                                    Toast.makeText(getApplicationContext(), "ESTABLECIENDO CONEXION CON LA BASE DE DATOS", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
                            t1.show();

                            Toast t2 =
                                    Toast.makeText(getApplicationContext(), "TRANSFIRIENDO DATOS", Toast.LENGTH_SHORT);
                            t2.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
                            t2.show();

                            Statement st = cn.createStatement();
                            st.execute("INSERT INTO dbo.Incidencias(ID,Linea,Puesto,Incidencia,AccionCP,FechaInicio,Hora,Responsable,Estado,Tiempo,Modificador,Presencial,Tipo) VALUES (9999,'" + auxIncidence.getLinea() + "','PC linea','" + auxIncidence.getDescripcion() + "','" + auxIncidence.getSolucion() + "','" + auxIncidence.getThisDate() + "','" + auxIncidence.getH_Inicio() + "','" + auxIncidence.getResponsable() + "','Finalizada','" + auxIncidence.getTotalMinutos() + "','" + config.getUid() + "','" + auxIncidence.getIs_Presencial() + "','Reten');");

                            Toast t3 =
                                    Toast.makeText(getApplicationContext(), "Se ha insertado: " + auxIncidence.getDescripcion(), Toast.LENGTH_SHORT);
                            t3.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
                            t3.show();

                            adminSQLiteHelper admin = new adminSQLiteHelper(this);
                            SQLiteDatabase db = admin.getWritableDatabase();
                            db.execSQL("DELETE FROM Incidencias WHERE Descripcion LIKE '" + auxIncidence.getDescripcion() + "';");




                            cn.close();



                        }

                    }catch (Exception ex){



                    }


                }

    }

    //Calculo de timeFinish - timeStart para calcular el total de minutos de la incidencia.
    public String calculateHoursToMinutos(String timeStart, String timeFinish){

        int totalToInt = 0;
        String totalToString = null;

        String[] start;
        String hhSplitTimeStart = null;
        String mmSplitTimeStart = null;
        String[] finish;
        String hhSplitTimeFinish = null;
        String mmSplitTimeFinish = null;

        start = timeStart.split(":");
        hhSplitTimeStart = start[0];
        mmSplitTimeStart = start[1];

        finish = timeFinish.split(":");
        hhSplitTimeFinish = finish[0];
        mmSplitTimeFinish = finish[1];


        totalToInt = (Integer.parseInt(hhSplitTimeFinish)*60 + Integer.parseInt(mmSplitTimeFinish)) - (Integer.parseInt(hhSplitTimeStart)*60 + Integer.parseInt(mmSplitTimeStart));
        totalToString = Integer.toString(totalToInt);



        return totalToString;

    }


    public Configuracion getConfiguration(){

        Configuracion config = new Configuracion();

        try {



            adminSQLiteHelper admin = new adminSQLiteHelper(this);
            SQLiteDatabase db = admin.getReadableDatabase();


            if(db==null){

                Toast.makeText(this, "No se ha podido leer la BD SQLite Configuracion", Toast.LENGTH_SHORT).show();

            }else {


                String selectQuery = "SELECT * FROM Configuracion";

                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {

                    do {

                        config.setUid(cursor.getString(1));
                        config.setServidor(cursor.getString(2));
                        config.setBaseDatos(cursor.getString(3));
                        config.setUsuarioSQL(cursor.getString(4));
                        config.setPasswordSQL(cursor.getString(5));

                    } while (cursor.moveToNext());

                }
                db.close();
            }

        }catch (SQLiteFullException ex){

            Toast.makeText(MainActivity.this, "ERROR: No se ha podido recuperar los datos de la configuración SQLite", Toast.LENGTH_SHORT).show();

        }


        return config;

    }



}
