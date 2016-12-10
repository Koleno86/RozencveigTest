package nikolay.morshchagin.Xml;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

abstract class XmlParser {
	private String		fileName;
	private Document	document;

	public XmlParser( String fileName ) {
		this.fileName = fileName;
	}

	protected int getChildCount( String parentTag, int parentItem, String childTag ) {
		try {
			NodeList nodeList = document.getElementsByTagName( parentTag );
			Element parentElement = (Element) nodeList.item( parentItem );
			NodeList childList = parentElement.getElementsByTagName( childTag );
			return childList.getLength();
		}
		catch ( NullPointerException ex ) {}
		return 0;
	}

	protected String getChildValue( String parentTag, int parentItem, String childTag, int childItem ) {
		try {
			NodeList nodeList = document.getElementsByTagName( parentTag );
			Element parentElement = (Element) nodeList.item( parentItem );
			NodeList childList = parentElement.getElementsByTagName( childTag );
			Element childElement = (Element) childList.item( childItem );
			Node childNode = childElement.getFirstChild();
			if ( childNode instanceof CharacterData ) {
				CharacterData cd = (CharacterData) childNode;
				return cd.getData();
			}
		}
		catch ( NullPointerException ex ) {}
		return "";
	}

	protected String getChildAttribute( String parentTag, int parentItem, String childTag, int childItem, String attributeTag ) {
		try {
			NodeList nodeList = document.getElementsByTagName( parentTag );
			Element parentElement = (Element) nodeList.item( parentItem );
			NodeList childList = parentElement.getElementsByTagName( childTag );
			Element childElement = (Element) childList.item( childItem );
			return childElement.getAttribute( attributeTag );
		}
		catch ( NullPointerException ex ) {}
		return "";
	}

	public void prepareDocument() {
		InputSource iSrc;
		DocumentBuilderFactory dBF;
		DocumentBuilder dB;
		iSrc = new InputSource( fileName );
		iSrc.setEncoding( "windows-1251" );
		try {
			dBF = DocumentBuilderFactory.newInstance();
			dB = dBF.newDocumentBuilder();
			document = dB.parse( iSrc );
			document.getDocumentElement().normalize();
		}
		catch ( Exception ex ) {
			throw new RuntimeException();
		}
	}

	public int StringToInt( String value ) {
		return ( value == "" ) ? 0 : Integer.valueOf( value );
	}

	abstract public void makeParsing();
}