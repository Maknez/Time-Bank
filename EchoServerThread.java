import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class EchoServerThread extends Thread 
{
    private Socket socket = null;

    public EchoServerThread(Socket socket)
    {
        super("EchoServerThread");
        this.socket = socket;
    }

    public void run()
    {
		boolean flag = true;
        try(
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); ) {
        	while(flag == true)
			{
        		 String buff = in.readLine();
                 int request = Integer.parseInt(buff);
				switch(request) {
					case 1:
						addServiceRequest(in);
						break;
					case 2:
						deleteServiceRequest(in,out);
						break;
					case 3:
						 printerService(out);
						 break;
					case 4:
						 bookServiceRequest(in,out);
						 break;
					case 5:
						 unBookServiceRequest(in,out);
						 break;
					case 6:
						flag = false;
						break;
				    default:
						break;
				}
			}
       		String buff = in.readLine();
        	int request = Integer.parseInt(buff);
	        if(request == 1) {
		        	 addServiceRequest(in);
		        	 out.println("Dodano!");
         	}
        } catch(Exception e) {}
    }

	static public void addServiceRequest(BufferedReader input) throws IOException {
		String type = input.readLine();
		String title = input.readLine();
		String localization = input.readLine();
		String date = input.readLine();
		String person = input.readLine();	
		Server.list.add(new Service(person, type, title, localization,date,"-"));
	}
	
	static public void deleteServiceRequest(BufferedReader input, PrintWriter out) throws IOException {
		int choice=Integer.parseInt(input.readLine());
		String userNick = input.readLine();
		if(Server.list.size() == 0) {
			out.println("Lista zleceń jest pusta");
		} else {
			if(String.valueOf(Server.list.get(choice - 1).getBooked()) != "-") {
				out.println("Nie można usunąć, ponieważ usługa jest zarezerwowana!");
			} else {
				if(userNick != Server.list.get(choice - 1).getUser()) {
				out.println("Nie jesteś właścicielem zlecenia nr: " + (choice) + "!");
				}
				else {
					Server.list.remove(choice - 1);
					out.println("Usunięto zlecenie nr: " + (choice));
				}
			}
		}
	}
	
	static public void bookServiceRequest(BufferedReader input, PrintWriter out) throws IOException {
		int choice=Integer.parseInt(input.readLine());
		Scanner in = new Scanner(System.in);
		choice = choice - 1;
		String userNick = input.readLine();
		if("-".equals(String.valueOf(Server.list.get(choice).getBooked()))) {
			((Service) Server.list.get(choice)).setBooked(userNick);
			out.println("Zarezerwowano zlecenie nr:" + (choice + 1));
		} else {
			out.println("Zlecenie zostało juz zarezerwowane przez kogoś innego!");
		}
	}
	
	static public void unBookServiceRequest(BufferedReader input, PrintWriter out) throws IOException {
		int choice = Integer.parseInt(input.readLine());
		String userNick = input.readLine();
		Scanner in = new Scanner(System.in);
		choice = choice - 1;

		if(String.valueOf(Server.list.get(choice).getBooked()) == "-") {
			out.println("Zlecenie zostało juz odrezerwowane!");
		} else {
			if(userNick.equals(String.valueOf(Server.list.get(choice).getBooked()))) {
				((Service) Server.list.get(choice)).setBooked("-");
				out.println("Odrezerwowano zlecenie nr:" + (choice + 1));
			}
			else {
				out.println("Nie ty zarezerwowałeś zlecenie nr: " + (choice + 1) + "!");				
				}
			}
	}
	
	static public void printerService(PrintWriter out) {
		out.println(Server.list.size());
		for(int i = 0; i < Server.list.size(); i++) {
			String buff="Usługa nr." + String.valueOf(i + 1);
			out.println(buff);
			buff="Właściciel usługi: " + String.valueOf(Server.list.get(i).getUser());
			out.println(buff);
			buff="Typ usługi: " + String.valueOf(Server.list.get(i).getType());
			out.println(buff);
			buff="Tytuł usługi: " + String.valueOf(Server.list.get(i).getTitle());
			out.println(buff);
			buff="Lokalizacja usługi: " + String.valueOf(Server.list.get(i).getLocalization());
			out.println(buff);
			buff="Data usługi: " + String.valueOf(Server.list.get(i).getDate());
			out.println(buff);
			buff="Rezerwacja usługi: " + String.valueOf(Server.list.get(i).getBooked());
			out.println(buff);
		}
	}
	
}
     
   

