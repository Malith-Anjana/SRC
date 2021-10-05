package com.example.csse_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Destination_form extends AppCompatActivity {
    Spinner spinner_start,spinner_end, spinner_qty;
    String bal,end_des,dist,quant,st_des, fees;
    Boolean status=false;
    String[] locations = {"Colombo","Galle"};
    String[] locations2 = {"Jaffna","Malabe"};
    Integer[] qtyStrng= {1,2,3,4,5,6,7,8,9,10};
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_form);

        //Hide title bar
        getSupportActionBar().hide();
        //Hide title bar



        TextView tokenId = findViewById(R.id.txt_tokenID);
        TextView balance = findViewById(R.id.txt_balance);
        TextView distance = findViewById(R.id.txt_dist);
        TextView fee = findViewById(R.id.txt_fee);
        spinner_start = findViewById(R.id.spinner3);
        spinner_end = findViewById(R.id.spinner4);
        spinner_qty = findViewById(R.id.spinner5);

        Button send = findViewById(R.id.btn_send);


        Data_Access_Class dac = new Data_Access_Class();






        //Declare start spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, locations);
        spinner_start.setAdapter(dataAdapter);


        //Declare end spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, locations2);
        spinner_end.setAdapter(dataAdapter1);

        //Declare qty spinner
        ArrayAdapter<Integer> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, qtyStrng);
        spinner_qty.setAdapter(dataAdapter2);



        end_des = spinner_end.getSelectedItem().toString();
        quant = spinner_qty.getSelectedItem().toString();

        st_des = spinner_start.getSelectedItem().toString();






        //Start destination Spinner On change
        spinner_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                st_des = spinner_start.getSelectedItem().toString();

                if(st_des=="Colombo" && end_des=="Malabe"){
                    distance.setText("23 km");
                    dist = "23";
                }

                else if(st_des=="Colombo" && end_des=="Jaffna"){
                    distance.setText("304 km");
                    dist = "304";
                }

                else if(st_des=="Galle" && end_des=="Jaffna"){
                    distance.setText("495 km");
                    dist = "495";
                }
                if(st_des=="Galle" && end_des=="Malabe"){
                    distance.setText("117 km");
                    dist = "117";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //End destination Spinner On change
        spinner_end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                end_des = spinner_end.getSelectedItem().toString();
                if(st_des=="Colombo" && end_des=="Malabe"){
                    distance.setText("23 km");
                    dist = "23";
                }

                else if(st_des=="Colombo" && end_des=="Jaffna"){
                    distance.setText("304 km");
                    dist = "304";
                }

                else if(st_des=="Galle" && end_des=="Jaffna"){
                    distance.setText("495 km");
                    dist = "495";
                }
                if(st_des=="Galle" && end_des=="Malabe"){
                    distance.setText("117 km");
                    dist = "1117";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Quantity Spinner On change
        spinner_qty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quant = spinner_qty.getSelectedItem().toString();
                float result = Float.parseFloat("1.20") * Float.parseFloat(quant) * Float.parseFloat(dist);
                if(result==0) {
                    fee.setText("0");
                    fees = "0";
                }
                else{
                    fee.setText(Float.toString(result));
                    fees = Float.toString(result);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        builder = new AlertDialog.Builder(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Confirmation Message!", Toast.LENGTH_SHORT).show();
                builder.setTitle("Alert!!")
                        .setMessage("Confirm to Buy the ticket")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String tokId = tokenId.getText().toString();
                                String fees = fee.getText().toString();
                                bal = balance.getText().toString();

                                String date = getCurrentDate() +" "+ getCurrentTime();


                                Trip_Class trip = new Trip_Class(tokId,st_des,end_des,dist,quant,fees,date);

                                dac.add(trip).addOnSuccessListener(suc->{
                                    Toast.makeText(getApplicationContext(), "Recorded", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Destination_form.this, QR_Show.class);
                                    intent.putExtra("bal", bal);
                                    intent.putExtra("start", st_des);
                                    intent.putExtra("end", end_des);
                                    intent.putExtra("dist", dist);
                                    intent.putExtra("quant", quant);
                                    intent.putExtra("date", date);
                                    startActivity(intent);


                                }).addOnFailureListener(er->{
                                    Toast.makeText(getApplicationContext(), "Fail to record"+er.getMessage(), Toast.LENGTH_SHORT).show();
                                });


                            }
                        })


                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();


            }
        });


    }




    private String getCurrentTime(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
    private String getCurrentDate(){
        return new SimpleDateFormat("dd LLL, yyyy", Locale.getDefault()).format(new Date());
    }



}