package fr.univavignon.courbes.inter.central;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Courbes
 * Copyright 2015-16 L3 Info UAPV 2015-16
 * 
 * This file is part of Courbes.
 * 
 * Courbes is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 2 of the License, or (at your option) any later version.
 * 
 * Courbes is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR 
 * PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Courbes. If not, see <http://www.gnu.org/licenses/>.
 */

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.univavignon.courbes.inter.ClientConnectionHandler;
import fr.univavignon.courbes.inter.central.AbstractConnectionPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.SettingsManager;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;
import fr.univavignon.courbes.network.ClientCommunication;
import fr.univavignon.courbes.network.central.server_data;
import fr.univavignon.courbes.network.simpleimpl.client.ClientCommunicationImpl;


/**
 * Classe permettant à l'utilisateur de spécifier les information de connexion
 * au serveur, pour configurer une partie réseau.
 * 
 * @author	L3 Info UAPV 2015-16
 */
public class ServerGameSelectionConnectionPanel extends AbstractConnectionPanel implements ClientConnectionHandler
{/** Numéro de série de la classe */
	private static final long serialVersionUID = 1L;
	/** Title du panel */
	private static final String TITLE = "Connexion au serveur";
	
	/**
	 * Construit un nouveau panel chargé de connecter le client à son serveur.
	 * 
	 * @param mainWindow
	 * 		Fenêtre principale.
	 */
	public ServerGameSelectionConnectionPanel(MainWindow mainWindow)
	{	super(mainWindow,TITLE);
	}
	
	@Override
	public String getDefaultIp()
	{	String result = SettingsManager.getLastServerIp();
		return result;
	}

	@Override
	public int getDefaultPort()
	{	int result = SettingsManager.getLastServerPort();
		return result;
	}
	
	/**
	 * Tente de se connecter au serveur.
	 * 
	 * @return
	 * 		Indique si on a au moins pu établir une connexion au serveur ({@code true}) ou pas ({@code false}).
	 */
	private boolean connect()
	{	// on initialise le Moteur Réseau
		ClientCommunication clientCom = new ClientCommunicationImpl();
		mainWindow.clientCom = clientCom;
		clientCom.setErrorHandler(mainWindow);
		clientCom.setConnectionHandler(this);
		
		clientCom.setIp(ip_server);
		SettingsManager.setLastServerIp(ip_server);
		int port = Integer.parseInt(port_server.toString());
		clientCom.setPort(port);
		SettingsManager.setLastServerPort(port);
		
		// puis on se connecte
		boolean result = clientCom.launchClient();
		if(result)
		{
			String id_player;
		
			String id_server;
			server_data data = new server_data();
			try {
				String nom = mainWindow.clientPlayer.profile.userName;
				String password="";
				String fichier ="res/profiles/profiles.txt";
				
				//lecture du fichier texte	
				try{
					InputStream ips=new FileInputStream(fichier); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						String[] parts = ligne.split(",");
						if(parts[0].compareTo(nom)==0)
							password=parts[4];
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				
				id_player=data.get_id_player(nom,password);
				id_server=data.get_id_server(ip_server);
				data.add_player_server(id_player, id_server);
			} catch (Exception e) {
				e.printStackTrace();
			}	 
		}
		return result;
	}
	
	@Override
	public void gotRefused()
	{	SwingUtilities.invokeLater(new Runnable()
		{	@Override
			public void run()
			{	JOptionPane.showMessageDialog(mainWindow, 
					"<html>Le serveur a rejeté votre candidature, car il ne reste "
					+ "<br/>pas de place dans la partie en cours de configuration.</html>");
			}
	    });
	}
	
	@Override
	public void gotAccepted()
	{	SwingUtilities.invokeLater(new Runnable()
		{	@Override
			public void run()
			{	mainWindow.clientCom.setConnectionHandler(null);
				mainWindow.displayPanel(PanelName.CLIENT_GAME_WAIT);
			}
	    });
	}
	
	@Override
	protected void nextStep()
	{	// on se connecte
		boolean connected = connect();
		
		if(connected)
		{	// on désactive les boutons le temps de l'attente
			backButton.setEnabled(false);
			nextButton.setEnabled(false);
		
			// puis on se contente d'attendre la réponse : acceptation ou rejet
			// la méthode correspondante du handler sera automatiquement invoquée
		}
		
		else
		{	JOptionPane.showMessageDialog(mainWindow, 
				"<html>Il n'a pas été possible d'établir la moindre connexion avec le serveur.</html>");
		}
	}

	@Override
	protected void previousStep()
	{	mainWindow.clientCom = null;
		mainWindow.displayPanel(PanelName.CLIENT_GAME_PLAYER_SELECTION);
	}
}

	