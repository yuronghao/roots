package com.emi.roots.util;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by zhaoyf on 2014/12/25.
 */
public class XmlCataFactory {
    private XmlCataFactory() {
    }
    private Marshaller marshaller;
    public XmlCataFactory(Class<?>... types) {
        try

        {
            JAXBContext jaxbContext = JAXBContext.newInstance(types);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
        } catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将JAVA->xml
     * @param obj
     * @return
     */
    public String marshal(Object obj) {
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                    new CharacterEscapeHandler() {
                        @Override
                        public void escape(char[] ac, int i, int j, boolean flag,
                                           Writer writer) throws IOException {
                            writer.write(ac, i, j);
                        }
                    });
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }finally {
            if(writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
