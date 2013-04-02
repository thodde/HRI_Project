package org.ros.rosjava_tutorial_pubsub;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

public class Talker extends AbstractNodeMain{
  private static String incMessage;

  @Override
    public GraphName getDefaultNodeName() { return GraphName.of("rosjava_tutorial_pubsub/talker"); }
  @Override
  public void onStart(final ConnectedNode connectedNode) {
    final Publisher<std_msgs.String> publisher =
        connectedNode.newPublisher("chatter", std_msgs.String._TYPE);
    // This CancellableLoop will be canceled automatically when the node shuts
    // down.
    connectedNode.executeCancellableLoop(new CancellableLoop() {
      private int sequenceNumber;
      @Override
      protected void setup() {
	 Thread thread1 = new Thread(){
      public void run(){
 	Imitate.main(null);   
          }
        };

	thread1.start();
	
	//Thread t = new Thread(ComponentExample.main(null));
	incMessage = "Setting up Nodelet...";
	std_msgs.String rosStr = publisher.newMessage();
        sequenceNumber = 0;
      }
      @Override
      public void loop() throws InterruptedException {
	std_msgs.String str1 = publisher.newMessage();
        str1.setData(incMessage);
        publisher.publish(str1);
        sequenceNumber++;
        Thread.sleep(1000);

} });
}
     public void forward( ConnectedNode rosNode) {
	final Publisher<std_msgs.String> publisher2 = rosNode.newPublisher("chatter", std_msgs.String._TYPE);
	std_msgs.String msg = publisher2.newMessage();
	msg.setData(incMessage);
	publisher2.publish(msg);
     }
     public static void setMessage(String str) {
	incMessage = str;
     }	
    public static String getMessage(){
	return incMessage;
    }
}
