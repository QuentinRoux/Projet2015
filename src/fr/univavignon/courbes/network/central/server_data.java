package fr.univavignon.courbes.network.central;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * @author uapv1602792
 *
 */
public class server_data {
    /**
     * @throws Exception
     */
    public ArrayList<String> retrieve_data() throws Exception {
        URL url_ip = new URL("https://pedago02a.univ-avignon.fr/~uapv1602792/projet2015/get_server_list.php");
        URLConnection yc = url_ip.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        ArrayList<String> inputList = new ArrayList<String>();
        String line;
        while ((line = in.readLine()) != null)
        {
        	inputList.add(line);
        }
        in.close();
        return inputList;
    }

    /**
     * @param id_player
     * @param id_server
     * @throws Exception
     */
    public void add_player_server(int id_player, int id_server) throws Exception {
        URL url_ip = new URL("https://pedago02a.univ-avignon.fr/~uapv1602792/projet2015/add_player_server.php");
        String data= "id_player="+id_player+"&&id_server="+id_server;
        HttpURLConnection con = (HttpURLConnection) url_ip.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        DataOutputStream dataOut = new DataOutputStream(
                con.getOutputStream());
        dataOut.writeBytes(data);
        dataOut.flush();
        dataOut.close();
    }
    
    /**
     * @param id_player
     * @param id_server
     * @throws Exception
     */
    public void remove_player_server(int id_player, int id_server) throws Exception {
        URL url_ip = new URL("https://pedago02a.univ-avignon.fr/~uapv1602792/projet2015/remove_player_server.php");
        String data= "id_player="+id_player+"&&id_server="+id_server;
        HttpURLConnection con = (HttpURLConnection) url_ip.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        DataOutputStream dataOut = new DataOutputStream(
                con.getOutputStream());
        dataOut.writeBytes(data);
        dataOut.flush();
        dataOut.close();
    }
    
    public void add_server(int max_player, int server_ip, int server_name, int id_player) throws Exception {
        URL url_ip = new URL("https://pedago02a.univ-avignon.fr/~uapv1602792/projet2015/remove_player_server.php");
        String data= "max_player="+max_player+"&&server_ip="+server_ip+"&&server_name="+server_name+"&&id_player="+id_player;
        HttpURLConnection con = (HttpURLConnection) url_ip.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        DataOutputStream dataOut = new DataOutputStream(
                con.getOutputStream());
        dataOut.writeBytes(data);
        dataOut.flush();
        dataOut.close();
    }
    
    public void remove_server(int id_server) throws Exception {
        URL url_ip = new URL("https://pedago02a.univ-avignon.fr/~uapv1602792/projet2015/remove_player_server.php");
        String data= "id_server="+id_server;
        HttpURLConnection con = (HttpURLConnection) url_ip.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        DataOutputStream dataOut = new DataOutputStream(
                con.getOutputStream());
        dataOut.writeBytes(data);
        dataOut.flush();
        dataOut.close();
    }
}

