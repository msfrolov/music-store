package com.epam.msfrolov.musicstore.xml.analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMHandler<T> {
    private static final Logger log = LoggerFactory.getLogger(DOMHandler.class);
    Class clazz;

    public DOMHandler(Class clazz) {
        this.clazz = clazz;
    }

    public T parse(Document document) {
        T result = null;

        Element element = document.getDocumentElement();
        selectNode(element);
        return result;
    }

    private void selectNode(Node node) {
        log.debug("- {}", node.getNodeName());
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                selectNode(item);
            }
        }
    }
}
