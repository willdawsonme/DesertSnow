package uts.wsd.soap.client;

import java.rmi.RemoteException;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

public class ArticlesClient {
	private static Articles articleSoap;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		ArticlesServiceLocator locator = new ArticlesServiceLocator();
		articleSoap = locator.getArticlesPort();
		
		System.out.println("What would you like to do? Commands include 'list', 'delete' and 'exit'.");
		String command;
		while (!(command = readCommand()).equals("exit")) {
			execute(command);
 		}
	}
	
	private static void execute(String command) throws RemoteException {
		switch (command) {
			case "list": 	list(); break;
			case "delete": 	delete(); break;
			default:		list();
		}
	}
	
	private static void list() throws RemoteException {
		Article[] articles = articleSoap.fetchArticles();
		if (articles.length > 0) {
			for (Article article : articles)
				System.out.println(article.getId() + ": " + article.getTitle());
		} else {
			System.out.println("No articles found.");
		}
	}
	
	private static void delete() throws RemoteException {
		int id = readId();
		articleSoap.deleteArticle(id);
		System.out.println("Deleted article " + id);
	}
	
	private static String readCommand() {
		System.out.print("Command: ");
		return in.nextLine();
	}
	
	private static int readId() {
		System.out.print("ID: ");
		return in.nextInt();
	}

}
