package com.uneev.app.lastday.last_day_app.communication;

import com.uneev.app.lastday.last_day_app.communication.info.HoroscopeInfo;
import com.uneev.app.lastday.last_day_app.enums.ZodiacSign;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class HoroscopeCommunication implements Communication {

    private final File horoscopeXML = new File("/Users/space/IdeaProjects/last_day_app", "horoscopeXML.xml");

        @Override
    public HoroscopeInfo getInfo() {
        updateHoroscope(); // обновляем файл с гороскопом

        HoroscopeInfo horoscopeInfo = new HoroscopeInfo();

        try { // парсим XML файл
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("horoscopeXML.xml");
            horoscopeXML.deleteOnExit();

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            // Просматриваем все подэлементы корневого - т.е. знаки зодиака
            NodeList zodiacSigns = root.getChildNodes();
            for (int i = 0; i < zodiacSigns.getLength(); i++) {
                Node zodiacSign = zodiacSigns.item(i);
                // Если нода не текст, то это знак зодиака - заходим внутрь
                if (zodiacSign.getNodeType() != Node.TEXT_NODE) {
                    NodeList days = zodiacSign.getChildNodes();
                    for(int j = 0; j < days.getLength(); j++) {
                        Node day = days.item(j);
                        if (!(day.getNodeName().equals("today"))) { // пропускаем гороскопы не на сегодня
                            continue;
                        }
                        // Если нода не текст, то это один из параметров знака зодиака - добавляем прогноз в Map
                        if (day.getNodeType() != Node.TEXT_NODE) {
                            horoscopeInfo.getHoroscope().put(ZodiacSign.valueOf(zodiacSign.getNodeName().toUpperCase()),
                                    day.getChildNodes().item(0).getTextContent());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return horoscopeInfo;
    }

    public void updateHoroscope() {
        try (BufferedInputStream inputStream = new BufferedInputStream(
                new URL("https://ignio.com/r/export/utf/xml/daily/com.xml").openStream());
             FileOutputStream fos = new FileOutputStream(horoscopeXML)) {
            byte[] data = new byte[1024];
            int byteContent;

            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, byteContent);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
