package ge.ufc.webservices;
import com.sun.xml.ws.client.BindingProviderProperties;
import ge.ufc.webservices.client.*;
import ge.ufc.webservices.handler.SoapHandler;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.net.ssl.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// all function except deletePerson, prints person in its body
public class App {
    private static PersonServiceI personServiceI;

    private static ClientProperties clientProperties;

    static {
        try {
            clientProperties = new ClientProperties();

            PersonServiceService personServiceService = new PersonServiceService();
            personServiceI = personServiceService.getPersonServicePort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        BindingProvider bindingProvider = (BindingProvider) personServiceI;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, clientProperties.getUrl());
        bindingProvider.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, clientProperties.getTimeout());
        bindingProvider.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT,  clientProperties.getTimeout());

        SSLContext sslContext = getInsecureSSLContext();
        bindingProvider.getRequestContext().put(BindingProviderProperties.SSL_SOCKET_FACTORY, sslContext.getSocketFactory());
        HostnameVerifier hostnameVerifier = getHostnameVerifier();
        bindingProvider.getRequestContext().put(BindingProviderProperties.HOSTNAME_VERIFIER, hostnameVerifier);
        Binding binding = bindingProvider.getBinding();
        var handlerList = binding.getHandlerChain();
        if (handlerList == null) {
            handlerList = new ArrayList<>();
        }
        handlerList.add(new SoapHandler());
        binding.setHandlerChain(handlerList);
    }
    public static void main(String[] args) {
        try {
            //get
            getPerson(1);

            //update
            Person updatePerson = new Person();
            updatePerson.setId(6);
            updatePerson.setFirstName("Giorgi");
            updatePerson.setLastName("G");
            updatePerson.setAge(35);

            updatePerson(updatePerson);

            //add
            Person addPerson = new Person();
            addPerson.setId(7);
            addPerson.setFirstName("new Person");
            addPerson.setLastName("added");
            addPerson.setAge(10);
            addPerson(addPerson);

            //all
            listPerson();
            //delete
            deletePerson(7);
            //all
            listPerson();

        } catch (PersonNotFoundException_Exception | PersonAlreadyExistsException_Exception e) {
            e.printStackTrace();
        }
    }

    public static Person getPerson(int id) throws PersonNotFoundException_Exception {
        Person p = personServiceI.getPerson(id);
        printPerson(p);
        return p;
    }

    public static boolean updatePerson(Person person) throws PersonNotFoundException_Exception {
        printPerson(person);
        return  personServiceI.updatePerson(person);
    }

    public static boolean addPerson(Person person) throws PersonAlreadyExistsException_Exception {
        printPerson(person);
        return personServiceI.addPerson(person);
    }

    public static boolean deletePerson(int id) throws PersonNotFoundException_Exception {
        return personServiceI.deletePerson(id);
    }

    public static List<Person> listPerson() {
        List<Person> result = personServiceI.listPerson();
        System.out.println("all Persons:");
        for(Person p: result)
            printPerson(p);
        return result;
    }

    private static void printPerson(Person person) {
        if (person == null)
            return;
        String personString = "{Name: " + person.getFirstName() +
                ", LastName: " + person.getLastName() +
                ", Age: " + person.getAge() +
                ", Id: " + person.getId() + '}';
        System.out.println(personString);
    }

    private static SSLContext getInsecureSSLContext() {
        TrustManager[] trustStore = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }};

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustStore, new SecureRandom());
            return sc;
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                return true;
            }
        };
    }

}

