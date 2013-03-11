package org.ros.rosjava_tutorial_pubsub;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

public class Talker extends AbstractNodeMain {
  public String incMessage;

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
	incMessage = "setup";
	std_msgs.String rosStr = publisher.newMessage();
	ComponentExample.main(null);
        sequenceNumber = 0;
      }
      @Override
      public void loop() throws InterruptedException {
	forward( connectedNode );
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
     public void setMessage(String str) {
	incMessage = str;
     }	
    public static void test(){
	System.out.println("TEST SUCCEEDED");
}
    public String getMessage(){
	return incMessage;
}}
