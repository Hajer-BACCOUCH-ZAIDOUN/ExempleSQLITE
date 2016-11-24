package isetsl.sem31.tp3.exemplebasedonneessqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HAJER on 27/10/2015.
 */
public class MaBaseSQLITE extends SQLiteOpenHelper {

    private static final String TABLE_LIVRES = "BooksTable";
    private static final String COL_ID = "_id";
    private static final String COL_ISBN = "ISBN";
    private static final String COL_TITRE = "TITRE";

    private static final String CREATE_BDD ="CREATE TABLE " + TABLE_LIVRES + " (" +
                                                 COL_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                 COL_ISBN  + " TEXT NOT NULL, " +
                                                 COL_TITRE + " TEXT NOT NULL);";

    public MaBaseSQLITE(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_LIVRES + ";");
        onCreate(db);
    }
}
