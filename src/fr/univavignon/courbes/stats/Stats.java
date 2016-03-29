package fr.univavignon.courbes.stats;

import java.util.HashMap;

import fr.univavignon.courbes.common.Player;



/** Class pour la gestion des statistiques (envoi calcul et reception avec la bdd) */
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
		
		/** Saisi l'id du gagnant de la partie */
		public Stats(int win)
		{
			winner=win;
		}

		/** constructeur */
		public Stats()
		{
			winner=0;
			
		}
		/** init(id gagnant de la partie) */
		public void init(int win)
		{
			winner=win;
		}
		
		/** Récupère l'id des player de la partie */
		public void Chargement_Joueurs(Player[] p,int nb_player)
		{
			nb_players=nb_player;
			for (int i=0;i<nb_player;i++)
			{
				Tab_Joueur[i]=p[i].playerId;
			}
		}
		/**  Entre l'elo des joueur de la partie dans la hmap */
		public void Chargement_Elo()
		{
			int value=0;
			for (int i=0;i<nb_players;i++)
			{
				//TODO Fair une requete au server pour obtenir l'élo du joueur i
				Liste_Score.put(Tab_Joueur[i], value);
			}
		}
		
		public void Envoi_Elo()
		{
			HashMap<Integer,Integer> m=calculateMultiplayer(Liste_Score,winner);
			for (int userId : m.keySet()) 
			{
				//TODO Envoyer au server les nouveaux elo "m.get(userId)"
				
			}
			
		}

		/** Calcul le nouvel elo des joueurs */
		public HashMap<Integer, Integer> calculateMultiplayer(HashMap<Integer, Integer> usersRating, int userIdWinner) {

	        if (usersRating.size() == 0) {
	            return usersRating;
	        }

	        HashMap<Integer, Integer> newUsersPoints = new HashMap<Integer, Integer>();

	        // K-factor
	        int K = 32;

	        // Calculate total Q
	        double Q = 0.0;
	        for (int userId : usersRating.keySet()) {
	            Q += Math.pow(10.0, ((double) usersRating.get(userId) / 400));
	        }

	        // Calculate new rating
	        for (int userId : usersRating.keySet()) {

	            /**
	             * Expected rating for an user
	             * E = Q(i) / Q(total)
	             * Q(i) = 10 ^ (R(i)/400)
	             */
	            double expected = (double) Math.pow(10.0, ((double) usersRating.get(userId) / 400)) / Q;
	                        
	            /**
	             * Actual score is
	             * 1 - if player is winner
	             * 0 - if player losses
	             * (another option is to give fractions of 1/number-of-players instead of 0)
	             */
	            int actualScore;
	            if (userIdWinner == userId) {
	                actualScore = 1;
	            } else {
	                actualScore = 0;
	            }

	            // new rating = R1 + K * (S - E);
	            int newRating = (int) Math.round(usersRating.get(userId) + K * (actualScore - expected));

	            // Add to HashMap
	            newUsersPoints.put(userId, newRating);
	            
	        }

	        return newUsersPoints;
	    }
}
