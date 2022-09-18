package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.entidad.Mensaje;
import py.una.entidad.MensajeJSON;

import java.util.ArrayList;
import java.util.List;

public class UDPServer {
	
	
    public static void main(String[] a){
        
        // Variables
        int puertoServidor = 9876;
        ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();
        
        try {
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor Sistemas Distribuidos - UDP ");
			
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

			
            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete,(Trabajo de Matias Sosa)");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                Mensaje m = MensajeJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);

                if(m.getTipoMensaje() == 1){
                    System.out.println("Se detecto una operacion de tipo 1 (Insercion de mensaje)");
                    System.out.println("DatoRecibido: " + datoRecibido );

                    listaMensajes.add(m);
                }
                else if(m.getTipoMensaje() == 2){
                    System.out.println("Se detecto una operacion de tipo 2 (Consulta de vehiculo de monto mayor)");
                    if(listaMensajes.size() == 0){
                        m = new Mensaje();
                    }
                    else{
                        m = listaMensajes.get(0);
                        for(Mensaje maux : listaMensajes){
                            if(m.getMonto() < maux.getMonto()){
                                m = maux;
                            }
                        }
                        m.setTipoMensaje(2L);
                    }
                }
                
                sendData = MensajeJSON.objetoString(m).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);

                serverSocket.send(sendPacket);
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

