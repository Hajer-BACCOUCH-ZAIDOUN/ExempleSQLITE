package isetsl.sem31.tp3.exemplebasedonneessqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_Ajouter) {
            //afficher l'activité de l'ajout
            Intent i=new Intent(this,AjouterActivity.class);
            startActivity(i);

            return true;
        }

        if (id == R.id.item_Rechercher) {

            //afficher l'activité de la recherche
            Intent i=new Intent(this,RechercherActivity.class);
            startActivity(i);

            return true;
        }

        if (id == R.id.item_Liste) {

            //afficher l'activité de la listview
            Intent i=new Intent(this,AfficherListeLivresActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
