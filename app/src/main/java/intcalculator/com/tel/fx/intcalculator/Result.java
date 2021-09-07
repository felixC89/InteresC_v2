package intcalculator.com.tel.fx.intcalculator;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView Pendiente = findViewById(R.id.tvPendiente);
        final TextView Tasa = findViewById(R.id.tvtasa2);
        final TextView Abonados = findViewById(R.id.tvAbonado);
        final TextView Interes = findViewById(R.id.tvInteres);
        final TextView Total = findViewById(R.id.tvtotal);
        final TextView fechacalculo = findViewById(R.id.fechacalculo);
        DecimalFormat formato = new DecimalFormat("#,###.00");

        Total.setPaintFlags(Total.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        Pendiente.setText(formato.format(Double.valueOf(bundle.getString("cpendiente")).doubleValue()));
        Tasa.setText(bundle.getString("tasa")+"%");
        Abonados.setText(bundle.getString("cabonado"));
        fechacalculo.setText(bundle.getString("calculoafecha"));
        Interes.setText(formato.format(Double.valueOf(bundle.getString("intereses")).doubleValue())+"("+bundle.getString("diasintereses")+" días)");
        Total.setText(formato.format(Double.valueOf(bundle.getString("total")).doubleValue()));
    }
}
