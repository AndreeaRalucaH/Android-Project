package com.example.proiecthorhociagurita;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExtrageFacturi extends AsyncTask<URL, Void, InputStream> {

    InputStream ist = null;

    public static List<Factura> facturas;
    public static String sbuf;

    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection conn = null;
        try{
            conn = (HttpURLConnection) urls[0].openConnection();
            conn.setRequestMethod("GET");
            ist = conn.getInputStream();

            //apelare functie Parsing
            facturas = Parsing(ist);

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = "";
            while((linie = br.readLine()) != null){
                sbuf += linie + '\n';
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return ist;
    }

    public static Node getNodeByName(String nodeName, Node parentNode) throws Exception {

        if (parentNode.getNodeName().equals(nodeName)) {
            return parentNode;
        }

        NodeList list = parentNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = getNodeByName(nodeName, list.item(i));
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public static String getAttributeValue(Node node, String attrName) {
        try {
            return ((Element) node).getAttribute(attrName);
        } catch (Exception e) {
            return "";
        }
    }

    public List<Factura> Parsing(InputStream isr){
        List<Factura> listF = new ArrayList<>();

        try{
            //creare parser care genereaza obiecte DOM din fisiere XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //creare instanta DOM din fisier XML
            DocumentBuilder db = dbf.newDocumentBuilder();
            //parsare
            Document domDoc = db.parse(isr);
            //structurare continut fisier
            domDoc.getDocumentElement().normalize();

            Node parinte = getNodeByName("Facturi", domDoc.getDocumentElement());
            if(parinte != null){
                NodeList listaCopiiParinte = parinte.getChildNodes();
                for(int i = 0; i < listaCopiiParinte.getLength(); i++){
                    //extrag fiecare nod din lista
                    Node copil = listaCopiiParinte.item(i);

                    if(copil != null && copil.getNodeName().equals("Factura")){
                        Factura factura = new Factura();
                        //parcurgere lista de fii (tag-urile Tag)
                        NodeList taguri = copil.getChildNodes();

                        for(int j = 0; j < taguri.getLength(); j ++){
                            //extrag fiecare nod din listavv
                            Node tag = taguri.item(j);

                            //extrag atribut nod
                            String attribute = getAttributeValue(tag, "atribut");

                            if(attribute.equals("Denumire furnizor")){
                                factura.setDenumireFurn(tag.getTextContent());
                            }

                            if(attribute.equals("Data emitere")){
                                factura.setDataEmitere(tag.getTextContent());
                            }

                            if(attribute.equals("Suma")){
                                factura.setSuma(Integer.parseInt(tag.getTextContent()));
                            }

                            if(attribute.equals("Data scadenta")){
                                factura.setDataScadenta(tag.getTextContent());
                            }

                            if(attribute.equals("Mentiuni")){
                                factura.setMentiuni(tag.getTextContent());
                            }

                        }

                        listF.add(factura);
                    }else Log.e("eroare", "Eroare parsare! Nodul este null");

                }
            }

            return listF;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
