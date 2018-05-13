/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometric.station.javafx.merge;

/**
 *
 * @author ColinP
 */
public interface IMqttMessageHandler {
    public void messageArrived(String message, String topic);
}
