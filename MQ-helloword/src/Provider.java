import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by songyongchao on 2017/5/10.
 */
public class Provider {


    public static void main(String[] args) throws JMSException {

        /*
        1,建立链接factory类
        2，建立连接
        3，创建session会话(参数1，是否事物，参数2，签收模式-一般为默认签收)
        4，创建目的地(其实就是一个name，queue队列的一个名字，唯一)
        5，创建发送和接收对象
        6，持久化策略(MessageProducer 设置 参数为 int 暂时不知道什么意思)
        7，使用jms规范 建立发送的数据
         */

        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://192.168.78.101:61616");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue name1 = session.createQueue("name1");
        MessageProducer producer = session.createProducer(name1);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage("active 消息： " + i);
            producer.send(textMessage);
        }

        if(connection != null){
            connection.close();
        }
    }



}
