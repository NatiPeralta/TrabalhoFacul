package br.pro.nati.apptarefas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TarefasD {

    public static void adicionar(Context context, Tarefa tarefa){
        ContentValues valores = new ContentValues();
        valores.put("nome", tarefa.getNome());
        valores.put("horario", tarefa.getHorario());
        valores.put("data", tarefa.getData());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("tarefa", null, valores);
    }

    public static void alterar(Context context, Tarefa tarefa){
        ContentValues valores = new ContentValues();
        valores.put("nome", tarefa.getNome());
        valores.put("horario", tarefa.getHorario());
        valores.put("data", tarefa.getData());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("tarefa", valores, " id = " + tarefa.getId(), null);
    }

    public static void deletar(Context context, int idTarefa) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete( "tarefa", " id = " + idTarefa, null);
    }

    public static List<Tarefa> getTarefa(Context context) {
        List<Tarefa> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tarefa ORDER BY nome", null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Tarefa taf = new Tarefa();
                taf.setId(cursor.getInt(0));
                taf.setNome(cursor.getString(1));
                taf.setHorario(cursor.getString(2));
                taf.setData(cursor.getString(3));
                lista.add(taf);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static Tarefa getTarefasById(Context context, int idTarefa){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tarefa WHERE id = " + idTarefa, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Tarefa taf = new Tarefa();
            taf.setId(cursor.getInt(0));
            taf.setNome(cursor.getString(1));
            taf.setHorario(cursor.getString(2));
            taf.setData(cursor.getString(3));

            return taf;
        } else {
            return null;
        }
    }
}
