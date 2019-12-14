package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class XmlController {
	public TextField anaTexto;
	public Label verTexto;
	public TextField nombre;
	public TextField apellido;
	public TextField edad;
	public Label error;
	
	Oxml oxml = new Oxml();
	static String cadena = "";
	public void leer() {
		verTexto.setOpacity(1);
		File file = new File("archivo.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("persona");		
			for(int temp = 0; temp < nList.getLength(); temp++) {
				  Node nNode = nList.item(temp);

				  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element eElement = (Element) nNode;
				    cadena = cadena +
				    		"Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent() + "\n" + 
				    		"Apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent() + "\n" +
				    		"Edad: " +eElement.getElementsByTagName("edad").item(0).getTextContent() + "\n" +
				    		"------------\n";
				  }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		verTexto.setText(cadena);
		cadena = "";
	}
	public void anadir() {
		if(nombre.getText().equals("") || apellido.getText().equals("") || edad.getText().equals("")) {
        	error.setText("No has escrito nada");
		}else {
			oxml.setNombre(nombre.getText());
			oxml.setApellido(apellido.getText());
			oxml.setEdad(edad.getText());	
			anadir2(oxml);
		}
	}
	public void anadir2(Oxml xml) {
		error.setText("");
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("archivo.xml"));
			doc.getDocumentElement().normalize();
			Node nodoRaiz = doc.getDocumentElement();
			Element nuevaPersona = doc.createElement("persona");
			Element nuevoNombre = doc.createElement("nombre");
			nuevoNombre.setTextContent(oxml.getNombre());
			Element nuevoApellido = doc.createElement("apellido");
			nuevoApellido.setTextContent(oxml.getApellido());
			Element nuevoEdad = doc.createElement("edad");
			nuevoEdad.setTextContent(oxml.getEdad());
			nuevaPersona.appendChild(nuevoNombre);
			nuevaPersona.appendChild(nuevoApellido);
			nuevaPersona.appendChild(nuevoEdad);
			nodoRaiz.appendChild(nuevaPersona);
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("archivo.xml"));
			transformer.transform(source, result);	
		}catch(ParserConfigurationException parseE) {
			System.out.println(parseE.getMessage());
		}catch(SAXException saxE) {
			System.out.println(saxE.getMessage());
		}catch(IOException ioE) {
			System.out.println(ioE.getMessage());
		}catch(TransformerConfigurationException transE) {
			System.out.println(transE.getMessage());
		}catch(TransformerException transforE) {
			System.out.println(transforE.getMessage());
		}
		nombre.setText("");
		apellido.setText("");
		edad.setText("");
	}
}
