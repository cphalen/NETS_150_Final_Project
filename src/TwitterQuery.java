import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterQuery {
	private static Twitter twitter = TwitterFactory.getSingleton();
	
	
	/*
	 * For this to work make sure you have the "twitter4j-core-4.0.7.jar"
	 * in your build path as an "external library"
	 */
	public static boolean isUserVerified(User user) {
		try {
			return twitter.showUser(user.id).isVerified();
		} catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
