import java.util.Calendar;
import java.util.Date;

public class Service {
	private String user;
    private String type;
    private String title;
    private String localization;
    private String date;
    private String booked;
  
    public Service(String user, String type, String title, String localization, String date, String booked) {
		this.user = user;
        this.type = type;
        this.title = title;
        this.localization = localization;
        this.date = date;
        this.booked = booked;
    }

    public void setBooked(String Booked) {
    	this.booked = Booked;
    }
    public String getUser() {
    	return user;
    }
    public String getType() {
    	return type;
    }
    public String getTitle() {
    	return title;
    }
    public String getLocalization() {
    	return localization;
    }
    public String getDate() {
    	return date;
    }
    public String getBooked() {
    	return booked;
    }
}
