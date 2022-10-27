package com.lorenzo.mytabapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ListaProdotti.db";
    private static final int DATABASE_VERSION = 1;
    private String nomeListaF;
    public String PrimeName;

    private static final String TABLE_NAME = "my_list";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODOTTO = "prodotto";
    private static final String COLUMN_QUANTITA = "quantita";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nomeLista;

        /*String query_2 = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODOTTO + " TEXT, " +
                COLUMN_QUANTITA + " TEXT);";

        db.execSQL(query_2);*/

    }

    public void creaNuovaTabella(String nome) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME, nome);

        String query_2 = "CREATE TABLE " + nome +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODOTTO + " TEXT, " +
                COLUMN_QUANTITA + " TEXT);";

        sqLiteDatabase.execSQL(query_2);

        nomeListaF = nome;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addProdotto(String prodotto, String quantita) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODOTTO, prodotto);
        contentValues.put(COLUMN_QUANTITA, quantita);

        long result = sqLiteDatabase.insert(nomeListaF, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Errore", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Aggiunto con successo!", Toast.LENGTH_SHORT).show();
        }
    }

   /* public ArrayList getAllText() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
            cursor.moveToNext();
        }
        return arrayList;
    }*/


    public Cursor readAllProdotti(String tableName) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName;


        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        } else {
            Toast.makeText(context.getApplicationContext(), "La lista è vuota", Toast.LENGTH_LONG).show();
        }
        return cursor;
    }


    public String getPrimeName() {
        return PrimeName;
    }

    public void setPrimeName(String primeName) {
        PrimeName = primeName;
    }



    public static String getTableName() {
        return TABLE_NAME;
    }
}

