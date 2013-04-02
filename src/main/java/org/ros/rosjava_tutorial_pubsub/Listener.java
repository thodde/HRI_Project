package org.ros.rosjava_tutorial_pubsub;

import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Subscriber;
import edu.wpi.disco.Disco;

public class Listener extends AbstractNodeMain {
  private static boolean status;
  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava_tutorial_pubsub/listener");
  }

  @Override
  public void onStart(ConnectedNode connectedNode) {
    final Log log = connectedNode.getLog();
    Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber("chatter", std_msgs.String._TYPE);
    subscriber.addMessageListener(new MessageListener<std_msgs.String>() {
 @Override
      public void onNewMessage(std_msgs.String message) {
        log.info("Task: \"" + message.getData() + "\"");
		setStatus(message);
  }
    });
}

  private void setStatus(std_msgs.String message) {
    if(message.getData() == message.getData())
    {
      status = true;
    }
    else { status = false; }
  }
  public static boolean getStatus() {
 	try { 
		Thread.sleep(12000);
		}catch (InterruptedException e) { }  
    return status;
  }
}
