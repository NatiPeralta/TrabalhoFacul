package br.pro.nati.apptarefas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "AppTarefas";
    private static final int VERSAO = 1;

    public Banco(Context context) { super(context, NOME_BANCO, null, VERSAO); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(  "CREATE TABLE IF NOT EXISTS tarefa ( " +
                     "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                     "nome TEXT NOT NULL, " +
                     "horario TEXT, " +
                     "data TEXT); "
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
