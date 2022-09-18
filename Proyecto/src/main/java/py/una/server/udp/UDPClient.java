package py.una.server.udp;


import java.io.*;
import java.net.*;

import py.una.entidad.Mensaje;
import py.una.entidad.MensajeJSON;

class UDPClient {
    public static Mensaje MenuMensaje(){
        Mensaje m = new Mensaje();
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    
            System.out.println("Ingrese el numero de la operacion que quiere realizar:");
            System.out.println("1-Insertar datos");
            System.out.println("2-Consultar vehiculo de mayor monto");
            String strOpcion = inFromUser.readLine();
            Long opcion = 0L;
            try {
                opcion = Long.parseLong(strOpcion);
            }catch(Exception e1) { }
            if (opcion == 1){
                System.out.print("Ingrese el número de cédula (debe ser numérico): ");
                String strcedula = inFromUser.readLine();
                Long cedula = 0L;
                try {
                    cedula = Long.parseLong(strcedula);
                }catch(Exception e1) { }
                
                System.out.print("Ingrese el nombre: ");
                String nombre = inFromUser.readLine();
    
                System.out.print("Ingrese el apellido: ");
                String apellido = inFromUser.readLine();
                
                System.out.print("Ingrese el chapa: ");
                String chapa = inFromUser.readLine();
    
                System.out.print("Ingrese el marca: ");
                String marca = inFromUser.readLine();
    
                System.out.print("Ingrese el monto(debe ser numérico): ");
                String strmonto = inFromUser.readLine();
                Long monto = 0L;
                try {
                    monto = Long.parseLong(strmonto);
                }catch(Exception e1) { }
    
                m = new Mensaje(opcion, cedula, nombre, apellido, chapa, marca, monto);
            }
            else if (opcion == 2){
                m = new Mensaje(opcion);
            }
            else{
                System.out.println("ingrese una opcion valida");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return m;

    }
    public static void main(String a[]) throws Exception {

        String direccionServidor = "server";

        int puertoServidor = 9876;
        
        try {

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            Mensaje m = MenuMensaje();
            
            String datoPaquete = MensajeJSON.objetoString(m); 
            sendData = datoPaquete.getBytes();

            if(m.getTipoMensaje() == 1){
                System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
            }
            else if(m.getTipoMensaje() == 2){
                System.out.println("Enviando operacion tipo:" + m.getTipoMensaje() + " al servidor. ("+ sendData.length + " bytes)");

            }
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperamos si viene la respuesta.(Trabajo de Matias Sosa");

            clientSocket.setSoTimeout(10000);

            try {
                clientSocket.receive(receivePacket);

                String respuesta = new String(receivePacket.getData());
                Mensaje mresp = MensajeJSON.stringObjeto(respuesta.trim());
                
                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                if(mresp.getTipoMensaje() == 1){
                    System.out.println("Infomacion guardada, gracias "+ mresp.getNombre());
                }
                else if(mresp.getTipoMensaje() == 2){
                    System.out.println("El vehiculo es: " + respuesta);
                }
            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 

