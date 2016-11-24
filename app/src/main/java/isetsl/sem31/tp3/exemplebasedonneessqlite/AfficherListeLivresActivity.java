package isetsl.sem31.tp3.exemplebasedonneessqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AfficherListeLivresActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_livres);
        listview = (ListView) findViewById(R.id.listview);


        //Création d'une instance de ma classe LivresBDD
        LivresBDD livreBdd = new LivresBDD(this);

        //ouvrir la connexion
        livreBdd.open();

        Cursor cursor = livreBdd.getAllLivres();//recuperer toutes les livres

        if (cursor.getCount()==0 ){
            Toast.makeText(this,"Aucun livre n'existe.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Affichage avec succés",Toast.LENGTH_LONG).show();

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_2,
                    cursor,
                    new String[]{LivresBDD.COL_ISBN, LivresBDD.COL_TITRE},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0);

            listview.setAdapter(adapter);
        }

        //fermer la base de données
        livreBdd.close();
    }
}
