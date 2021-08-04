package intcalculator.com.tel.fx.intcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView Pendiente = findViewById(R.id.tvPendiente);
        final TextView Abonados = findViewById(R.id.tvAbonado);
        final TextView Interes = findViewById(R.id.tvInteres);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        Pendiente.setText(bundle.getString("cpendiente"));
        Abonados.setText(bundle.getString("cabonado"));
        Interes.setText(bundle.getString("intereses")+"("+bundle.getString("diasintereses")+" días)");
    }
}
