package isetsl.sem31.tp3.exemplebasedonneessqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HAJER on 27/10/2015.
 */
public class LivresBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "dbBooks.db";
    private static final String TABLE_LIVRES = "BooksTable";
    private static final String COL_ID = "_id";
    private static final int NUM_COL_ID = 0;
    public static final String COL_ISBN = "ISBN";
    private static final int NUM_COL_ISBN = 1;
    public static final String COL_TITRE = "TITRE";
    private static final int NUM_COL_TITRE = 2;

    private SQLiteDatabase bdd;
    private MaBaseSQLITE maBaseSQLite;


    public LivresBDD(Context context) {
        //On créer la BDD et sa table
        maBaseSQLite = new MaBaseSQLITE(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return  bdd;
    }

    public long insertLivre(Livre livre){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom //de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ISBN, livre.getIsbn());
        values.put(COL_TITRE, livre.getTitre());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_LIVRES, null, values);
    }

    public int updateLivre(int id, Livre livre){
        //La mise à jour d'un livre dans la BDD
        //fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour
        //grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ISBN, livre.getIsbn());
        values.put(COL_TITRE, livre.getTitre());
        return bdd.update(TABLE_LIVRES, values, COL_ID + " = " + id, null);
    }

    public int removeLivreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_LIVRES, COL_ID + " = " + id, null);
    }

    public Livre getLivreWithTitre(String titre){
        //Récupère dans un Cursor les valeur correspondant à un
        //livre contenu dans la BDD (ici on sélectionne le livre
        //grâce à son titre)
        Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToLivre(c);
    }

    public Livre getLivreWithId(int id){
        //Récupère dans un Cursor les valeur correspondant à un
        //livre contenu dans la BDD (ici on sélectionne le livre
        //grâce à son id)
        Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_ID + " = " + id , null, null, null, null);
        return cursorToLivre(c);
    }

    public Cursor getAllLivres(){
        //récupérer tout select * from tablelivres sous forme d'un curseur
        Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, null, null, null, null, null);
        return c;
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Livre cursorToLivre(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on //renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        //On créé un livre
        Livre livre = new Livre();


        //on lui affecte toutes les infos grâce aux infos contenues //dans le Cursor
        livre.setId(c.getInt(NUM_COL_ID));
        livre.setIsbn(c.getString(NUM_COL_ISBN));
        livre.setTitre(c.getString(NUM_COL_TITRE));

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return livre;
    }
}
