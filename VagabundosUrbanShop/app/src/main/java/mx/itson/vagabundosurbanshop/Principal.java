package mx.itson.vagabundosurbanshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import mx.itson.vagabundosurbanshop.webservice.WSProductos;
import mx.itson.vagabundosurbanshop.webservice.WSUsuario;

public class Principal  extends AppCompatActivity implements Runnable{

    Button btnGuardar;
    EditText nombre,cantidad,precio,codigo;

    Handler handler = new Handler();
    ProgressDialog progressDialog;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproductos);
        JavaXML();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Principal.this);
                progressDialog.setTitle("Guardando los datos");
                progressDialog.setMessage("Conectando con el servidor");
                progressDialog.show();
                thread = new Thread(Principal.this);
                thread.start();

            }
        });





    }

    public void JavaXML(){
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        nombre = (EditText)findViewById(R.id.txtNombreProducto);
        cantidad = (EditText)findViewById(R.id.txtCantidadProducto);
        precio=(EditText)findViewById(R.id.txtPrecioProducto);
        codigo=(EditText)findViewById(R.id.txtCodigoProducto);



    }


    @Override
    public void run() {
        WSProductos productos = new WSProductos();

      boolean  respuesta = true;
    final boolean finalRespuesta ;

        try {
            respuesta = productos.agregarProductos(nombre.getText().toString(),cantidad.getText().toString(),precio.getText().toString(),codigo.getText().toString());

        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        finalRespuesta= respuesta;


        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    if (finalRespuesta){

                    Toast.makeText(Principal.this, "Registro Exitoso",Toast.LENGTH_SHORT).show();}

                    else{
                        Toast.makeText(Principal.this, "Ocurrio un error",Toast.LENGTH_SHORT).show();

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
