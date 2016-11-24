package isetsl.sem31.tp3.exemplebasedonneessqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterActivity extends AppCompatActivity {

    EditText editIsbn, editTitre;
    Button btnAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        editIsbn  = (EditText) findViewById(R.id.editISBN);
        editTitre = (EditText) findViewById(R.id.editTitre);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);


    }

    public void AjouterClick(View view){
        //on vérifie si les deux zones de texte sont vides ou nn
        if(editTitre.getText().toString().equals("") || (editIsbn.getText().toString().equals(""))){
            //On affiche un toast
            Toast.makeText(this, "Veuillez verifier que vous avez saisi un titre et un ISBN.", Toast.LENGTH_LONG).show();
        }
        else {
            //récuperer isbn et titre depuis les zones de texte
            String isbn = editIsbn.getText().toString();
            String titre = editTitre.getText().toString();

            //Création d'une instance de ma classe LivresBDD
            LivresBDD livreBdd = new LivresBDD(this);

            //Création d'un livre
            Livre livre  = new Livre(isbn, titre);

            //On ouvre la base de données pour écrire dedans
            livreBdd.open();

            //On insère le livre que l'on vient de créer
            long res  = livreBdd.insertLivre(livre);

            if (res!=0){//s'il ya un livre inséré
                String Message;
                Message="Le livre < " + titre + " , " + isbn + " > est inséré avec succés.";
                //Afficher toast
                Toast.makeText(this, Message.toString(), Toast.LENGTH_LONG).show();
            }
            else{//si aucun livre inséré
                String Message;
                Message="ERREUR. Le livre < " + titre + " , " + isbn + " > n'est pas inséré.";
                //Afficher toast
                Toast.makeText(this, Message.toString(), Toast.LENGTH_LONG).show();

            }
            //fermer la base de données
            livreBdd.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajouter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
