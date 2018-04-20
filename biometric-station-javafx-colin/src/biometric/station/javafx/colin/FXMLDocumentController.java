/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometric.station.javafx.colin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author yoshi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private LineChart heartbeatSensorChart;
    @FXML private LineChart accelorometerChart;
    @FXML private LineChart temperatureChart;
    @FXML private LineChart fingerprintSensorChart;
    @FXML private TextField messageBox;
    @FXML private TextArea chatBox;
    
    private XYChart.Series heartbeaatValues;
    private XYChart.Series accelorometerValues;
    private XYChart.Series temperatureValues;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
