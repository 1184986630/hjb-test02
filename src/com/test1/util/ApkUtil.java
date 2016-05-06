package com.test1.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;


/**
 * apk工具�?
 * @author lichao
 * Apr 19, 2013 -- 2:03:40 PM
 */
public class ApkUtil {
	
	//private static final Namespace NS = Namespace.getNamespace("http://schemas.android.com/apk/res/android");
	
	
	public static String[] getXmlInputStream(String apkPath) {
		ZipFile zipFile = null;
		//--
		String[] st = new String[4];
		//--
		try {
			zipFile = new ZipFile(apkPath);
			ZipEntry zipEntry = new ZipEntry("AndroidManifest.xml");
			//------------------
			AXmlResourceParser parser=new AXmlResourceParser();
			parser.open(zipFile.getInputStream(zipEntry));
			while (true) {
 				int type=parser.next();
 				if (type==XmlPullParser.END_DOCUMENT) {
 					break;
 				}
 				switch (type) {
 					case XmlPullParser.START_TAG:
 					{
 						for (int i=0;i!=parser.getAttributeCount();++i) {
 							if("versionName".equals(parser.getAttributeName(i))){
 								st[0] = getAttributeValue(parser,i);
 								System.out.println(st[0] );
 							}else if("versionCode".equals(parser.getAttributeName(i))){
 								st[1] = getAttributeValue(parser,i);
 								System.out.println(st[1]);
 							}else if("package".equals(parser.getAttributeName(i))){
 								st[2] = getAttributeValue(parser,i);
 								System.out.println(st[2]);
 							}
 						}
 					}
 				}
 			}
			//------------------
			AXMLPrinter xmlPrinter = new AXMLPrinter();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				zipFile.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
//+++
	private static String getAttributeValue(AXmlResourceParser parser,int index) {
		int type=parser.getAttributeValueType(index);
		int data=parser.getAttributeValueData(index);
		if (type==TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type==TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X",getPackage(data),data);
		}
		if (type==TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type==TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X",data);
		}
		if (type==TypedValue.TYPE_INT_BOOLEAN) {
			return data!=0?"true":"false";
		}
		if (type==TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data))+
				DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type==TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data))+
				FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type>=TypedValue.TYPE_FIRST_COLOR_INT && type<=TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X",data);
		}
		if (type>=TypedValue.TYPE_FIRST_INT && type<=TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>",data,type);
	}
	
	public static float complexToFloat(int complex) {
		return (float)(complex & 0xFFFFFF00)*RADIX_MULTS[(complex>>4) & 3];
	}
	
	private static final float RADIX_MULTS[]={
		0.00390625F,3.051758E-005F,1.192093E-007F,4.656613E-010F
	};
	private static final String DIMENSION_UNITS[]={
		"px","dip","sp","pt","in","mm","",""
	};
	private static final String FRACTION_UNITS[]={
		"%","%p","","","","","",""
	};
	private static String getPackage(int id) {
		if (id>>>24==1) {
			return "android:";
		}
		return "";
	}
   
   
}
