package Exercicio1.controller;

import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedesController {

	public static class IpConfigurator {
		public String getNetworkDetails() {
			String os = System.getProperty("os.name").toLowerCase();
			try {
				if (os.contains("win")) {
					Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
					List<String> networkDetailsList = new ArrayList<>(); // Criar a lista fora do loop
					while (networkInterfaces.hasMoreElements()) {
						NetworkInterface networkInterface = networkInterfaces.nextElement();
						if (!networkInterface.isLoopback() && networkInterface.isUp()) {
							List<String> interfaceDetails = getNetworkInterfaceDetails(networkInterface);
							networkDetailsList.addAll(interfaceDetails); // Adicionar os detalhes na lista
						}
					}
					if (!networkDetailsList.isEmpty()) {
						return String.join("\n\n", networkDetailsList);
					}
				} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
	                List<String> networkDetailsList = NetworkInfoLinux();
	                if (!networkDetailsList.isEmpty()) {
	                    return String.join("\n\n", networkDetailsList);
	                }
	            }

	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
	        return "Network details not found";
	    }

		public static List<String> NetworkInfoLinux() {
	        List<String> networkDetailsList = new ArrayList<>();
	        
	        

	        try {
	            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

	            while (networkInterfaces.hasMoreElements()) {
	                NetworkInterface networkInterface = networkInterfaces.nextElement();

	                if (!networkInterface.isUp() || networkInterface.isLoopback())
	                    continue;

	                List<String> interfaceDetails = getNetworkInterfaceDetails(networkInterface);
	                networkDetailsList.addAll(interfaceDetails);
	            }
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }

	        return networkDetailsList;
	    }
		    private static List<String> getNetworkInterfaceDetails(NetworkInterface networkInterface) throws SocketException {
		        List<String> networkDetailsList = new ArrayList<>();
		        
		        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
		        while (inetAddresses.hasMoreElements()) {
		            InetAddress inetAddress = inetAddresses.nextElement();
		            if (inetAddress instanceof Inet4Address) {
		                String networkDetails = "Adapter Name: " + networkInterface.getDisplayName() + "\nIPv4 Address: "
		                        + inetAddress.getHostAddress();
		                networkDetailsList.add(networkDetails);
		            }
		        }
		        
		        return networkDetailsList;
		    }
		}

		public static  void PingTester() {

			String os = System.getProperty("os.name").toLowerCase();

			if (os.contains("win")) {
				runPingWindows();
			} else if (os.contains("nix") || os.contains("nux")) {
				runPingUnix();
			} else {
				System.out.println("Sistema operacional não suportado.");
			}
			
		}

		private static void runPingWindows() {
			try {
			    String command = "ping -n 10 www.google.com.br";  // Comando para Windows, use "ping -c 10 www.google.com.br" para Unix
			    Process process = Runtime.getRuntime().exec(command);
			    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			    String line;
			    String averageTime = null;

			    while ((line = reader.readLine()) != null) {
			        if (line.contains("tempo=") || line.contains("time=")) {
			            int startIndex = line.indexOf("tempo=") + 6;
			            int endIndex = line.indexOf("ms", startIndex);
			            if (endIndex != -1) {
			                averageTime = line.substring(startIndex, endIndex).trim();
			                break;  // Encontrou o tempo médio, pode sair do loop
			            }
			        }
			    }

			    reader.close();

			    if (averageTime != null) {
			        JOptionPane.showMessageDialog(null, "Tempo médio de ping: " + averageTime + " ms");
			    } else {
			        JOptionPane.showMessageDialog(null, "Não foi possível obter o tempo médio de ping.");
			    }
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}

		private static void runPingUnix() {
			try {
			    String command = "ping -c 10 www.google.com.br";  // Comando para Unix
			    Process process = Runtime.getRuntime().exec(command);
			    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			    String line;
			    String averageTime = null;

			    while ((line = reader.readLine()) != null) {
			        if (line.contains("time=")) {
			            int startIndex = line.indexOf("time=") + 5;
			            int endIndex = line.indexOf("ms", startIndex);
			            if (endIndex != -1) {
			                averageTime = line.substring(startIndex, endIndex).trim();
			                break;  // Encontrou o tempo médio, pode sair do loop
			            }
			        }
			    }

			    reader.close();

			    if (averageTime != null) {
			        JOptionPane.showMessageDialog(null, "Tempo médio de ping: " + averageTime + " ms");
			    } else {
			        JOptionPane.showMessageDialog(null, "Não foi possível obter o tempo médio de ping.");
			    }
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}

	}