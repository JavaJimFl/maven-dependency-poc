package org.kaib.discover;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class MavenDependencyFinder {

	public static void main(String[] args) throws Exception {

		final Path textDependencyTreePath = Paths.get("C:/temp/myfile.txt");
		final Document document = parseTextDependencyTree(textDependencyTreePath);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);

	}

	private static Document parseTextDependencyTree(final Path textDependencyTreeFile) {

		return DependencyTreeParser.parse(textDependencyTreeFile);
	}

}
