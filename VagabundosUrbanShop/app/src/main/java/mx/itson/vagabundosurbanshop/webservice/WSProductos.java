package mx.itson.vagabundosurbanshop.webservice;

import android.util.Xml;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WSProductos {



  public Object VisualizarProductos() throws IOException, XmlPullParserException {

        boolean result = false;

        SoapObject soap = new SoapObject("http://WSProducto/", "buscarTodos");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE http = new HttpTransportSE("http://209.145.53.75:8080//vagabundosWS/ProductoWS?WSDL");
        http.call("buscarTodos", envelope);
        Object respuesta = envelope.getResponse();


      return respuesta;
  }


    public boolean agregarProductos(String nombre,String cantidad, String precio ,String codigo) throws SoapFault {

        boolean result = true;
        try {

        SoapObject soap = new SoapObject("http://WSProducto/", "agregar");
        soap.addProperty("nombreProducto", nombre);
        soap.addProperty("cantidad", cantidad);
        soap.addProperty("precio", precio);
        soap.addProperty("codigoProducto", codigo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE http = new HttpTransportSE("http://209.145.53.75:8080//vagabundosWS/ProductoWS?WSDL");

            http.call("agregar", envelope);
            Object respuesta = envelope.getResponse();
            if (respuesta.toString().equalsIgnoreCase("true")) {
                result = true;

            } else {
                result = false;


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }






        return result;
    }


}
