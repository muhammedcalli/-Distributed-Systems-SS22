import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Message message = new Message();
		System.out.println("choose a method : ");
		String input = scanner.nextLine();

		if(input.equals("add")){
			message.setMethod(input);
			System.out.println("to which Newspaper you want to add a article? ");
			message.setNewspaperselection(Integer.parseInt(scanner.nextLine()));

			System.out.println("enter articles name: ");
			message.setArticle(scanner.nextLine());

			System.out.println("enter category: ");
			message.setCategory(scanner.nextLine());

			System.out.println("enter article page of number: ");
			message.setPageofNumber(Integer.parseInt(scanner.nextLine()));

		}

		else if(input.equals("get")) {
			message.setMethod(input);
			System.out.println("which article you want to display? ");
			message.setNewspaperselection(Integer.parseInt(scanner.nextLine()));

		}

		try{
			int serverPort = 4242;
			Socket socket = new Socket("::1", serverPort);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			out.writeObject(message);
			Object data = in.readObject();
			System.out.println("Response : " + data);

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	}




