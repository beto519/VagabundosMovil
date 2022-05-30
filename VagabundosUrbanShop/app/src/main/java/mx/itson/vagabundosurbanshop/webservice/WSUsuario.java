package mx.itson.vagabundosurbanshop.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class WSUsuario {

    public boolean login(String correo, String contraseña){

        boolean result = false;
        try {


            SoapObject soap = new SoapObject("http://WSUsuario/", "loginUser");
            soap.addProperty("correousuario", correo);
            soap.addProperty("contraseñausuario", contraseña);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soap);
            HttpTransportSE http = new HttpTransportSE("http://209.145.53.75:8080//vagabundosWS/UsuarioWS?WSDL");
            http.call("loginUser", envelope);
            Object respuesta = envelope.getResponse();

            if (respuesta.toString().equalsIgnoreCase("true")) {
                result = true;

            } else {
                result = false;


            }

        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Metodo que realiza una conexion entre android y un web service de tipo SOAP, utilizando la libreria KSOAP y su configuracion
     * pertinente, conectado a un servidor y obteniendo su servicio pertinente para este caso que es agregar usuarios, de esta
     * manera serializamos y los agregamos a un xml ya que en este caso nuestro web service es de este tipo.
     * @param nombre del usuario al que deseas crear
     * @param correo del usuario que sera un dato requerido para iniciar sesión
     * @param contraseña contraseña utilizada para iniciar sesion despues.
     * @return
     */
    public boolean Registrar (String nombre, String correo, String contraseña){

        boolean result = false;
        try {


            SoapObject soap = new SoapObject("http://WSUsuario/", "agregarUsuario");
            soap.addProperty("nombreusuario", nombre);
            soap.addProperty("correousuario", correo);
            soap.addProperty("contraseñausuario", contraseña);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soap);
            HttpTransportSE http = new HttpTransportSE("http://209.145.53.75:8080//vagabundosWS/UsuarioWS?WSDL");
            http.call("agregarUsuario", envelope);
            Object respuesta = envelope.getResponse();

            if (respuesta.toString().equalsIgnoreCase("true")) {
                result = true;

            } else {
                result = false;


            }

        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
