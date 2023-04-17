package Server;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;

public class Server {
    private static Newspaper newspaper1 = new Newspaper("Newspaper1");
    private static Newspaper newspaper2 = new Newspaper("Newspaper2");
    private static HashSet<Newspaper> newspapaerSet = new HashSet<>();

    public static void main(String argv[]) throws Exception {


        //Newspaper1
        newspaper1.addArticle("Art1", "Cat1", 101);
        newspaper1.addArticle("Art2", "Cat2", 102);
        newspaper1.addArticle("Art3", "Cat3", 103);
        newspaper1.addArticle("Art4", "Cat4", 104);
        newspaper1.addArticle("Art5", "Cat5", 105);
        newspaper1.addArticle("Art6", "Cat6", 106);

        //Newspaper2
        newspaper2.addArticle("Art22", "Cat11", 201);
        newspaper2.addArticle("Art23", "Cat22", 202);
        newspaper2.addArticle("Art24", "Cat33", 203);
        newspaper2.addArticle("Art25", "Cat44", 204);
        newspaper2.addArticle("Art26", "Cat55", 205);
        newspaper2.addArticle("Art27", "Cat66", 206);

        newspapaerSet.add(newspaper1);
        newspapaerSet.add(newspaper2);

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        Context context = new InitialContext(properties);
        TopicConnectionFactory connFactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
        TopicConnection conn = connFactory.createTopicConnection();
        conn.start();

        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic1 = (Topic) context.lookup("dynamicTopics/topic1");
        Topic topic2 = (Topic) context.lookup("dynamicTopics/topic2");
        conn.start();

        System.out.println("Test1");
        RMessageListener listener = new RMessageListener();
        listener.setTopic1(topic1);
        listener.setSession(session);

        TopicSubscriber subscriber1 = session.createSubscriber(topic2);
        subscriber1.setMessageListener(listener);


        if(System.in.read()!=0){
            session.close();
            conn.close();
        }

    }
        public HashSet<String> getNewspapaerNames(){
            HashSet<String> names=new HashSet<>();
            HashSet<Newspaper> newsMethod = newspapaerSet;
            Iterator<Newspaper> iterator = newsMethod.iterator();

            while (iterator.hasNext()){
                names.add(iterator.next().getName());
            }
            return names;
        }

    public void addArticleToNews(String newspapaerName, String infoArticle){

        String infos = infoArticle;
        String[] split = infos.split(",");
        String name,category,page;
        name = split[2];
        category = split[3];
        page = split[4];
        System.out.println("\nAdd to newspapaer: "+newspapaerName+"\nArticle name: "+name +" Caterogy: "+category + " Page: "+page);

        for(Newspaper newspaper: newspapaerSet){
            if(newspaper.getName().contains(newspapaerName)){
                newspaper.addArticle(name,category, Integer.parseInt(page));
                System.out.println("Added to: "+newspaper.getName());
            }
        }


    }
    public static HashSet<Newspaper> getNewspapaerSet() {
        return newspapaerSet;
    }



}














