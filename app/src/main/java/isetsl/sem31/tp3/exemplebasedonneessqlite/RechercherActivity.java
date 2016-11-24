package isetsl.sem31.tp3.exemplebasedonneessqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RechercherActivity extends AppCompatActivity {

    EditText editID, editTitre, editISBN;
    Button btnRechercher, btnModifier, btnSupprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher);

        editID = (EditText) findViewById(R.id.editID);
        editISBN = (EditText) findViewById(R.id.editIsbn);
        editTitre = (EditText) findViewById(R.id.editTitre);

        btnModifier = (Button) findViewById(R.id.btnModifier);
        btnRechercher = (Button) findViewById(R.id.btnRechercher);
        btnSupprimer = (Button) findViewById(R.id.btnSupprimer);

    }

    //click sur le bouton rechercher
    //on doit saisir l'id et on clique sur rechercher
    public void RechercherClick(View view){

            //Création d'une instance de ma classe LivresBDD
            LivresBDD livreBdd = new LivresBDD(this);

            //ouvrir la connexion
            livreBdd.open();

            //récupérer l'id du livre à chercher
            int id_livre = Integer.parseInt(editID.getText().toString()) ;

            //chercher dans la base de données le livre ayant l'id numéro id_livre
            Livre livre = livreBdd.getLivreWithId(id_livre);

            //tester si livre est null ou non
            if(livre==null){
                //On affiche un toast d'erreur
                String Message = "Le livre ayant le numéro " + String.valueOf(id_livre) +"n'existe pas.";
                Toast.makeText(this, Message , Toast.LENGTH_LONG).show();
            }
            else {
                //mettre les données du livre trouvé dans les zones de texte
                editISBN.setText(livre.getIsbn());
                editTitre.setText(livre.getTitre());
            }

            //fermer la base de données
            livreBdd.close();
    }


    //click sur le bouton modifier
    //si j'ai afficher un livre (id, isbn, titre) et je veux le modifier

    public void ModifierClick(View view) {

        //on vérifie si les trois zones de texte sont vides ou nn
        if(editTitre.getText().toString().equals("") ||
                (editISBN.getText().toString().equals(""))||
                editID.getText().toString().equals("")){
            //On affiche un toast
            Toast.makeText(this, "Veuillez verifier que vous avez saisi l'ID du livre à modifier, ainsi que le titre, et l'ISBN.", Toast.LENGTH_LONG).show();
        }
        else {
            //Création d'une instance de ma classe LivresBDD
            LivresBDD livreBdd = new LivresBDD(this);

            //ouvrir la connexion
            livreBdd.open();

            String titre = editTitre.getText().toString();
            String isbn = editISBN.getText().toString();
            int id_livre=Integer.parseInt(editID.getText().toString());

            Livre livre = new Livre(isbn,titre);


            int res = livreBdd.updateLivre(id_livre,livre );
            if (res==1){//s'il ya un livre modifié
                String Message;
                Message="Le livre < " + id_livre + " > est modifié avec succés.";
                //Afficher toast
                Toast.makeText(this, Message.toString(), Toast.LENGTH_LONG).show();

            }

            //fermer la base de données
            livreBdd.close();
        }


    }


    public void SupprimerClick(View view) {
        //on vérifie si les trois zones de texte sont vides ou nn
        if(editTitre.getText().toString().equals("") ||
                (editISBN.getText().toString().equals(""))||
                editID.getText().toString().equals("")){
                 //On affiche un toast
            Toast.makeText(this, "Veuillez verifier que vous avez saisi l'ID du livre à supprimer, ainsi que le titre, et l'ISBN.", Toast.LENGTH_LONG).show();
        }
        else {
            //Création d'une instance de ma classe LivresBDD
            LivresBDD livreBdd = new LivresBDD(this);

            //ouvrir la connexion
            livreBdd.open();

            int id_livre = Integer.parseInt(editID.getText().toString());
            int res = livreBdd.removeLivreWithID(id_livre);

            if (res==1){//s'il ya un livre supprimé
                String Message;
                Message="Le livre < " + id_livre + " > est supprimé avec succés.";
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
        getMenuInflater().inflate(R.menu.menu_rechercher, menu);
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
