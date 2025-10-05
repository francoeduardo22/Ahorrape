package com.example.ahorra.actividades;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ahorra.R;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtDni, txtNombre, txtApellido, txtFechaNac, txtCorreo, txtClave, txtClave2;
    Button btnCamara, btnCrear, btnRegresar;
    RadioGroup grpSexo;
    RadioButton rbtNoDefinido, rbtMasculino, rbtFemenino;
    ImageView imgFoto;
    Spinner cboDistrito;
    CheckBox chkTerminos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtDni =findViewById(R.id.regTxtDni);
        txtNombre=findViewById(R.id.regTxtNombre);
        txtApellido=findViewById(R.id.regTxtApellido);
        txtFechaNac=findViewById(R.id.regTxtFechaNac);
        txtCorreo=findViewById(R.id.regTxtCorreo);
        txtClave=findViewById(R.id.regTxtClave);
        txtClave2=findViewById(R.id.regTxtClave2);
        btnCamara=findViewById(R.id.regBtnCamara);
        btnCrear=findViewById(R.id.regBtnCrear);
        btnRegresar=findViewById(R.id.regBtnRegresar);
        grpSexo=findViewById(R.id.regGrpSesp);
        rbtNoDefinido=findViewById(R.id.regRbtNoDefinido);
        rbtMasculino=findViewById(R.id.regRbtMasculino);
        rbtFemenino=findViewById(R.id.regRbtFemenino);
        imgFoto=findViewById(R.id.regImgFoto);
        cboDistrito=findViewById(R.id.regCboDistrito);
        chkTerminos=findViewById(R.id.regChkTerminos);


        btnCamara.setOnClickListener(this);
        btnCrear.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
        txtFechaNac.setOnClickListener(this);
        chkTerminos.setOnClickListener(this);

        btnCrear.setEnabled(false);
        LlenarDistritos();
    }

    private void LlenarDistritos() {
        String[] distritos ={"--Seleecione distrito--","San Juan Lurigancho", "El agustino","Rimac"};
        cboDistrito.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item,distritos));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.regBtnCamara)
            tomarFoto();
        else if (v.getId() == R.id.regBtnCrear)
            crearCuenta();
        else if (v.getId() == R.id.regBtnRegresar)
            regresar();
        else if (v.getId() == R.id.regTxtFechaNac)
            seleccionarFecha();
        else if (v.getId() == R.id.regChkTerminos)
            mostrarTerminos();


    }

    private void tomarFoto() {
    }

    private void crearCuenta() {

    }

    private void regresar() {
        Intent sesion = new Intent(this, SesionActivity.class);
        startActivity(sesion);
        finish();
    }

    private void seleccionarFecha() {
        DatePickerDialog dpd;
        final Calendar fechaActual = Calendar.getInstance();
        int año = fechaActual.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtFechaNac.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, año, mes, dia);
        dpd.show();
        Button btnCancelar = dpd.getButton(DatePickerDialog.BUTTON_NEGATIVE);
        btnCancelar.setAllCaps(false);
        btnCancelar.setText("Cancelar");
        Button btnOk = dpd.getButton(DatePickerDialog.BUTTON_POSITIVE);
        btnOk.setAllCaps(false);
        btnOk.setText("Aceptar");
    }


    private void mostrarTerminos() {

    }
}