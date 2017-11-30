package iagocolodetti.analisecombinatoria;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;

public class InfosActivity extends AppCompatActivity {

    String spinnerItens[] = new String[]{
            "Permutação Simples",
            "Permutação com Repetição",
            "Arranjo Simples",
            "Arranjo com Repetição",
            "Combinação Simples",
            "Combinação com Repetição"
    };

    final String[] TextoEx = new String[]{
            "Permutação simples é definida como sendo o número de maneiras de arrumar 'n' elementos em 'n' posições em que cada maneira se diferencia pela ordem em que os elementos aparecem.",
            "Permutação com repetição é definida como sendo o número de maneiras de arrumar 'n' elementos em 'n' posições com repetição de elementos em que cada maneira se diferencia pela ordem em que os elementos aparecem.\nOnde 'n' é o número total de elementos e 'a', 'b',... são os números de repetições de elementos.",
            "Arranjo simples é usado quando a ordem dos elementos importa e cada elemento pode ser contado apenas uma vez.\nOnde 'n' é o número total de elementos e 'p' é a quantidade total de elementos escolhidos.",
            "Arranjo com repetição é usado quando a ordem dos elementos importa e cada elemento pode ser contado mais de uma vez.\nOnde 'n' é o número total de elementos e 'p' é a quantidade total de elementos escolhidos.",
            "Combinação simples é usado quando a ordem dos elementos não importa e cada elemento pode ser contado apenas uma vez.\nOnde 'n' é o número total de elementos e 'p' é a quantidade total de elementos escolhidos.",
            "Combinação com repetição é usado quando a ordem dos elementos não importa e cada elemento pode ser contado mais de uma vez.\nOnde 'n' é o número total de elementos e 'p' é a quantidade total de elementos escolhidos."
    };

    final int[] FormulasEx = new int[]{
            R.drawable.permutacaosimples,
            R.drawable.permutacaocomrepeticao,
            R.drawable.arranjosimples,
            R.drawable.arranjocomrepeticao,
            R.drawable.combinacaosimples,
            R.drawable.combinacaocomrepeticao
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_infos);

        final Toolbar mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);

        //region Instanciar variáveis
        final Button btVoltar = (Button) findViewById(R.id.buttonVoltar);
        final Spinner sprTipoInfo = (Spinner) findViewById(R.id.spinnerTipoInfo);
        final ImageView imgFormulaEx = (ImageView) findViewById(R.id.imageViewFormula);
        final TextView tvEx = (TextView) findViewById(R.id.textViewExplicacao);
        //endregion

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        tvEx.setText(TextoEx[preferences.getInt("InfoTipo", 0)]);
        imgFormulaEx.setImageResource(FormulasEx[preferences.getInt("InfoTipo", 0)]);

        //region Botão Seta
        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //endregion

        //region Spinner Tipos
        if(Build.VERSION.SDK_INT < 21) sprTipoInfo.setBackgroundColor(Color.parseColor("#DBDBDB"));
        else sprTipoInfo.setBackgroundTintList(getResources().getColorStateList(R.color.colorSpinnerBackgroundTint));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textstyle, spinnerItens);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprTipoInfo.setAdapter(dataAdapter);
        sprTipoInfo.setSelection(preferences.getInt("InfoTipo", 0));
        sprTipoInfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imgFormulaEx.setImageResource(FormulasEx[position]);
                tvEx.setText(TextoEx[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        final Spinner sprTipoInfo = (Spinner) findViewById(R.id.spinnerTipoInfo);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("InfoTipo", sprTipoInfo.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final Spinner sprTipoInfo = (Spinner) findViewById(R.id.spinnerTipoInfo);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("InfoTipo", sprTipoInfo.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final Spinner sprTipoInfo = (Spinner) findViewById(R.id.spinnerTipoInfo);
        final ImageView imgFormulaEx = (ImageView) findViewById(R.id.imageViewFormula);
        final TextView tvEx = (TextView) findViewById(R.id.textViewExplicacao);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        sprTipoInfo.setSelection(preferences.getInt("InfoTipo", 0));
        tvEx.setText(TextoEx[preferences.getInt("InfoTipo", 0)]);
        imgFormulaEx.setImageResource(FormulasEx[preferences.getInt("InfoTipo", 0)]);
    }
}
