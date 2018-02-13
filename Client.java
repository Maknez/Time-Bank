import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException, UnknownHostException{
	
			String hostName = "localhost";
			int portNumber = 4444;
			String userNick = setNick();
			boolean whileVal = true;
			
			try (Socket echoSocket = new Socket(hostName, portNumber);
			   PrintWriter out =new PrintWriter(echoSocket.getOutputStream(), true);
			   BufferedReader in =new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			   BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));)
		    {
				while (whileVal == true) {
				int menuOption = menu(out);
					switch(menuOption) {
						case 1:
							addService(out,userNick);
							continue;
						case 2:
							deleteService(out,in, userNick);		
							continue;
						case 3:
							printerService(in);
							continue;
						case 4:
							 bookService(out, in, userNick);
							 continue;
						case 5:
							unBookService(out,in, userNick);
							 continue;
						case 6:
							whileVal=false;
							break;
					    default:
							System.out.println("Zły wybór!");
							continue;
					}
				}
			} catch(IOException e) {
				System.out.println(e);
			}
	}
	


	
	static public Integer menu(PrintWriter out){
		Integer valueOfChoice=0;	
		System.out.println("\n****** BANK CZASU ******");
		System.out.println("* 1. Dodaj Usługę      *");
		System.out.println("* 2. Usuń Usługę       *");
		System.out.println("* 3. Sprawdź Usługi    *");
		System.out.println("* 4. Zarezerwuj Usługi *");
		System.out.println("* 5. Odrezerwuj Usługi *");
		System.out.println("* 6. Zakończ           *");
		System.out.println("************************");
		System.out.print("Twój wybór: ");
		Scanner in = new Scanner(System.in);
		valueOfChoice = in.nextInt();
		out.println(valueOfChoice);
		return valueOfChoice;
	}
	
	static public void addService(PrintWriter output, String person) {
		String type,title,localization,date;
		//int day, month, year;
		Scanner in = new Scanner(System.in);
	    System.out.print("\nDodawanie usługi");
        System.out.print("\nTyp usługi: ");
     		type = in.nextLine();
     		output.println(type);
        System.out.print("Tytuł usługi: ");
     		title = in.nextLine();
     		output.println(title);
        System.out.print("Lokalizacja usługi: ");
     		localization = in.nextLine();
     		output.println(localization);
        System.out.println("Data usługi w formacie dd.mm.yyyy: ");
		while(true) {
			date = in.nextLine();
			if (date == null || !date.matches("^(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9]).[0-9]{4}$")) {
				System.out.println("Podana zły format daty! Spróbuj ponownie :)");
			}
			else {
				output.println(date);
				break;
			}
        }
			output.println(person);
    }
	
	static public void deleteService(PrintWriter output, BufferedReader input, String userNick) throws IOException {
		int choice=0;
		Scanner in = new Scanner(System.in);
		System.out.print("\nKtóre zlecenie chcesz usunać?: ");
		choice = in.nextInt();
		if((choice)<0)
		{
			System.out.println("Błędna wartość!)");
		}else {
			output.println(choice);
			output.println(userNick);
			System.out.println(input.readLine());
		}
	}
	
	  static public void printerService(BufferedReader in) throws NumberFormatException, IOException {
		  int buff = Integer.parseInt(in.readLine());
		  if(buff == 0)
		  {
			  System.out.println("\nLista zleceń jest pusta!");
		  }
		  else 
		  {
		    for(int i = 0; i < buff; i++) 
		    		{	
					System.out.println(in.readLine());
					System.out.println(in.readLine());
					System.out.println(in.readLine());
					System.out.println(in.readLine());
					System.out.println(in.readLine());
					System.out.println(in.readLine());
					System.out.println(in.readLine());
			    }
		    }
	  }
	
	  static public void bookService(PrintWriter output, BufferedReader input, String userNick) throws IOException {
			int choice;
			Scanner in = new Scanner(System.in);
			System.out.print("\nKtóre zlecenie chcesz zarezerwować?: ");
			choice = in.nextInt();
			output.println(choice);
			output.println(userNick);
			System.out.println(input.readLine());
		}
		
	  static public void unBookService(PrintWriter output, BufferedReader input, String userNick) throws IOException {
			int choice;
			Scanner in = new Scanner(System.in);
			System.out.print("\nKtóre zlecenie chcesz odrezerwować?: ");
			choice = in.nextInt();
			output.println(choice);
			output.println(userNick);
			System.out.println(input.readLine());
		}
	  
	static public String setNick() {
		String text;
		System.out.println("\nPodaj swój nick: ");
		Scanner in = new Scanner(System.in);
		text=in.nextLine();
		return text;
	}
	
}

	

	
	
	


