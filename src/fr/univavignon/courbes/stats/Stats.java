package fr.univavignon.courbes.stats;

import java.util.HashMap;

import fr.univavignon.courbes.common.Player;

public class Stats {
			
		/** Map de stockage temporaire des score pour l'envoi au central.
		///  HashMap<ID player,Score> */
		public HashMap<Integer,Integer> Liste_Score;
		
		/** Tableau des id des joueur d'on on va géré les statistiques */
		public int Tab_Joueur[];
		/** nombre d'élément du tableau precedent */
		public int nb_players;
		
		/** Id joueur gagnant */
		public int winner;
		
		public Stats(int win)
		{
			winner=win;
		}

		public Stats()
		{
			winner=0;
			
		}
		public void Chargement_Joueurs(Player[] p,int nb_player)
		{
			nb_players=nb_player;
			for (int i=0;i<nb_player;i++)
			{
				Tab_Joueur[i]=p[i].playerId;
			}
		}
		
		public void Chargement_Elo()
		{
			int value=0;
			for (int i=0;i<nb_players;i++)
			{
				
				Liste_Score.put(Tab_Joueur[i], value);
			}
		}
		
		
	

}
