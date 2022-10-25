package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
	ArrayList<KlockData> klocks = new ArrayList<KlockData>();
	//arraylist of klockdata
	
	ClockUtilities clockUtil;
	Timer timer;
	TimeZone timeZone;
	TimeZone timeZone2;

	JFrame frame;
	JPanel panel;
	JTextArea text;
	JTextArea text2;

	JButton button = new JButton("add a city");

	public WorldClocks() {
		clockUtil = new ClockUtilities();

		// The format for the city must be: city, country (all caps)
		String city = "Chicago, US";
		String city2 = "San Diego, US";

		
		timeZone = clockUtil.getTimeZoneFromCityName(city);
		timeZone2 = clockUtil.getTimeZoneFromCityName(city2);
		

		Calendar calendar = Calendar.getInstance(timeZone);
		//making a calendar object from the timezone we have

		KlockData klock = new KlockData();
		klock.timezone = timeZone;
		klock.calendar = calendar;
		klock.city = city;
		klock.datestr = dateStr;
		klock.timestr = timeStr;
		klocks.add(klock);
		//making a klock for the 1st timezone and calendar, then storing it in arraylist

		

		KlockData klock2 = new KlockData();
		klock2.timezone = timeZone2;
		klock2.calendar = calendar;
		klock2.city = city;
		klock2.datestr = dateStr;
		klock2.timestr = timeStr;
		klocks.add(klock2);
		
	
		
		
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.YEAR);
		//creating date
		
		System.out.println(klocks.get(0).datestr);
		//print date
		
		
		frame = new JFrame();
		panel = new JPanel();
		text = new JTextArea();
		text2 = new JTextArea();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300, 300);
		frame.add(panel);
		panel.add(button);
		panel.add(text);
		panel.add(text2);
		text.setSize(100, 100);
		text2.setSize(100, 100);
		text.setText(city + "\n" + dateStr);
		//print city name and date on jtext
		
		text2.setText(city2 + "\n" + dateStr);

		//call actionPerformed every second
		timer = new Timer(1000, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		for(int i = 0; i < klocks.size();i++) {
			
			
		
		KlockData e = klocks.get(i);
		Calendar c = Calendar.getInstance(e.timezone);
		//create a new calendar based on the 1st timezone

		String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND) + "]";
		//create military and 12 hour time
		

		e.timestr = militaryTime + twelveHourTime;
		//combine both times into a string
		

		System.out.println(e.timestr);
		//print time 
		
		text.setText(e.city + "\n" + e.datestr + "\n" + e.timestr);
		//print city name, date, and time on jtextarea
		
		}
	}
}
