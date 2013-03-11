package org.ros.rosjava_tutorial_pubsub;

import java.util.List;

import edu.wpi.cetask.*;
import edu.wpi.disco.*;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.*;

/* 
 * This class illustrates the appropriate methods to use to embed Disco 
 * as a component of a larger software system.  
 * 
 * Important note re threads:  Disco is a single-threaded implementation, which means
 * that the embedding system needs make sure that only one thread at a time is making
 * a call on methods in the Interaction instance below.  (The only exception is
 * synchronized methods, such as Interaction.done()---see source code.)
 * 
 * Important note re turn-taking: Disco does not include a built-in algorithm for
 * turn-taking, as this is a complex topic and typically depends heavily on the
 * application context, e.g., spoken versus text versus graphical interfaces.
 * (The Disco debugging console provides a simple "automatic" mode in which each
 * user turn consists of zero or more non-utterance actions terminated by an 
 * utterance---this can be turned off using the 'next false' command---see
 * console 'help'). 
 *
 * Invoke the main method below to run this example.
 */
public class ComponentExample {

   public static void main (String[] args) { new ComponentExample(); }
   
   private final Interaction interaction = new Interaction(new MyAgent("agent"), new User("user"));
   private final Disco disco = interaction.getDisco();
   private final boolean guess = disco.getProperty("interaction@guess", true);
   
   private ComponentExample () {
         
      // note the try block below creating ConsoleWindow is optional and creates a Disco
      // console window that runs in parallel with the embedding system. This is convenient
      // for debugging, such as using the 'history' or 'status' commands and using
      // 'eval' to check or set application state.
     System.out.println("OPENING NEW CONSOLE WINDOW"); 	
     try (ConsoleWindow window = new ConsoleWindow(interaction, 600, 500, 14)) {

         // the code below is running on the *main* thread, but think of this as an
         // example of the appropriate thread in the embedding application

         // using example task model from Disco source release
         disco.load("SetDinnerTable.xml");

         // typically the embedding system is calling Disco inside of a loop,
         // but here we just have straight-line code to illustrate some
         // typical calls


         // next line is here only to keep optional Disco console window open in 
         // this demo until you type 'quit' or close it
         try { interaction.join(); } catch (InterruptedException e) {}
      }
   }

   private void agent () {
      // see simple model for agent turn at
      // {@link Agent#respond(Interaction,boolean,boolean)}
      interaction.getSystem().respond(interaction, false, guess);
   }
   
   private void user (Task task, Plan contributes) {
      interaction.done(true, task, contributes);
   }
   
   private Task newInstance (String task) { 
      return disco.getTaskClass(task).newInstance();
   }
   
   private static class MyAgent extends Agent {
      
      private MyAgent (String name) { super(name); }
      
      @Override
      public void say (Utterance utterance) {
         // here is where you would put natural language generation
         // and/or pass utterance string to TTS or GUI
         // for now we just call Disco's default formatting and print
         // out result on system console
         System.out.println(utterance.getDisco().formatUtterance(utterance));
      }
   }
}
