package com.nilscreation.unitconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrayList;
    EditText etInput;
    TextView etOutput;
    ImageView imgSwap;
    TextView txtunit1, txtunit2;
    Button btnConvert;
    float result;
    boolean swap = false;
    String selectedUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        etInput = findViewById(R.id.input);
        etOutput = findViewById(R.id.output);
        imgSwap = findViewById(R.id.swap);
        txtunit1 = findViewById(R.id.txtunit1);
        txtunit2 = findViewById(R.id.txtunit2);
        btnConvert = findViewById(R.id.btnConvert);

        arrayList = new ArrayList();
        arrayList.add("Length");
        arrayList.add("Weight");
        arrayList.add("Time");
        arrayList.add("Temperature");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = arrayList.get(position);
                swap = false;
                calculate();
                if (selectedUnit == "Length") {
                    txtunit1.setText("Meter");
                    txtunit2.setText("Centimeter");
                } else if (selectedUnit == "Weight") {
                    txtunit1.setText("Kilogram");
                    txtunit2.setText("Gram");
                } else if (selectedUnit == "Time") {
                    txtunit1.setText("Minute");
                    txtunit2.setText("Second");
                } else if (selectedUnit == "Temperature") {
                    txtunit1.setText("Degree Celsius");
                    txtunit2.setText("Kelvin");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        imgSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap = !swap;
                calculate();
                if (selectedUnit == "Length") {
                    if (swap) {
                        txtunit1.setText("Centimeter");
                        txtunit2.setText("Meter");
                    } else {
                        txtunit1.setText("Meter");
                        txtunit2.setText("Centimeter");
                    }
                } else if (selectedUnit == "Weight") {
                    if (swap) {
                        txtunit1.setText("Gram");
                        txtunit2.setText("Kilogram");
                    } else {
                        txtunit1.setText("Kilogram");
                        txtunit2.setText("Gram");
                    }
                } else if (selectedUnit == "Time") {
                    if (swap) {
                        txtunit1.setText("Second");
                        txtunit2.setText("Minute");
                    } else {
                        txtunit1.setText("Minute");
                        txtunit2.setText("Second");
                    }
                }
                else if (selectedUnit == "Temperature") {
                    if (swap) {
                        txtunit1.setText("Kelvin");
                        txtunit2.setText("Degree Celsius");
                    } else {
                        txtunit1.setText("Degree Celsius");
                        txtunit2.setText("Kelvin");
                    }
                }
            }
        });
    }

    private void calculate() {
        String inputtext = etInput.getText().toString();
        if (!inputtext.isEmpty()) {
            if (selectedUnit == "Length") {
                if (!swap) {
                    result = (Float.parseFloat(inputtext) * 100);
                    etOutput.setText("" + result);
                } else {
                    result = (Float.parseFloat(inputtext) / 100);
                    etOutput.setText("" + result);
                }
            } else if (selectedUnit == "Weight") {
                if (!swap) {
                    result = (Float.parseFloat(inputtext) * 1000);
                    etOutput.setText("" + result);
                } else {
                    result = (Float.parseFloat(inputtext) / 1000);
                    etOutput.setText("" + result);
                }
            } else if (selectedUnit == "Time") {
                if (!swap) {
                    result = (Float.parseFloat(inputtext) * 60);
                    etOutput.setText("" + result);
                } else {
                    result = (Float.parseFloat(inputtext) / 60);
                    etOutput.setText("" + result);
                }
            }else if (selectedUnit == "Temperature") {
                if (!swap) {
                    result = (float) (Float.parseFloat(inputtext) + 273.15);
                    etOutput.setText("" + result);
                } else {
                    result = (float) (Float.parseFloat(inputtext) - 273.15);
                    etOutput.setText("" + result);
                }
            }
        } else {
        }
    }

    @Override
    public void onBackPressed() {
        exitDialog();
    }

    private void exitDialog() {
        AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
        exit.setTitle("Exit");
        exit.setMessage("Do you really want to Exit?");
        exit.setIcon(R.drawable.logo);
        exit.setCancelable(false);

        exit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        exit.show();
    }
}