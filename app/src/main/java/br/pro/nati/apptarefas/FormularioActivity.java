package br.pro.nati.apptarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spHorario;
    private EditText etData;
    private Button btnSalvar;
    private String acao;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        spHorario = findViewById(R.id.spHorarios);
        etData = findViewById(R.id.etData);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { salvar(); }
        });

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("alterar")){
            carregarFormulario();
        }
    }

    private void carregarFormulario(){
        int idTarefa = getIntent().getIntExtra("idTarefa", 0);
        tarefa = TarefasD.getTarefasById(this, idTarefa);
        etNome.setText( tarefa.getNome() );
        etData.setText( tarefa.getData() );

        String[] horario = getResources().getStringArray(R.array.horarios);

        for( int i = 0; i < horario.length ; i++){
            if( tarefa.getHorario().equals( horario[i] )){
                spHorario.setSelection( i );
                break;
            }
        }
    }


    private  void salvar(){
        String nome = etNome.getText().toString();
        String data = etData.getText().toString();

        if( nome.isEmpty() || data.isEmpty() || spHorario.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        }else{

            if( acao.equals("adicionar") ){
                tarefa = new Tarefa();
            }
            tarefa.setNome( nome );
            tarefa.setHorario( spHorario.getSelectedItem().toString() );
            tarefa.setData( data );

            if( acao.equals("adicionar") ){
                TarefasD.adicionar(this, tarefa);
                etNome.setText("");
                spHorario.setSelection(0, true);
                etData.setText("");
            }else{
                TarefasD.alterar(this, tarefa);
                finish();
            }
        }
    }
}