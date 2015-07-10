package com.dhcc.webservice;
/**
 * ͨ�ýӿ�
 */
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WsApiUtil {
	static final String SERVICE_NS = "http://tempuri.org";

	// ����Web Service�������ռ�
	public static void main(String[] sar) {
		//loadData();
	}

	
	/**
	 * 
	 * ���շ��������ص�SoapObject����
	 * @param serviceUrl
	 * @param methodName
	 * @param requestCode
	 * @param requestXml
	 * @return
	 * @throws Exception
	 */
	public static String loadSoapObject(String serviceUrl,String methodName, Map Parrm) throws Exception {
		String retData = null ;
		
		// ����soapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		// ���ò���
			
		Iterator iter = Parrm.entrySet().iterator() ;//�Ȼ�ȡ���map��set���У��ٻ���������еĵ�����
	    while(iter.hasNext()){
	    Map.Entry entry = (Map.Entry)iter.next(); //�õ�������е�ӳ�������set�е����ͣ�HashMap����Map.Entry���ͣ������map�ӿ�������
	     //Integer key = (Integer)entry.getKey(); //���key
	     soapObject.addProperty(entry.getKey().toString(), entry.getValue());
	   
	   } 

		// ����SoapSerializationEnvelope���󣬲�����WebService�汾��
		SoapSerializationEnvelope serializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		// ����serializationEnvelope�����badyOut����
		serializationEnvelope.bodyOut = soapObject;
		// ����HttpTransportSE����,����ȷ��wsdl�����ַ
		HttpTransportSE httpTransportSE = new HttpTransportSE(serviceUrl);//
		
		try {
			// httpTransportSE����Call����
			httpTransportSE.call(SERVICE_NS + "/" + methodName,
					serializationEnvelope);
			// ��ȡ���صĽ������
			if (serializationEnvelope.getResponse() != null) {
				SoapObject result = (SoapObject) serializationEnvelope.bodyIn;
				Object obj =  result.getProperty(methodName+ "Result");
				
				//obj: <Response><ResultCode>0</ResultCode><ResultDesc></ResultDesc><ResultList><Patinfo><name>����</name><sex>��</sex><age>20</age></Patinfo><Patinfo><name>����</name><sex>Ů</sex><age>22</age></Patinfo></ResultList></Response>
				
				retData = obj.toString();
				
			}
			
		} catch (IOException e) {
			throw e;
		} catch (XmlPullParserException e2) {
			throw e2;
		}catch (Exception e3) {
			throw e3;
		}

		return retData ;
	}
	

	
}
