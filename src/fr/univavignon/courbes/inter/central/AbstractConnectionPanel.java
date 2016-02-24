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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import fr.univavignon.courbes.inter.simpleimpl.AbstractConfigurationPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.local.LocalPlayerConfigPanel;

/**
 * Classe permettant à l'utilisateur de choisir un serveur
 * 
 * @author	L3 Info UAPV 2015-16
 */
public abstract class AbstractConnectionPanel extends AbstractConfigurationPanel
{	/** Numéro de série de la classe */
	private static final long serialVersionUID = 1L;
	
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
		
		JLabel ipLabel = new JLabel("ip de la partie");
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
		
	//	for(int i=0;i<Math.max(1,getMinPlayerNbr());i++)
	//		addServer();
		
		
		
		JLabel ex1 = new JLabel("192.168.0.1");
		JLabel jex1 = new JLabel("4/6");
		panel.add(ex1);
		panel.add(jex1);
		JLabel ex2 = new JLabel("10.104.23.108");
		JLabel jex2 = new JLabel("8/8");
		panel.add(ex2);
		panel.add(jex2);

		JLabel ex3 = new JLabel("10.122.3.176");
		JLabel jex3 = new JLabel("1/3");
		panel.add(ex3);
		panel.add(jex3);
		add(panel);
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
