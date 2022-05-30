package mx.itson.vagabundosurbanshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


import mx.itson.vagabundosurbanshop.webservice.WSUsuario;


public class Login extends AppCompatActivity implements Runnable {

    Button btnLogin,btnRegitrar;
    EditText contraseña;
AutoCompleteTextView Usuario;
Handler handler = new Handler();
ProgressDialog  progressDialog;
Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        JavaXML();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setTitle("Iniciando Sesion");
                progressDialog.setMessage("Conectando con el servidor");
                progressDialog.show();
                thread = new Thread(Login.this);
                thread.start();

            }
        });
    }

    public void JavaXML(){
    btnLogin = (Button)findViewById(R.id.btnLogin);
    Usuario = (AutoCompleteTextView) findViewById(R.id.txtUsuario);

    contraseña=(EditText)findViewById(R.id.txtPassword);
btnRegitrar=(Button)findViewById(R.id.btnRegistrarLogin);


btnRegitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent re = new Intent(Login.this,Register.class);

                startActivity(re);

            }
        });


    }

    /**
     *
     */
    @Override
    public void run() {
        WSUsuario usuario = new WSUsuario();

        final boolean  respuesta  = usuario.login(Usuario.getText().toString(),contraseña.getText().toString());
        handler.post(new Runnable() {
            @Override
            public void run() {
            try {
                if (respuesta){

                    Intent principal = new Intent(Login.this,Principal.class);
                    principal.putExtra("correousuario",Usuario.getText().toString());
                    startActivity(principal);

                }else{
                    Toast.makeText(Login.this,"Datos invalidos",Toast.LENGTH_SHORT).show();

                }
            }catch(Exception e){
                e.printStackTrace();

            }finally {
                progressDialog.dismiss();
            }



            }
        });


    }
}
