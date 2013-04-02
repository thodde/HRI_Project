package edu.wpi.lfd;

public class RosD {
//     public void forward( ConnectedNode rosNode) {
//	final org.ros.rosjava_tutorial_pubsub.Talker.Publisher<std_msgs.String> publisher2 = 
//			org.ros.rosjava_tutorial_pubsub.Talker.newPublisher("chatter", std_msgs.String._TYPE);
//	org.ros.rosjava_tutorial_pubsub.Talker.std_msgs.String msg = publisher2.newMessage();
//	msg.setData(incMessage);
//	publisher2.publish(msg);
//   }
     public static void setMessage(String str) {
	org.ros.rosjava_tutorial_pubsub.Talker.setMessage(str);
     }	
    public static String getMessage(){
	return org.ros.rosjava_tutorial_pubsub.Talker.getMessage();
    }
}
  
