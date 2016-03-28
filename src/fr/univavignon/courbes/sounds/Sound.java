package fr.univavignon.courbes.sounds;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

//TODO trouver un moyen de faire en sorte que la musique change entre différents plateau.
public class Sound extends Thread{
    private AudioFormat format;
    private byte[] samples;
    private String chemin;
    public static int choix;
    
    // lancé par start() dans le luncher
    @Override public void run()
    {
		InputStream stream = new ByteArrayInputStream(this.getSamples()); 
		this.play(stream);	
    }
    
    /**
     * 
     * @param filename le lien vers le fichier song (URL ou absolute path)
     */
    public Sound(String filename){
     try{
      AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
      format = stream.getFormat();
      samples = getSamples(stream);
      chemin=filename;
     }
     catch (UnsupportedAudioFileException e){
      e.printStackTrace();
     }
     catch (IOException e){
      e.printStackTrace();
     }
    }
    public byte[] getSamples(){
     return samples;
    }
    public byte[] getSamples(AudioInputStream stream){
     int length = (int)(stream.getFrameLength() * format.getFrameSize());
     byte[] samples = new byte[length];
     DataInputStream in = new DataInputStream(stream);
     try{
      in.readFully(samples);
     }
     catch (IOException e){
      e.printStackTrace();
     }
     return samples;
    }
    public void play(InputStream source) {
     int bufferSize = format.getFrameSize() * Math.round(format.getSampleRate() / 10);
     byte[] buffer = new byte[bufferSize];
     SourceDataLine line;
     try{
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
      line = (SourceDataLine)AudioSystem.getLine(info);
      line.open(format, bufferSize);
     }
     catch (LineUnavailableException e){
      e.printStackTrace();
      return;
     }
     line.start();
     try{
      int numBytesRead = 0;
      while (numBytesRead != -1){
       numBytesRead = source.read(buffer, 0, buffer.length);
       if (numBytesRead != -1)
        line.write(buffer, 0, numBytesRead);
      }
     }
     catch (IOException e){
     e.printStackTrace();
     }
     line.drain();
     line.close();
    }
}
