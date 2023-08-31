package Exercicio1.view;

import Exercicio1.controller.RedesController;
import Exercicio1.controller.RedesController.IpConfigurator;

import java.io.*;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		

		String menu =  "----------------------------------------------------------------------------- \n" +
				"Selecione uma opção:\n" + "1. DIGITE 1 PARA SABER SEU SISTEMA OPERACIONAL \n"
				+ "----------------------------------------------------------------------------- \n"
				+ "2. DIGITE 2 PARA SABER SEU ENDEREÇO IPV4 \n"
				+ "----------------------------------------------------------------------------- \n"
				+ "3. DIGITE 3 PARA SABER O SEU TEMPO MEDIO DE PING   \n "
				+ "----------------------------------------------------------------------------- \n"
				+ "0. Sair\n"
				+ "----------------------------------------------------------------------------- \n";
		
		
		RedesController redes = new RedesController();
		 IpConfigurator ipConfigurator = new IpConfigurator();
	     String networkDetails = ipConfigurator.getNetworkDetails();
	     
	     
		
		 int opc;
	        do {
	            opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
	            switch (opc) {
	                case 1:
	                	
	                	JOptionPane.showMessageDialog(null,"Nome do sistema operacional: " + getOperatingSystemName());
	                    break;
	                case 2:
	                	JOptionPane.showMessageDialog(null,  " " + networkDetails);
	                    break;
	                case 3:
	                	Exercicio1.controller.RedesController.PingTester();
	                    break;
	                case 0:
	                    JOptionPane.showMessageDialog(null, "Este exercicio vale 1 ponto na media.");
	                    break;
	                default:
	                    JOptionPane.showMessageDialog(null, "Opção inválida. Escolha novamente.");
	            }

	            if (opc != 0) {
	                int voltarAoMenu = JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu anterior?", "Voltar ao Menu", JOptionPane.YES_NO_OPTION);
	                if (voltarAoMenu != JOptionPane.YES_OPTION) {
	                    opc = 0; // Sair do loop se não quiser voltar ao menu
	                }
	            }
	        } while (opc != 0);
	    }
	 private static String getOperatingSystemName() {
	        String osName = System.getProperty("os.name");
	        return osName;
	    }
}
