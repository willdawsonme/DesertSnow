/**
 * ArticlesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uts.wsd.soap.client;

public class ArticlesServiceLocator extends org.apache.axis.client.Service implements uts.wsd.soap.client.ArticlesService {

    public ArticlesServiceLocator() {
    }


    public ArticlesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ArticlesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ArticlesPort
    private java.lang.String ArticlesPort_address = "http://localhost:8080/desertsnow/soap/articles";

    public java.lang.String getArticlesPortAddress() {
        return ArticlesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ArticlesPortWSDDServiceName = "ArticlesPort";

    public java.lang.String getArticlesPortWSDDServiceName() {
        return ArticlesPortWSDDServiceName;
    }

    public void setArticlesPortWSDDServiceName(java.lang.String name) {
        ArticlesPortWSDDServiceName = name;
    }

    public uts.wsd.soap.client.Articles getArticlesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ArticlesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getArticlesPort(endpoint);
    }

    public uts.wsd.soap.client.Articles getArticlesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uts.wsd.soap.client.ArticlesPortBindingStub _stub = new uts.wsd.soap.client.ArticlesPortBindingStub(portAddress, this);
            _stub.setPortName(getArticlesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setArticlesPortEndpointAddress(java.lang.String address) {
        ArticlesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uts.wsd.soap.client.Articles.class.isAssignableFrom(serviceEndpointInterface)) {
                uts.wsd.soap.client.ArticlesPortBindingStub _stub = new uts.wsd.soap.client.ArticlesPortBindingStub(new java.net.URL(ArticlesPort_address), this);
                _stub.setPortName(getArticlesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ArticlesPort".equals(inputPortName)) {
            return getArticlesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.wsd.uts/", "ArticlesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.wsd.uts/", "ArticlesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ArticlesPort".equals(portName)) {
            setArticlesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
