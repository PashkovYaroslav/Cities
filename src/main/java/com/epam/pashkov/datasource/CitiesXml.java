package com.epam.pashkov.datasource;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * Created by Yaroslav on 14.05.2015.
 */
public class CitiesXml implements CitiesDAO {
    private List<String> cities;

    public CitiesXml() {
        getAllCities();
    }

    //Get all cities from database
    public List<String> getAllCities() {
        cities = new ArrayList<String>();
        try
        {
            File xmlFile = new File("cities.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());


            for(int tmp = 0; tmp < nodeList.getLength(); tmp++)
            {
                Node node = nodeList.item(tmp);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    String name = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
                    cities.add(name);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return cities;
    }

    public void writeCitiesListToFile(List<String> cities){
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("cities");
            document.appendChild(rootElement);

            for(String cityStr : cities) {
                Element city = document.createElement("city");
                rootElement.appendChild(city);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(cityStr));
                city.appendChild(name);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("cities.xml"));

            transformer.transform(domSource, streamResult);
            System.out.println("Файл сохранен!");
        }
        catch (ParserConfigurationException pce)
        {
            System.out.println(pce.getLocalizedMessage());
            pce.printStackTrace();
        }
        catch (TransformerException te)
        {
            System.out.println(te.getLocalizedMessage());
            te.printStackTrace();
        }
    }

    public boolean getCity(String title) {
        if(cities.contains(title)){
            return true;
        }
        else{
            return false;
        }
    }
}
