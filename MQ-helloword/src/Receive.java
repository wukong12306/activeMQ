import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by songyongchao on 2017/5/10.
 */
public class Receive {
    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://192.168.78.101:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue name1 = session.createQueue("name1"); //这个和上面的必须一样
        MessageConsumer consumer = session.createConsumer(name1);

        while (true){
            TextMessage receive = (TextMessage) consumer.receiveNoWait();
            if(receive == null){
                break;
            }
            System.out.println(receive.getText());
        }
        System.out.println("结束。。。。");

        if(connection != null){
            connection.close();
        }


    }

}
