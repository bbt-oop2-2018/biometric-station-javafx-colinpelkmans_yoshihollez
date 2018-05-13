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
import javafx.stage.Stage;

/**
 *
 * @author colin
 */
public class FXMLDocumentController implements Initializable, IMqttMessageHandler {
    
    @FXML private LineChart heartbeatSensorChart;
    @FXML private LineChart accelorometerChart;
    @FXML private LineChart temperatureChart;
    @FXML private LineChart fingerprintSensorChart;
    @FXML private Button generateData;
    
    private XYChart.Series heartbeatValues;
    private XYChart.Series accelorometerValues;
    private XYChart.Series accelorometerValuesY;
    private XYChart.Series accelorometerValuesZ;
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
    
    MqttBroker heartbeat;
    MqttBroker accelerometer;
    MqttBroker temperature;
    
     @FXML
     private void generateRandomDataHandler(ActionEvent event) {
        
        double randomHeartbeat = dataGenerator.nextDouble()
        * (MAXIMUM_HEART - MINIMUM_HEART + 1) + MINIMUM_HEART;
        heartbeatValues.getData().add(new XYChart.Data(xValueHeart, randomHeartbeat));
        xValueHeart++;
        
        double randomAccelerometerX = dataGenerator.nextDouble()
        * (MAXIMUM_ACCEL - MINIMUM_ACCEL + 1) + MINIMUM_ACCEL;
        accelorometerValues.getData().add(new XYChart.Data(xValueAccel, randomAccelerometerX));
        
        double randomAccelerometerY = dataGenerator.nextDouble()
        * (MAXIMUM_ACCEL - MINIMUM_ACCEL + 1) + MINIMUM_ACCEL;
        accelorometerValuesY.getData().add(new XYChart.Data(xValueAccel, randomAccelerometerY));
        
        double randomAccelerometerZ = dataGenerator.nextDouble()
        * (MAXIMUM_ACCEL - MINIMUM_ACCEL + 1) + MINIMUM_ACCEL;
        accelorometerValuesZ.getData().add(new XYChart.Data(xValueAccel, randomAccelerometerZ));
        xValueAccel++;
        
        double randomTemperature = dataGenerator.nextDouble()
        * (MAXIMUM_TEMPERATURE - MINIMUM_TEMPERATURE + 1) + MINIMUM_TEMPERATURE;
        temperatureValues.getData().add(new XYChart.Data(xValueTemp, randomTemperature));
        xValueTemp++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        heartbeatValues = new XYChart.Series();
        heartbeatValues.setName("heartbeat in BPM");
        heartbeatSensorChart.getData().add(heartbeatValues);
        
        accelorometerValues = new XYChart.Series();
        accelorometerValues.setName("X");
        accelorometerChart.getData().add(accelorometerValues);
        
        accelorometerValuesY = new XYChart.Series();
        accelorometerValuesY.setName("Y");
        accelorometerChart.getData().add(accelorometerValuesY);
        
        accelorometerValuesZ = new XYChart.Series();
        accelorometerValuesZ.setName("Z");
        accelorometerChart.getData().add(accelorometerValuesZ);
        
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
      
       
       // Create a chat service and allow this class to receive messages
       heartbeat = new MqttBroker("colin1", "heartbeat");
       heartbeat.setMessageHandler(this);
       
       accelerometer = new MqttBroker("colin2","accelerometer");
       accelerometer.setMessageHandler(this);
 
       temperature = new MqttBroker("colin3","Temp");
       temperature.setMessageHandler(this);
    }    

    @Override
    public void messageArrived(String message, String topic) {
        if (topic.equals("HB")){
            double temperature = Double.parseDouble(message);
            heartbeatValues.getData().add(new XYChart.Data(xValueHeart, heartbeat));
            xValueHeart++;
            System.out.println(heartbeat);
        } else if (topic.equals("Accel")){
          accelorometerValues.getData().add(new XYChart.Data(xValueAccel, accelerometer)); 
          xValueAccel++;
           System.out.println("accelerometer");
        } else if (topic.equals("Temp")){
          temperatureValues.getData().add(new XYChart.Data(xValueTemp, temperature));
        xValueTemp++;   
         System.out.println(temperature);
        }
    }
    
//    private void disconnectClientOnClose() {
//        // Source: https://stackoverflow.com/a/30910015
//        generateData.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
//            if (oldScene == null && newScene != null) {
//               //  scene is set for the first time. Now its the time to listen stage changes.
//                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
//                    if (oldWindow == null && newWindow != null) {
//                       //  stage is set. now is the right time to do whatever we need to the stage in the controller.
//                        ((Stage) newWindow).setOnCloseRequest((event) -> {
//                            MqttBroker.disconnect();
//                        });
//                    }
//                });
//            }
//        });
//    }
}
