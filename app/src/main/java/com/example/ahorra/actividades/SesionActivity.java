package com.example.ahorra.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns; // Importamos Patterns para validar el email
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Importamos Toast para mostrar mensajes

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ahorra.R;

public class SesionActivity extends AppCompatActivity implements View.OnClickListener {

    // 1. Nombres de variables corregidos
    EditText txtCorreo, txtClave;
    CheckBox chkRecordar;
    Button btnIngresar, btnSalir;
    TextView lblRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // A menudo esto se comenta si causa problemas de layout
        setContentView(R.layout.activity_sesion);

        // 2. Inicialización limpia (una sola vez)
        txtCorreo = findViewById(R.id.sesTxtCorreo);
        txtClave = findViewById(R.id.sesTxtClave);
        chkRecordar = findViewById(R.id.sesChkRecordar);
        btnIngresar = findViewById(R.id.sesBtnIngresar);
        btnSalir = findViewById(R.id.sesBtnSalir);
        lblRegistrate = findViewById(R.id.sesLblRegistro);

        // Configuración de los listeners
        btnIngresar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        lblRegistrate.setOnClickListener(this);

        // El código de insets para EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sesBtnIngresar) {
            // Obtenemos los valores al momento de hacer clic
            String correo = txtCorreo.getText().toString().trim();
            String clave = txtClave.getText().toString().trim();
            ingresar(correo, clave);
        } else if (id == R.id.sesBtnSalir) {
            salir();
        } else if (id == R.id.sesLblRegistro) {
            registrarse();
        }
    }

    // 3. Método ingresar CON VALIDACIÓN
    private void ingresar(String correo, String clave) {
        // --- Validación de campos ---
        if (correo.isEmpty()) {
            txtCorreo.setError("El correo es obligatorio");
            txtCorreo.requestFocus();
            return; // Detenemos la ejecución si el campo está vacío
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            txtCorreo.setError("Por favor, ingrese un correo válido");
            txtCorreo.requestFocus();
            return; // Detenemos si el formato no es válido
        }

        if (clave.isEmpty()) {
            txtClave.setError("La contraseña es obligatoria");
            txtClave.requestFocus();
            return;
        }

        // --- Lógica de autenticación (Ejemplo) ---
        // ¡IMPORTANTE! Reemplaza esto con tu lógica real (Firebase, base de datos, etc.)
        String correoValido = "admin@ahorra.com";
        String claveValida = "123456";

        if (correo.equals(correoValido) && clave.equals(claveValida)) {
            // Si las credenciales son correctas, navegamos
            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
            Intent bienvenida = new Intent(this, BienvenidoActivity.class);
            bienvenida.putExtra("nombre", "upn - sjl"); // Puedes pasar datos si lo necesitas
            startActivity(bienvenida);
            finish(); // Cierra la actividad de sesión para que el usuario no pueda volver
        } else {
            // Si las credenciales son incorrectas
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    private void salir() {
        finishAffinity();
    }

    private void registrarse() {
        Intent registrate = new Intent(this, RegistroActivity.class);
        startActivity(registrate);
    }
}