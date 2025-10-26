package com.example.ahorra.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ahorra.R;

public class SesionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtCorreo, txtClave;
    CheckBox chkRecordar;

    Button btnIngresar, btnSalir;
    TextView lblRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtCorreo = findViewById(R.id.sesTxtCorreo);
        txtClave = findViewById(R.id.sesTxtClave);
        chkRecordar = findViewById(R.id.sesChkRecordar);
        btnIngresar = findViewById(R.id.sesBtnIngresar);
        btnSalir = findViewById(R.id.sesBtnSalir);
        lblRegistrate = findViewById(R.id.sesLblRegistro);


        btnIngresar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        lblRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.sesBtnIngresar)
            ingresar(txtCorreo.getText().toString(), txtClave.getText().toString());
        else if (v.getId()==R.id.sesBtnSalir)
            salir();
        else if (v.getId()== R.id.sesLblRegistro)
            registrarse();
    }

    private void ingresar(String correo, String clave) {
        Intent bienvenida = new Intent(this, BienvenidoActivity.class);
        bienvenida.putExtra("nombre", "upn - sjl");
        startActivity(bienvenida);
    }
    private void salir() {
        finishAffinity();
    }

    private void registrarse() {
        Intent registrate = new Intent(this, RegistroActivity.class);
        startActivity(registrate);
    }
}