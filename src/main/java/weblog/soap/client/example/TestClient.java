package weblog.soap.client.example;

import weblog.soap.client.example.model.GetWeatherInformation;
import weblog.soap.client.example.model.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.URL;

public class TestClient {
    public static void main(String[] args) throws Exception {
        QName serviceName = new QName("http://ws.cdyne.com/WeatherWS/", "Weather");
        QName portName = new QName("http://ws.cdyne.com/WeatherWS/", "WeatherSoap");
        Service service = Service.create(new URL("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL"),
                serviceName);

        JAXBContext context = JAXBContext.newInstance("weblog.soap.client.example.model");

        Dispatch<Object> dispatch = service.createDispatch(
                portName,
                context,
                Service.Mode.PAYLOAD);

        ObjectFactory factory = new ObjectFactory();

        GetWeatherInformation input = new GetWeatherInformation();

        System.out.println("Start querying the web service");

        Object responseObj = dispatch.invoke(input);

        System.out.println("Got response");
    }
}