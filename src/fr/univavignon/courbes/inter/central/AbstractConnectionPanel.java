package fr.univavignon.courbes.inter.central;

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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import fr.univavignon.courbes.inter.ClientConnectionHandler;
import fr.univavignon.courbes.inter.simpleimpl.AbstractConfigurationPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.SettingsManager;
import fr.univavignon.courbes.network.ClientCommunication;
import fr.univavignon.courbes.network.central.server_data;
import fr.univavignon.courbes.network.simpleimpl.client.ClientCommunicationImpl;

/**
 * Classe permettant à l'utilisateur de choisir un serveur
 * 
 * @author	L3 Info UAPV 2015-16
 */
public abstract class AbstractConnectionPanel extends AbstractConfigurationPanel
{	/** Numéro de série de la classe */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	String ip_server;
	/**
	 * 
	 */
	String port_server;
	/**
	 * 
	 */
	String nom_server;
	/**
	 * Construit un nouveau panel chargé de connecter le client à son serveur.
	 * 
	 * @param mainWindow
	 * 		Fenêtre principale.
	 * @param title
	 * 		Titre de ce panel.
	 */
	public AbstractConnectionPanel(MainWindow mainWindow, String title)
	{	super(mainWindow,title);
	}
	
	@Override
	protected void initContent()
	{	
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(layout);
		add(panel);
		JPanel titlePanel = new JPanel();
		layout = new BoxLayout(titlePanel, BoxLayout.LINE_AXIS);
		titlePanel.setLayout(layout);
		panel.add(titlePanel);
		int height = 20;
		Dimension dim;
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		JLabel ipLabel = new JLabel("Nom de la partie");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dim = new Dimension(500,height);
		ipLabel.setPreferredSize(dim);
		ipLabel.setMaximumSize(dim);
		ipLabel.setMinimumSize(dim);
		ipLabel.setBorder(border);
		titlePanel.add(ipLabel);

		titlePanel.add(Box.createHorizontalGlue());

		JLabel nbPlayerLabel = new JLabel("Joueurs/Joueurs max");
		nbPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dim = new Dimension(500,height);
		nbPlayerLabel.setPreferredSize(dim);
		nbPlayerLabel.setMaximumSize(dim);
		nbPlayerLabel.setMinimumSize(dim);
		nbPlayerLabel.setBorder(border);
		titlePanel.add(nbPlayerLabel);

		titlePanel.add(Box.createHorizontalGlue());
		
		ArrayList<String> list_data;
		server_data data = new server_data();
		try {
			list_data=data.retrieve_data();
			for( String ip_player_server:list_data )
			{
				String[] parts = ip_player_server.split(" ");
				ip_server = parts[0];
				port_server = parts[1];
				String nb_player = parts[2];
				String max_player = parts[3];
				nom_server = parts[4];
				JPanel ipPanel1 = new JPanel();
				layout = new BoxLayout(ipPanel1, BoxLayout.LINE_AXIS);
				ipPanel1.setLayout(layout);
				
				JButton ex1 = new JButton(nom_server);
				ex1.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
					  nextStep();
						
		
				  }
				});
				ex1.setHorizontalAlignment(SwingConstants.CENTER);
				dim = new Dimension(500,height);
				ex1.setPreferredSize(dim);
				ex1.setMaximumSize(dim);
				ex1.setMinimumSize(dim);
				
				JLabel jex1 = new JLabel(nb_player+'/'+max_player);
				jex1.setHorizontalAlignment(SwingConstants.CENTER);
				dim = new Dimension(600,height);
				jex1.setPreferredSize(dim);
				jex1.setMaximumSize(dim);
				jex1.setMinimumSize(dim);
				
				ipPanel1.add(ex1);
				ipPanel1.add(jex1);
				panel.add(ipPanel1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	protected void addServer()
	{	
		
		validate();
		repaint();
	}
	
	/**
	 * Renvoie l'adresse IP par défaut.
	 * 
	 * @return
	 * 		Adresse IP par défaut.
	 */
	public abstract String getDefaultIp();
	
	/**
	 * Renvoie le port TCP par défaut.
	 * 
	 * @return
	 * 		Port TCP par défaut.
	 */
	public abstract int getDefaultPort();
}
