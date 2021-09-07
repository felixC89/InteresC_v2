package intcalculator.com.tel.fx.intcalculator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Button generar;
    EditText finitial;
    EditText ffinal;
    EditText capactual;
    EditText interes;
    EditText cuota;


    String diain,mesin,anioin;
    String diafin,mesfin,aniofin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generar = findViewById(R.id.btngenerar);
        finitial =  findViewById(R.id.etfinitial);
        ffinal =  findViewById(R.id.etffinal);
        capactual =  findViewById(R.id.etcapactual);
        interes =  findViewById(R.id.etinteres);
        cuota =  findViewById(R.id.etcuota);

        finitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.etfinitial:
                        showDatePickerDialog(finitial);
                        break;
                }
            }
        });

        ffinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.etffinal:
                        showDatePickerDialog(ffinal);
                        break;
                }
            }
        });

        interes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(interes.getText().length()>3){
                    interes.getText().delete(interes.length()-1,interes.length());
                }
            }
        });

        generar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(finitial.getText().length()<=14 && !TextUtils.isEmpty(finitial.getText()))
                {
                    if(ffinal.getText().length()<=14 && !TextUtils.isEmpty(ffinal.getText()))
                    {

                        if(capactual.getText().length()>0 && !TextUtils.isEmpty(capactual.getText()))
                        {
                            if(interes.getText().length()>0 && !TextUtils.isEmpty(interes.getText()))
                            {

                                //****************************************************************************

                                int dias = datecounter(Integer.parseInt(finitial.getText().toString().split("/")[2].trim()),Integer.parseInt(finitial.getText().toString().split("/")[1].trim()),Integer.parseInt(finitial.getText().toString().split("/")[0].trim()),Integer.parseInt(ffinal.getText().toString().split("/")[2].trim()),Integer.parseInt(ffinal.getText().toString().split("/")[1].trim()),Integer.parseInt(ffinal.getText().toString().split("/")[0].trim()));

                                System.out.println("Dias: "+dias);

                                //****************************************************************************

                                double vinteres = Double.parseDouble(interes.getText().toString())/100;
                                //System.out.println(vinteres);

                                double capitalactual= Double.parseDouble(capactual.getText().toString());

                                double cuotaprestamo = 0.0;

                                if(!TextUtils.isEmpty(cuota.getText()))
                                {
                                    cuotaprestamo = Double.parseDouble(cuota.getText().toString());cuotaprestamo = 0.0;
                                }

                                double interespendiente = ((capitalactual * vinteres) / 30) * dias;

                                double capitalab = 0.0;
                                double cappend = capitalactual;

                                if(cuotaprestamo > 0)
                                {
                                    capitalab = cuotaprestamo - interespendiente;

                                    cappend = capitalactual - capitalab;
                                }

                                //System.out.println(interespendiente);

                                //Si se cumple con la longitud requerida continua con la ejecucion del codigo
                                //Toast.makeText(getApplicationContext(),"Pasa! Marzo del 1-16", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(MainActivity.this,Result.class);

                                Bundle bundle = new Bundle();
                                bundle.putString("cpendiente",Double.toString(cappend));
                                bundle.putString("tasa", interes.getText().toString());
                                bundle.putString("cabonado", Double.toString(capitalab));
                                bundle.putString("intereses",Double.toString(interespendiente));
                                bundle.putString("diasintereses",Double.toString(dias));
                                bundle.putString("total",Double.toString(capitalactual + interespendiente));
                                bundle.putString("calculoafecha",ffinal.getText().toString());
                                intent.putExtras(bundle);

                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Formato de interes incorrecto!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Formato de capital incorrecto!", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No Pasa fecha final no es correcta!", Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(),"No Pasa fecha inicial no es correcta!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int datecounter(int anioi,int mesi, int diai,int aniof,int mesf, int diaf)
    {
        Calendar c = Calendar.getInstance();

        Calendar fechaInicio = new GregorianCalendar();

        fechaInicio.set(anioi, mesi, diai);
        System.out.println(anioi+'-'+mesi+'-'+diai);

        Calendar fechaFin = new GregorianCalendar();


        fechaFin.set(aniof, mesf, diaf);


        c.setTimeInMillis(

                fechaFin.getTime().getTime() - fechaInicio.getTime().getTime()
        );

        if(fechaFin.getTime().getTime()<fechaInicio.getTime().getTime())
        {
            Toast.makeText(getApplicationContext(),"Fechas no tienen formato correcto", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),String.valueOf(c.get(Calendar.DAY_OF_YEAR)), Toast.LENGTH_LONG).show();
        }

        return c.get(Calendar.DAY_OF_YEAR);
    }

    private void showDatePickerDialog(final EditText tfecha) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                tfecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

