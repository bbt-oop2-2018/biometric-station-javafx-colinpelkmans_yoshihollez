/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometric.station.javafx.merge;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 *
 * @author colin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private LineChart heartbeatSensorChart;
    @FXML private LineChart accelorometerChart;
    @FXML private LineChart temperatureChart;
    @FXML private LineChart fingerprintSensorChart;
    @FXML private Button generateData;
    
    private XYChart.Series heartbeatValues;
    private XYChart.Series accelorometerValues;
    private XYChart.Series temperatureValues;
    
    final NumberAxis xAxisHeart = new NumberAxis();
    final NumberAxis yAxisHeart = new NumberAxis();
    
    final NumberAxis xAxisTemp = new NumberAxis();
    final NumberAxis yAxisTemp = new NumberAxis();
    
    final NumberAxis xAxisAccel = new NumberAxis();
    final NumberAxis yAxisAccel = new NumberAxis();
    
    private Random dataGenerator = new Random();
    
    private final int MAXIMUM_HEART = 200;
    private final int MINIMUM_HEART = 50; 
    private final int MAXIMUM_ACCEL = 50;
    private final int MINIMUM_ACCEL = 0;
    private final int MAXIMUM_TEMPERATURE = 50;
    private final int MINIMUM_TEMPERATURE = 0;
    
    private int xValueHeart = 0;
    private int xValueAccel = 0;
    private int xValueTemp = 0;
    
     @FXML
     private void generateRandomDataHandler(ActionEvent event) {
        
        double randomHeartbeat = dataGenerator.nextDouble()
        * (MAXIMUM_HEART - MINIMUM_HEART + 1) + MINIMUM_HEART;
        System.out.println(randomHeartbeat);
        heartbeatValues.getData().add(new XYChart.Data(xValueHeart, randomHeartbeat));
        xValueHeart++;
        
        double randomAccelerometer = dataGenerator.nextDouble()
        * (MAXIMUM_ACCEL - MINIMUM_ACCEL + 1) + MINIMUM_ACCEL;
        System.out.println(randomAccelerometer);
        accelorometerValues.getData().add(new XYChart.Data(xValueAccel, randomAccelerometer));
        xValueAccel++;
        
        double randomTemperature = dataGenerator.nextDouble()
        * (MAXIMUM_TEMPERATURE - MINIMUM_TEMPERATURE + 1) + MINIMUM_TEMPERATURE;
        System.out.println(randomTemperature);
        temperatureValues.getData().add(new XYChart.Data(xValueTemp, randomTemperature));
        xValueTemp++;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        heartbeatValues = new XYChart.Series();
        heartbeatValues.setName("heartbeat in BPM");
        heartbeatSensorChart.getData().add(heartbeatValues);
        
        accelorometerValues = new XYChart.Series();
        accelorometerValues.setName("speed is m/s");
        accelorometerChart.getData().add(accelorometerValues);
        
        temperatureValues = new XYChart.Series();
        temperatureValues.setName("temperature in °C");
        temperatureChart.getData().add(temperatureValues);

       // Set Axis
       heartbeatSensorChart.getYAxis().setLabel("Heartbeat");
       heartbeatSensorChart.getXAxis().setLabel("Measurement");
       
       accelorometerChart.getYAxis().setLabel("Speed");
       accelorometerChart.getXAxis().setLabel("Measurement");
       
       temperatureChart.getYAxis().setLabel("Temperature");
       temperatureChart.getXAxis().setLabel("Measurement");
    }    
    
}
