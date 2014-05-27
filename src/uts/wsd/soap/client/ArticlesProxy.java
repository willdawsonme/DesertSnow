package uts.wsd.soap.client;

public class ArticlesProxy implements uts.wsd.soap.client.Articles {
  private String _endpoint = null;
  private uts.wsd.soap.client.Articles articles = null;
  
  public ArticlesProxy() {
    _initArticlesProxy();
  }
  
  public ArticlesProxy(String endpoint) {
    _endpoint = endpoint;
    _initArticlesProxy();
  }
  
  private void _initArticlesProxy() {
    try {
      articles = (new uts.wsd.soap.client.ArticlesServiceLocator()).getArticlesPort();
      if (articles != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)articles)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)articles)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (articles != null)
      ((javax.xml.rpc.Stub)articles)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uts.wsd.soap.client.Articles getArticles() {
    if (articles == null)
      _initArticlesProxy();
    return articles;
  }
  
  public uts.wsd.soap.client.Article deleteArticle(int arg0) throws java.rmi.RemoteException{
    if (articles == null)
      _initArticlesProxy();
    return articles.deleteArticle(arg0);
  }
  
  public uts.wsd.soap.client.Article[] fetchArticles() throws java.rmi.RemoteException{
    if (articles == null)
      _initArticlesProxy();
    return articles.fetchArticles();
  }
  
  
}