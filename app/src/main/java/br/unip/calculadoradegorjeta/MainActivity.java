package br.unip.calculadoradegorjeta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Constantes usadas ao se salvar/restaurar estado do app
    private static final String CONTA = "CONTA";
    private static final String PERCENTUAL = "PERCENTUAL";

    //Atributos que armazenam os valores que devem ser mantidos
    //quando o aplicativo reinicia
    private double conta;
    private double percentual;

    //Armazena as referêrencias aos componentes da interface gráfica

    private EditText contaEditText_;
    private EditText gorjeta5EditText;
    private EditText gorjeta10EditText;
    private EditText gorjeta15EditText;
    private SeekBar percentualSeekBar;
    private EditText percentualEditText;
    private EditText gorjetaEditText;
    private EditText valorTotaleditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtém referência aos componentes da tela:
        contaEditText_ = (EditText) findViewById(R.id.contaEditText);
        gorjeta5EditText = (EditText) findViewById(R.id.ngorjeta5EditText);
        gorjeta10EditText = (EditText) findViewById(R.id.ngorjeta10EditText);
        gorjeta15EditText = (EditText) findViewById(R.id.ngorjeta15EditText);
        percentualSeekBar = (SeekBar) findViewById(R.id.percentualSeekBar);
        percentualEditText = (EditText) findViewById(R.id.percentualEditText);
        gorjetaEditText = (EditText) findViewById(R.id.gorjetaEditText);
        valorTotaleditText = (EditText) findViewById(R.id.valorTotaleditText);

        //Cria os ouvintes de eventos para views interativas
        contaEditText_.addTextChangedListener(ouvinteContaEdit);
        percentualSeekBar.setOnSeekBarChangeListener(ouvintePercentualSeekBar);

        //Verifica se o aplicativo acabou de ser inciciado ou está sedno restaurado
        if (savedInstanceState == null){
            conta = 0;
            percentual = 7;
        }else {

            //o aplicativo está sendo restaurado de memória, não está sendo executado a partir de zero. Assim, os valores de conta e percentual são restaurados


            conta = savedInstanceState.getDouble(CONTA);
            percentual = savedInstanceState.getDouble(PERCENTUAL);
        }

        //atualiza os componentes gráficos com os valores atualizados
        contaEditText_.setText(String.format("%.2f", conta));
        percentualSeekBar.setProgress((int) percentual);
    }

    private void atualizarFinal(){
        /*double gorjeta = Double.parseDouble(gorjetaEditText.getText().toString());
        double valorfin = conta + gorjeta;
        valorTotaleditText.setText(String.format("%.2f", valorfin));*/
        valorTotaleditText.setText(String.format("%.1f", Calculadora.valorFinal(conta,percentual)));
    }

    //Atualiza o valor das gorjetas padrão
    private void atualizarGorjetas(){
        double [] gorjetas = Calculadora.gorjeta(conta);
        gorjeta5EditText.setText(String.format("%.1f",gorjetas[0]));
        gorjeta10EditText.setText(String.format("%.1f",gorjetas[1]));
        gorjeta15EditText.setText(String.format("%.1f",gorjetas[2]));
        atualizarFinal();
    }

    //Atualiza o valor da gorjeta personalizado
    private  void atualizarGorjetaPersonalizada(){
        gorjetaEditText.setText(String.format("%.1f", Calculadora.gorjeta(conta,percentual)));
        atualizarFinal();
    }

    //define o objeto ouvinte de mudança de texto do contaEditText
    private TextWatcher ouvinteContaEdit = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Este método deve ser sobrescrito, mas não é usado neste app
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                conta = Double.parseDouble(contaEditText_.getText().toString());
            }catch (NumberFormatException e){
                conta = 0;
            }
            atualizarGorjetas();
            atualizarGorjetaPersonalizada();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Este método deve ser sobrescrito, mas não é usado neste app
        }
    };

    //define o objeto ouvinte de mudança no percentualSeekBarr
    private SeekBar.OnSeekBarChangeListener ouvintePercentualSeekBar =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percentual = (double) percentualSeekBar.getProgress();
            percentualEditText.setText(String.format("%.1f", percentual));
            atualizarGorjetaPersonalizada();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //Este método deve ser sobrescrito, mas não é usado neste app
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //Este método deve ser sobrescrito, mas não é usado neste app
        }
    };

    //este método é chamado quando o aplicativo é interrompido. Nele criamos nossa lógica
    //para armazenar as informações que devem ser recuperados quando o aplicativo é reiniciado

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState){
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putDouble(CONTA, conta);
        outState.putDouble(PERCENTUAL, percentual);

    }

    public void btnGorjeta5(View view){
    ValorFinal(5);
    }
    public void btnGorjeta10(View view){
        ValorFinal(10);
    }
    public void btnGorjeta15(View view){
        ValorFinal(15);
    }

    private void ValorFinal(int gorjeta){
        if(gorjeta == 5) {

            EditText num1 = findViewById(R.id.contaEditText);
            EditText num2 = findViewById(R.id.ngorjeta5EditText);

            int d_n1 = Integer.parseInt(num1.getText().toString());
            int d_n2 = Integer.parseInt(num2.getText().toString());


            int soma = d_n1 + d_n2;

            ExibirMensagem("O valor da conta é: "+soma);


        }
        else if(gorjeta == 10){

            EditText num1 = findViewById(R.id.contaEditText);
            EditText num2 = findViewById(R.id.ngorjeta10EditText);

            int d_n1 = Integer.parseInt(num1.getText().toString());
            int d_n2 = Integer.parseInt(num2.getText().toString());


            int soma = d_n1 + d_n2;

            ExibirMensagem("O valor da conta é: "+soma);
        }
        else if(gorjeta == 15){
            EditText num1 = findViewById(R.id.contaEditText);
            EditText num2 = findViewById(R.id.ngorjeta15EditText);

            int d_n1 = Integer.parseInt(num1.getText().toString());
            int d_n2 = Integer.parseInt(num2.getText().toString());


            int soma = d_n1 + d_n2;

            ExibirMensagem("O valor da conta é: "+soma);
        }
    }

    private void ExibirMensagem(String mensagem){
        AlertDialog messageSoma = new AlertDialog.Builder(MainActivity.this).create();
        messageSoma.setTitle("Informação");
        messageSoma.setMessage(mensagem);

        messageSoma.setIcon(R.drawable.ic_launcher_background);

        messageSoma.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Clique aqui em Ok",
                        Toast.LENGTH_SHORT).show();
            }


        });
        messageSoma.show();
    }

}