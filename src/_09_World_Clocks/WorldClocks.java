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
import javax.swing.JOptionPane;
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
	// arraylist of klockdata

	ClockUtilities clockUtil;
	Timer timer;
	JFrame frame;
	JPanel panel;

	JButton button = new JButton("add a city");

	public WorldClocks() {
		clockUtil = new ClockUtilities();

		// The format for the city must be: city, country (all caps)
		String city = "Chicago, US";
		
		frame = new JFrame();
		panel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300, 300);
		frame.add(panel);
		panel.add(button);
		cityAdd(city);
		button.addActionListener(this);
		
		timer = new Timer(1000, this);
		timer.start();
		

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource().equals(button)) {
			String city = JOptionPane.showInputDialog("enter a city");
			cityAdd(city);			
		}
		else {
		
		for (int i = 0; i < klocks.size(); i++) {

			KlockData e = klocks.get(i);
			Calendar c = Calendar.getInstance(e.timezone);
			// create a new calendar based on the 1st timezone

			String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":"
					+ c.get(Calendar.SECOND);
			String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
					+ c.get(Calendar.SECOND) + "]";
			// create military and 12 hour time

			e.timestr = militaryTime + twelveHourTime;
			// combine both times into a string

			System.out.println(e.timestr);
			// print time

			e.text.setText(e.city + "\n" + e.datestr + "\n" + e.timestr);
			// print city name, date, and time on jtextarea

		}
		}
	}
	
	void cityAdd(String city){
		TimeZone timeZone = clockUtil.getTimeZoneFromCityName(city);

		Calendar calendar = Calendar.getInstance(timeZone);
		// making a calendar object from the timezone we have

		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		String dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.YEAR);
		// creating date

		// making a klock for the 1st timezone and calendar, then storing it in
		// arraylist

		JTextArea text = new JTextArea();
	
		panel.add(text);
		text.setSize(100, 100);
		text.setText(city + "\n" + dateStr);
		// print city name and date on jtext

		// call actionPerformed every second
		

		KlockData klock = new KlockData();
		klock.timezone = timeZone;
		klock.calendar = calendar;
		klock.city = city;
		klock.datestr = dateStr;
		klock.text = text;
		klocks.add(klock);
		
	}
	
	
	
	
	}
	

