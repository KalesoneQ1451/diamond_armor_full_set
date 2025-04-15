package com.example.diamond_armor_full_set;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView email;
    TextView password;
    Button edit;
    TextView info;
    boolean udalo = true;
    boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        edit = findViewById(R.id.edit);
        info = findViewById(R.id.info);

        info.setText("Witaj! Aplikacja wykonana przez: Kacper Jatczak");

        edit.setOnClickListener(view -> {
            if (status){
                alert();
            } else {
                alertPass(null);
            }
        });
    }

    public void alert(){
        EditText mail = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień email:")
                .setMessage("Podaj email")
                .setView(mail)
                .setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertPass(mail.getText().toString().trim());
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        status = false;
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setBackgroundColor(R.color.orange);
            }
        });
        builder.create().show();
    }
    public void alertPass(String mail){
        status = true;
        TextView info1 = new TextView(this);
        info1.setText("Podaj nowe hasło:");
        TextView info2 = new TextView(this);
        info2.setText("Powtórz hasło:");

        EditText pass = new EditText(this);
        EditText rePass = new EditText(this);
        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        rePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(info1);
        linearLayout.addView(pass);
        linearLayout.addView(info2);
        linearLayout.addView(rePass);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień Hasło")
                .setView(linearLayout)
                .setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String passwordText = pass.getText().toString().trim();
                        String rePasswordText = rePass.getText().toString().trim();
                        if (mail.contains("@")){
                            email.setText(mail);
                        } else {
                            info.setText("Błąd: Nieprawidłowy format emaila.");
                            info.setTextColor(Color.RED);
                            udalo = false;
                        }
                        if (passwordText.equals(rePasswordText)){
                            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setText(passwordText);
                        } else {
                            info.setText("Błąd: Hasła nie pasują do siebie.");
                            info.setTextColor(Color.RED);
                            udalo = false;

                        }
                        if(udalo){
                            info.setText("Profil zaktualizowany! Nowy email: " + mail);
                            info.setTextColor(Color.GREEN);
                        }

                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        info.setText("Edycja profilu anulowana.");
                        info.setTextColor(Color.GRAY);
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setBackgroundColor(R.color.orange);
            }
        });
        builder.create().show();
    }
}