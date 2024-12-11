package observer;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Observer {
    public HashSet<String> publishers = new HashSet<>();

    public void gotNews(String news, LocalDateTime time, String publisher) {
        if (publishers.contains(publisher)) {
            System.out.println("Time: "+ time
                    + "\n" + "Publisher: " + publisher
                    + "\n" + news);
        }
    }

    public void addPublisher(String publisher) {
        publishers.add(publisher);
    }
}
