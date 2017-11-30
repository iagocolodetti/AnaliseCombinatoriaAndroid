package iagocolodetti.analisecombinatoria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.text.method.DigitsKeyListener;
import android.content.res.Configuration;
import android.view.inputmethod.InputMethodManager;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    Funcoes f = new Funcoes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Toolbar mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);

        //region Instanciar variáveis
        final TextView tvR = (TextView) findViewById(R.id.textViewR);
        final Spinner sprTipo = (Spinner) findViewById(R.id.spinnerTipo);
        final Button btCalcular = (Button) findViewById(R.id.buttonCalcular);
        final TextView tvN = (TextView) findViewById(R.id.textViewN);
        final TextView tvP = (TextView) findViewById(R.id.textViewP);
        final EditText etN = (EditText) findViewById(R.id.editTextN);
        final EditText etP = (EditText) findViewById(R.id.editTextP);
        //endregion

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        tvR.setText(preferences.getString("tvR", ""));
        tvP.setText(preferences.getString("tvP", ""));
        etN.setText(preferences.getString("etN", ""));
        etP.setText(preferences.getString("etP", ""));
        etP.setEnabled(preferences.getBoolean("etPe", false));

        //region Spinner Tipos

        if(Build.VERSION.SDK_INT < 21) sprTipo.setBackgroundColor(Color.parseColor("#DBDBDB"));
        else sprTipo.setBackgroundTintList(getResources().getColorStateList(R.color.colorSpinnerBackgroundTint));

        String spinnerItens[] = new String[]{"Permutação Simples", "Permutação com Repetição", "Arranjo Simples", "Arranjo com Repetição", "Combinação Simples", "Combinação com Repetição"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textstyle, spinnerItens);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprTipo.setAdapter(dataAdapter);
        sprTipo.setSelection(preferences.getInt("sprTipo", 0));
        sprTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tvN.setText("N");
                        tvP.setText("P");
                        etP.setEnabled(false);
                        break;
                    case 1:
                        tvN.setText("N");
                        tvP.setText("A,B,C,...");
                        etP.setEnabled(true);
                        etP.setKeyListener(DigitsKeyListener.getInstance("1234567890,"));
                        break;
                    case 2: case 3: case 4: case 5:
                        tvN.setText("N");
                        tvP.setText("P");
                        etP.setEnabled(true);
                        etP.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                        break;
                    default:
                        tvN.setText("N");
                        tvP.setText("P");
                        etP.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region Botão Calcular
        btCalcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    int TipoSelecionado = sprTipo.getSelectedItemPosition();
                    int n, p = 0;

                    final int MIN_NUMBER = 1, MAX_NUMBER = 100;

                    String sN = etN.getText().toString();
                    String sP = etP.getText().toString();

                    //region Tratamento de Erro
                    if (TipoSelecionado == 0) {
                        if (sN.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "O campo 'n' está vazio.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!f.intTryParse(sN)) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' não é um inteiro.", Toast.LENGTH_SHORT).show();
                            return;
                        } else n = Integer.parseInt(sN);
                        if (n < MIN_NUMBER || n > MAX_NUMBER) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' deve ser um número inteiro positivo de " + MIN_NUMBER + " a " + MAX_NUMBER + ".", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else if (TipoSelecionado == 1) {
                        if (sN.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "O campo 'n' está vazio.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (sP.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "O campo 'p' está vazio.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!f.intTryParse(sN)) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' não é um inteiro.", Toast.LENGTH_SHORT).show();
                            return;
                        } else n = Integer.parseInt(sN);
                        if (n < MIN_NUMBER || n > MAX_NUMBER) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' deve ser um número inteiro positivo de " + MIN_NUMBER + " a " + MAX_NUMBER + ".", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        if (sN.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "O campo 'n' está vazio.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (sP.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "O campo 'p' está vazio.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!f.intTryParse(sN)) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' não é um inteiro.", Toast.LENGTH_SHORT).show();
                            return;
                        } else n = Integer.parseInt(sN);
                        if (n < MIN_NUMBER || n > MAX_NUMBER) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'n' deve ser um número inteiro positivo de " + MIN_NUMBER + " a " + MAX_NUMBER + ".", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!f.intTryParse(sP)) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'p' não é um inteiro.", Toast.LENGTH_SHORT).show();
                            return;
                        } else p = Integer.parseInt(sP);
                        if (p < MIN_NUMBER || p > MAX_NUMBER) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'p' deve ser um número inteiro positivo de " + MIN_NUMBER + " a " + MAX_NUMBER + ".", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if((TipoSelecionado != 3 && TipoSelecionado != 5) && n < p) {
                            Toast.makeText(getApplicationContext(), "O valor do campo 'p' deve ser menor ou igual ao valor do campo 'n'.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //endregion

                    switch (TipoSelecionado) {
                        case 0:
                            tvR.setText("P(n) -> P(" + sN + ")\n" + f.permutacaoSimples(n).toString());
                            break;

                        case 1:
                            String exibir = "P(n,(a,b,c,...)) -> P(" + sN + ",(";
                            String[] sABC = sP.split(",");
                            int[] pp = new int[sABC.length];
                            for (int i = 0; i < sABC.length; i++) {
                                //region Tratamento de Erro (p por p)
                                if (!f.intTryParse(sABC[i])) {
                                    Toast.makeText(getApplicationContext(), "Um valor do campo 'a,b,c,...' não é um inteiro.", Toast.LENGTH_SHORT).show();
                                    return;
                                } else p = Integer.parseInt(sABC[i]);
                                if (p < MIN_NUMBER || p > MAX_NUMBER) {
                                    Toast.makeText(getApplicationContext(), "Os valores do campo 'a,b,c,...' devem ser números inteiros positivos de " + MIN_NUMBER + " a " + MAX_NUMBER + ".", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                //endregion
                                if (i == 0) exibir += sABC[i];
                                else exibir += "," + sABC[i];
                                pp[i] = p;
                            }
                            //region Tratamento de Erro
                            int s = 0;
                            for(int i = 0; i < pp.length; i++) s += pp[i];
                            if(n < s) {
                                Toast.makeText(getApplicationContext(), "O valor total do campo 'p' deve ser menor ou igual ao valor do campo 'n'.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //endregion
                            tvR.setText(exibir + "))\n" + f.permutacaoRepeticao(n, pp).toString());
                            break;

                        case 2:
                            tvR.setText("A(n,p) -> A(" + sN + "," + sP + ")\n" + f.arranjoSimples(n, p).toString());
                            break;

                        case 3:
                            tvR.setText("AR(n,p) -> A(" + sN + "," + sP + ")\n" + f.arranjoRepeticao(n, p).toString());
                            break;

                        case 4:
                            tvR.setText("C(n,p) -> C(" + sN + "," + sP + ")\n" + f.combinacaoSimples(n, p).toString());
                            break;

                        case 5:
                            tvR.setText("CR(n,p) -> CR(" + sN + "," + sP + ")\n" + f.combinacaoRepeticao(n, p).toString());
                            break;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Erro interno.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //endregion
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menutoolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbarinfo:
                startActivity(new Intent(MainActivity.this, InfosActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final TextView tvR = (TextView) findViewById(R.id.textViewR);
        final Spinner sprTipo = (Spinner) findViewById(R.id.spinnerTipo);
        final TextView tvP = (TextView) findViewById(R.id.textViewP);
        final EditText etN = (EditText) findViewById(R.id.editTextN);
        final EditText etP = (EditText) findViewById(R.id.editTextP);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("tvR", tvR.getText().toString());
        editor.putInt("sprTipo", sprTipo.getSelectedItemPosition());
        editor.putString("tvP", tvP.getText().toString());
        editor.putString("etN", etN.getText().toString());
        editor.putString("etP", etP.getText().toString());
        editor.putBoolean("etPe", etP.isEnabled());
        editor.apply();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final TextView tvR = (TextView) findViewById(R.id.textViewR);
        final Spinner sprTipo = (Spinner) findViewById(R.id.spinnerTipo);
        final TextView tvP = (TextView) findViewById(R.id.textViewP);
        final EditText etN = (EditText) findViewById(R.id.editTextN);
        final EditText etP = (EditText) findViewById(R.id.editTextP);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        tvR.setText(preferences.getString("tvR", ""));
        sprTipo.setSelection(preferences.getInt("sprTipo", 0));
        tvP.setText(preferences.getString("tvP", "P"));
        etN.setText(preferences.getString("etN", ""));
        etP.setText(preferences.getString("etP", ""));
        etP.setEnabled(preferences.getBoolean("etPe", false));
    }

    //region Para os dados não se perderem ao girar a tela (Não entra mais no onCreate ao girar)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        hideSoftKeyboard(this); // Ocultar teclado ao girar a tela
    }
    //endregion

    //region Função para ocultar teclado
    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    //endregion
}
