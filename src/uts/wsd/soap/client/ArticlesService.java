/**
 * ArticlesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uts.wsd.soap.client;

public interface ArticlesService extends javax.xml.rpc.Service {
    public java.lang.String getArticlesPortAddress();

    public uts.wsd.soap.client.Articles getArticlesPort() throws javax.xml.rpc.ServiceException;

    public uts.wsd.soap.client.Articles getArticlesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
