/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Debasish
 */
public class SendReceive {
public String rslt;
public String rr = "NA";   
    
    
    public String sndrcv(Integer s, SerialPort sp)
    {
        Integer i=s;
        
        //Sending instruction
            System.out.println("Sending: "+i);
            try {
                sp.getOutputStream().write(i);
            } catch (IOException ex) {
                Logger.getLogger(LandingForm.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
            try {
                sp.getOutputStream().flush();
            } catch (IOException ex) {
                Logger.getLogger(LandingForm.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LandingForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            //Reding Response
            
            sp.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent spe) {
                if(spe.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                {
                    return;
                }
                byte[] newData = new byte[sp.bytesAvailable()];
                int numRead = sp.readBytes(newData, newData.length);
                String res = new String(newData,StandardCharsets.UTF_8);
                rr=res;
                System.out.println("Send Receive Response is: "+rr+" : "+newData);
            }
        });
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(LandingForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rr;
    }
    
    
}
