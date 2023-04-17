package Server;

import javax.jms.*;
import java.util.HashSet;
import java.util.Iterator;

public class RMessageListener extends Server implements MessageListener {
    private Topic topic1;
    private TopicSession session;
    @Override
    public void onMessage(Message message) {
        System.out.println("Test2");
        TopicPublisher publisher;
        TextMessage objM = (TextMessage) message;

        try {

            String textMessage = new String();
            textMessage = objM.getText();

            if (textMessage.contains("Start")) {
                HashSet<String> newPaperNames = getNewspapaerNames();
                String text = new String();
                Iterator<String> iterator = newPaperNames.iterator();

                while (iterator.hasNext()) {
                    text = text + "\n" + iterator.next();
                }

                publisher = this.session.createPublisher(this.topic1);
                TextMessage textMessage1 = this.session.createTextMessage();
                textMessage1.setText(text);
                publisher.send(textMessage1);
                System.out.println("New Client connected\nNewspaper names sent to client" + textMessage1.getText());
                publisher.close();
            }

            if (textMessage.equals("News1")) {

                System.out.println("Client want Newspapaer 1 to edit");
                publisher = this.session.createPublisher(this.topic1);
                TextMessage textMessage1 = this.session.createTextMessage();
                textMessage1.setText("Newspaper1 selected");
                System.out.println(textMessage1.getText());

                publisher.send(textMessage1);
                publisher.close();
            }

            if (textMessage.contains("News2")) {
                System.out.println("\nClient want Newspapaer 2 to edit");
                publisher = this.session.createPublisher(this.topic1);
                TextMessage textMessage1 = this.session.createTextMessage();
                textMessage1.setText("Newspaper 2 selected");
                publisher.send(textMessage1);
                publisher.close();
            }
            if(textMessage.contains("1")) {
                int articleNumber = 0;
                String textReceive = textMessage;

                String[] split = textReceive.split(",");
                String name = split[1];

                //Add article to Newspapaer with name and paramenter
                addArticleToNews(name, textMessage);

                //Get the sise of List articles of Newspapaer
                HashSet<Newspaper> newspapers = getNewspapaerSet();
                Iterator<Newspaper> newspaperIterator = newspapers.iterator();

                for (Newspaper newspaper : getNewspapaerSet()) {
                    if (newspaper.getName().contains(name)) {
                        articleNumber = newspaper.getArticles().size();
                    }
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void setTopic1(Topic topic1){
        this.topic1 = topic1;
    }

    public void setSession(TopicSession session){
        this.session = session;
    }


}


