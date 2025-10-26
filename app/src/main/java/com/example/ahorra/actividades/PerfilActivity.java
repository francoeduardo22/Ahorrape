package com.example.ahorra.actividades; // <-- Asegúrate que el paquete sea este

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.example.ahorra.R;

// Si el nombre de la clase está en rojo, significa que el archivo
// no se llama 'PerfilActivity.java'
public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // 1. Configurar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPerfil);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // 2. Configurar el botón de Cerrar Sesión
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> {
            Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SesionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // 3. Configurar las opciones

        // --- Tarjeta 1.2: Opciones de Cuenta ---
        View opcionCambiarPass = findViewById(R.id.opcionCambiarPass);
        ((ImageView) opcionCambiarPass.findViewById(R.id.imgOpcionIcono)).setImageResource(android.R.drawable.ic_lock_lock);
        ((TextView) opcionCambiarPass.findViewById(R.id.tvOpcionTitulo)).setText(R.string.perfil_cambiar_pass);

        View opcionMetodos = findViewById(R.id.opcionMetodos);
        ((ImageView) opcionMetodos.findViewById(R.id.imgOpcionIcono)).setImageResource(android.R.drawable.ic_menu_myplaces);
        ((TextView) opcionMetodos.findViewById(R.id.tvOpcionTitulo)).setText(R.string.perfil_metodos_reg);

        View opcionEliminar = findViewById(R.id.opcionEliminar);
        ((ImageView) opcionEliminar.findViewById(R.id.imgOpcionIcono)).setImageResource(android.R.drawable.ic_delete);
        ((ImageView) opcionEliminar.findViewById(R.id.imgOpcionIcono)).setColorFilter(getResources().getColor(R.color.colorRojoPeligro));
        ((TextView) opcionEliminar.findViewById(R.id.tvOpcionTitulo)).setText(R.string.perfil_eliminar_cuenta);
        ((TextView) opcionEliminar.findViewById(R.id.tvOpcionTitulo)).setTextColor(getResources().getColor(R.color.colorRojoPeligro));


        // --- Tarjeta 2.1: Opciones de Seguridad ---
        View opcionEncriptados = findViewById(R.id.opcionEncriptados);
        ((ImageView) opcionEncriptados.findViewById(R.id.imgOpcionIcono)).setImageResource(android.R.drawable.ic_lock_lock);
        ((TextView) opcionEncriptados.findViewById(R.id.tvOpcionTitulo)).setText(R.string.perfil_datos_encriptados);
        opcionEncriptados.findViewById(R.id.imgOpcionFlecha).setVisibility(View.GONE);

        View opcionNotificaciones = findViewById(R.id.opcionNotificaciones);
        ((ImageView) opcionNotificaciones.findViewById(R.id.imgOpcionIcono)).setImageResource(android.R.drawable.ic_notification_overlay);
        ((TextView) opcionNotificaciones.findViewById(R.id.tvOpcionTitulo)).setText(R.string.perfil_notificaciones);
        SwitchCompat switchNotif = opcionNotificaciones.findViewById(R.id.switchOpcion);
        switchNotif.setChecked(true);

        // ... (Aquí iría la configuración del resto de opciones) ...
    }
}