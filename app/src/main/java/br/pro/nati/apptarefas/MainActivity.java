package br.pro.nati.apptarefas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTarefas;
    private List<Tarefa> listaDeTarefas;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra( "acao",  "adicionar" );
                startActivity( intent );
            }
        });

        lvTarefas = findViewById(R.id.lvTarefas);

        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra( "acao",  "alterar" );
                int idTarefa = listaDeTarefas.get(position).getId();
                intent.putExtra("idTarefa", idTarefa);
                startActivity( intent );
            }
        });

        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                deletar( position );

                return true;
            }
        });

        carregarLista();
    }

    private void deletar(int posicao){
        Tarefa tafSelecionado = listaDeTarefas.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Deletar");
        alerta.setIcon( android.R.drawable.ic_dialog_alert );
        alerta.setMessage("Deseja deletar a tarefa " + tafSelecionado.getNome() + "? ");

        alerta.setNeutralButton("Não", null);

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TarefasD.deletar(MainActivity.this, tafSelecionado.getId() );
                carregarLista();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLista();
    }

    private void carregarLista(){

        listaDeTarefas = TarefasD.getTarefa(this);

        if (listaDeTarefas.size() == 0){
            Tarefa fake = new Tarefa("Não há tarefas ainda!", "", "");
            listaDeTarefas.add( fake );
            lvTarefas.setEnabled( false );
        }else{
            lvTarefas.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeTarefas);
        lvTarefas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}