package com.example.n2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Definindo as variáveis para os componentes da UI
    private EditText txtPrimMeta, txtSegMeta, txtTercMeta, txtSal, txtVenda;
    private TextView lblResultado, lblCom, lblTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializando os componentes da UI
        txtPrimMeta = findViewById(R.id.txtPrimMeta);
        txtSegMeta = findViewById(R.id.txtSegMeta);
        txtTercMeta = findViewById(R.id.txtTercMeta);
        txtSal = findViewById(R.id.txtSal);
        txtVenda = findViewById(R.id.txtVenda);
        lblResultado = findViewById(R.id.lblResultado);
        lblCom = findViewById(R.id.lblCom);
        lblTotal = findViewById(R.id.lblTotal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método btnCalcular, agora fora de onCreate
    public void btnCalcular(View view) {
        // Pegando os valores dos campos EditText
        float prim_meta = Float.parseFloat(txtPrimMeta.getText().toString());
        float seg_meta = Float.parseFloat(txtSegMeta.getText().toString());
        float terc_meta = Float.parseFloat(txtTercMeta.getText().toString());
        float salario = Float.parseFloat(txtSal.getText().toString());
        float vendas = Float.parseFloat(txtVenda.getText().toString());

        float comissao;

        // Lógica para calcular a comissão com base nas metas
        if (vendas < prim_meta) {
            comissao = vendas * 0.02f;
            lblResultado.setText("Você não atingiu nenhuma meta!");
        } else {
            if (vendas >= prim_meta && vendas < seg_meta) {
                comissao = vendas * 0.025f;
                lblResultado.setText("Parabéns! Você atingiu a primeira meta!");
            } else if (vendas >= seg_meta && vendas < terc_meta) {
                comissao = vendas * 0.03f;
                lblResultado.setText("Parabéns! Você atingiu a segunda meta!");
            } else {
                comissao = vendas * 0.035f;
                lblResultado.setText("Parabéns! Você atingiu a terceira meta!");
            }
        }

        // Exibindo a comissão e o total
        lblCom.setText(String.format("%.2f", comissao));
        float total = salario + comissao;
        lblTotal.setText(String.format("%.2f", total));
    }
}
