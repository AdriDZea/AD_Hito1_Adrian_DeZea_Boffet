package texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextoController {
	public Label verTexto;
	public TextField anaTexto;
	public Label error;
	public void anadir() {
		error.setText("");
		FileWriter flwriter = null;
		String ruta = ".\\archivo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if(anaTexto.getText().equals("")) {
        	error.setText("No has escrito nada");
        }
        if(archivo.exists()) {
            try {
            	flwriter = new FileWriter(archivo, true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter);
				bfwriter.write("\n" + anaTexto.getText());
				bfwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
        } else {
            try {
				bw = new BufferedWriter(new FileWriter(archivo, true));
				bw.write(anaTexto.getText());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        anaTexto.setText("");
	}
	public void leer() {
		error.setText("");
		String ruta = ".\\archivo.txt";
		String cadena = "";
		try {
			FileReader f = new FileReader(ruta);
			BufferedReader b = new BufferedReader(f);
		      while((ruta = b.readLine())!=null) {
		          cadena = cadena + "\n" + ruta;
		      }
		      verTexto.setText(cadena);
		      b.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
