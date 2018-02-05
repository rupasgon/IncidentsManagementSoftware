package cat.rupasgon.reten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by uidq2310 on 13/11/2017.
 */

public class GestionIncidencias extends AppCompatActivity {

    ListView getmLeadsList;
    ArrayAdapter<String> mLeadsAdapter;


    public GestionIncidencias(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);


        ListView mLeadsList = (ListView) findViewById(R.id.leads_list);



    }

    public void onClickNueva(){




        Intent j = new Intent(this,GestionIncidencias.class);
        startActivity(j);



    }




}

