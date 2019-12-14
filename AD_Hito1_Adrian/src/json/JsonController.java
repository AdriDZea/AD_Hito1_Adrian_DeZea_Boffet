package json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JsonController {
	String cadena = "";
	ArrayList<OJson> lista = new ArrayList<OJson>();
	int cont = 0;
	String n, a;
	String ed = "";
	int e;
	
	public TextField anaTexto;
	public Label verTexto;
	public TextField nombre;
	public TextField apellido;
	public TextField edad;
	public Label error;
	
	public void leer() throws FileNotFoundException {
		verTexto.setOpacity(1);
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("archivo.json");
        JsonElement datos = parser.parse(fr);
        cadena = "";
        if (datos.isJsonObject()) {
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                leer2(entrada.getValue());
            }
        }
        cadena = "";
	}
	public void anadir() throws FileNotFoundException {
		JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("archivo.json");
        JsonElement datos = parser.parse(fr);
        if (datos.isJsonObject()) {
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                ana2(entrada.getValue());
            }
        }
        if (nombre.getText().equals("") || apellido.getText().equals("") || edad.getText().equals("")) {
        	error.setText("No has escrito nada");
        }
        else {
        	error.setText("");
        	n = nombre.getText();
            a = apellido.getText();
            ed = edad.getText();
            e = Integer.parseInt(ed);
            OJson o = new OJson(n, a, e);
            lista.add(o);
        }       
        Gson gson = new Gson();
		String jsonString = null;
		String personas = "{personas:[\n";
        for(int i = 0; i<lista.size(); i++) {
        	if(i<lista.size()-1) {
        		jsonString = "{" + "'nombre': " + lista.get(i).getNombre() + ",'apellido': " + lista.get(i).getApellido() + ",'edad': " + lista.get(i).getEdad() + "}";
            	personas = personas + jsonString + ",\n";
        	}
        	else {
        		jsonString = "{" + "'nombre': " + lista.get(i).getNombre() + ",'apellido': " + lista.get(i).getApellido() + ",'edad': " + lista.get(i).getEdad() + "}";
            	personas = personas + jsonString + "\n]}";
        	}
        }
        FileWriter flwriter = null;
		String ruta = ".\\archivo.json";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        try {
        	flwriter = new FileWriter(archivo);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(personas);
			bfwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        lista.clear();
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
	}
	public void leer2(JsonElement datos) throws FileNotFoundException {
        if (datos.isJsonObject()) {
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                if(entrada.getKey() .equals("edad")) {
                	cadena = cadena + entrada.getKey() + ":" + entrada.getValue() + "\r" + "----\r";
                }else {
                	cadena = cadena + entrada.getKey() + ":" + entrada.getValue() + "\r";
                }
                verTexto.setText(cadena);
                leer2(entrada.getValue());
            }
        }
        else if(datos.isJsonArray()){
			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();	
			while (iter.hasNext()) {
	            JsonElement entrada = iter.next();
	            leer2(entrada);
	        }
		}
		else if(datos.isJsonPrimitive()) {
		}
		else if(datos.isJsonNull()) {
		}
	}
	public void ana2(JsonElement datos) throws FileNotFoundException {
		if (datos.isJsonObject()) {
            JsonObject obj = datos.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entrada = iter.next();
                if(entrada.getKey() .equals("nombre")) {
                	n = entrada.getValue().toString();
                	cont++;
                }
                else if(entrada.getKey() .equals("apellido")) {
                	a = entrada.getValue().toString();
                	cont++;
                }
                else if(entrada.getKey() .equals("edad")){
                	e = Integer.parseInt(entrada.getValue().toString());
                	cont++;
                }
                if(cont == 3) {
                	OJson o = new OJson(n, a, e);
                	lista.add(o);
                	cont = 0;
                }
                ana2(entrada.getValue());
            }
        }
        else if(datos.isJsonArray()){
			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();

			while (iter.hasNext()) {
	            JsonElement entrada = iter.next();
	            ana2(entrada);
	        }
		}
		else if(datos.isJsonPrimitive()) {
		}
		else if(datos.isJsonNull()) {
		}
	}
}