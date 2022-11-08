package ge.ufc.webapps.config;

import com.sun.xml.ws.client.BindingProviderProperties;
import ge.ufc.webapps.client.TBCServiceI;
import ge.ufc.webapps.client.TBCServiceService;
import ge.ufc.webapps.handler.SoapHandler;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.MessageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class Server
{
    private static final Logger logger = LogManager.getLogger();
    public static TBCServiceI getProxy()
    {
        TBCServiceI serviceProxy = new TBCServiceService().getTBCServicePort();
        Configuration.Agent agent;
        try {
            agent = Configuration.getConfiguration().getAgent();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        BindingProvider bindingProvider = (BindingProvider) serviceProxy;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, agent.getUrl());
        bindingProvider.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, agent.getTimeout());
        bindingProvider.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, agent.getTimeout());
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestHeaders.put("agent", Collections.singletonList(agent.getId()));
        requestHeaders.put("pass", Collections.singletonList(agent.getPassword()));
        bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        Binding binding = bindingProvider.getBinding();
        var handlerList = binding.getHandlerChain();
        if (handlerList == null) {
            handlerList = new ArrayList<>();
        }
        handlerList.add(new SoapHandler());
        binding.setHandlerChain(handlerList);
        return serviceProxy;
    }
    private Server(){}
}
