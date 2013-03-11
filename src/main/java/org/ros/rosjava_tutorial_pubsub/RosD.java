package org.ros.rosjava_tutorial_pubsub;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

public class RosD {
  public String incMessage;
  
  public String getMessage() {
  return incMessage;
  }
  public void setMessage(String str) {
  incMessage = str;
  }
  public void send(){
  org.ros.rosjava_tutorial_pubsub.Talker.test();
  }  
}
