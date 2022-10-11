package _08_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton city = new JButton("City temp");
	JButton weather = new JButton("Weather conditions");
	JButton max = new JButton("Max & Minium temp");
    HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();

	void setup() {
		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.add(panel);
		panel.add(city);
		panel.add(weather);
		panel.add(max);
		city.addActionListener(this);
		weather.addActionListener(this);
		max.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource().equals(city)) {
	        String input = JOptionPane.showInputDialog("Enter a city name");
	        String cityName = Utilities.capitalizeWords(input);
	        WeatherData datum = weatherData.get(cityName);

	        if( datum == null ) {
	            System.out.println("Unable to find weather data for: " + cityName);
	        } else {
	            System.out.println(cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
	        }
			
		}
	
	if (arg0.getSource().equals(weather)) {
        String weather = JOptionPane.showInputDialog("Enter a weather condition");
        for(String c : weatherData.keySet()) {
            WeatherData datum = weatherData.get(c);
            if(datum.weatherSummary.equals(weather)) {
        		System.out.println(c);
        	}
        	
        }
        	
        }
	if (arg0.getSource().equals(max)) {
        String m1 = JOptionPane.showInputDialog("Enter a minium temp");
        String m2 = JOptionPane.showInputDialog("Enter a max temp");
        double min = Double.parseDouble(m1);
        double max = Double.parseDouble(m2);
        for(String d : weatherData.keySet()) {
            WeatherData datum = weatherData.get(d);
            if(datum.temperatureF > min && datum.temperatureF < max) {
        		System.out.println(d);
        	}
        	
        }
        	
        }
	
	
	
	
	}

	}
  

