/**
 * Created by omarbaradei on 9/24/16.
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;


public class SearchTweets {

        private static Twitter twitter;

    // testing push

        private static void setupTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("xXPUUA4SgcthsS3m6spTqpQfG")
                .setOAuthConsumerSecret("c69xwIVBEzwqRg7U2C79P7RuQj3RKsHdrFbyBCyUq1roZ6EJ4D")
                .setOAuthAccessToken("708878004806619136-6omlIOjQwbZA0FFz2nPj1NlnLheonzr")
                .setOAuthAccessTokenSecret("j45RdKgy7cWpKD6KKxcxwKQnpigj2Y93y4jLiu8h5st2a");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    /**
     * Temp
     *
     * @param args search query
     */
    public static void main(String[] args) {
        setupTwitter();
        if (args.length < 1) {
            System.out.println("No params inserted.");
            System.exit(-1);
        }
        try {
            Query query = new Query(args[0]);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}

//public class TwitterClass {
//    private Twitter twitter;
//    private TwitterStream twitterStream;
//    private static int current = 0;



//    public TwitterClass() {
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("xXPUUA4SgcthsS3m6spTqpQfG")
//                .setOAuthConsumerSecret("\tc69xwIVBEzwqRg7U2C79P7RuQj3RKsHdrFbyBCyUq1roZ6EJ4D")
//                .setOAuthAccessToken("\t708878004806619136-6omlIOjQwbZA0FFz2nPj1NlnLheonzr")
//                .setOAuthAccessTokenSecret("\tj45RdKgy7cWpKD6KKxcxwKQnpigj2Y93y4jLiu8h5st2a");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        twitter = tf.getInstance();
//        try {
//            setupTwitterStream();
//        } catch (TwitterException e) {
//            System.out.println("Error! Twitter failed us somehow, sorry!");
//            e.printStackTrace();
//        }
//    }
//
//    private void setupTwitterStream() throws TwitterException {
//        Query query = new Query();
//        QueryResult result = twitter.search(query);
//        List<Status> tweets = result.getTweets();
//        twitterStream = new TwitterStreamFactory().getInstance();
//        query.setGeoCode(tweets.get(current).getGeoLocation(), 20 ,Query.MILES);
//        FilterQuery fq = new FilterQuery(query.toString());
//        StatusListener listener = new StatusListener() {
//            @Override
//            public void onStatus(Status status) {
//                String content = status.getText();
//                GeoLocation geolocation = status.getGeoLocation();
//                double [] geoCoordinates =  { geolocation.getLatitude(), geolocation.getLongitude()};
//                if (query.getGeocode().equals(geolocation)) {
//                    fq.locations(geoCoordinates);
//                    current++;
//                }
//                System.out.println(content);
//            }
//
//            @Override
//            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//
//            }
//
//            @Override
//            public void onTrackLimitationNotice(int i) {
//
//            }
//
//            @Override
//            public void onScrubGeo(long l, long l1) {
//
//            }
//
//            @Override
//            public void onStallWarning(StallWarning stallWarning) {
//
//            }
//
//            @Override
//            public void onException(Exception e) {
//                e.printStackTrace();
//
//            }
//        };
//        twitterStream.addListener(listener);
//        twitterStream.filter(fq);
//    }
//
//    public static void main(String[] args) throws TwitterException {
//    }
//}
