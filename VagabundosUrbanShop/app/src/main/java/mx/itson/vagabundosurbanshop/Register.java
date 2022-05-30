package mx.itson.vagabundosurbanshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mx.itson.vagabundosurbanshop.webservice.WSUsuario;

public class Register extends AppCompatActivity implements Runnable {

    Button btnRegistrar;
    EditText nombre,correo,contraseña;

    Handler handler = new Handler();
    ProgressDialog progressDialog;
    Thread thread;

    /**
     * Se muestra un mensaje con una muestra de progreso donde se estara instanciando la conexion entre la aplicacion y el servidor.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        JavaXML();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Register.this);
                progressDialog.setTitle("Creando Usuario");
                progressDialog.setMessage("Conectando con el servidor");
                progressDialog.show();
                thread = new Thread(Register.this);
                thread.start();

            }
        });
    }

    /**
     * Metodo para enviar los atributos de java a xml que es un tipo de servicio
     * donde se encuentra alojado nuestro web service, que esta direccionado en un servidor.
     */
    public void JavaXML(){
        btnRegistrar = (Button)findViewById(R.id.btnAgregarUser);
        nombre = (EditText)findViewById(R.id.txtNombreUser);
        correo = (EditText)findViewById(R.id.txtCorreoUser);
        contraseña=(EditText)findViewById(R.id.txtPasswordUser);




    }

    /**
     * De esta forma mandamos llamar el metodo que tenemos en Ws usuario donde nos permitira registrar un usuario, agregando sus
     * datos pertinentes.
     */
    @Override
    public void run() {
        WSUsuario usuario = new WSUsuario();

        final boolean  respuesta  = usuario.Registrar(nombre.getText().toString(),correo.getText().toString()
                ,contraseña.getText().toString());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (respuesta){

                        Intent principal = new Intent(Register.this,Principal.class);
                        principal.putExtra("nombreusuario",nombre.getText().toString());
                        startActivity(principal);

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
