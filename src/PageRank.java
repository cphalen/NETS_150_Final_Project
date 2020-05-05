import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PageRank {
	
	static Map<Long, User> userMap;
	
	public static void main(String[] args) {
		loadUserMap();
		runPageRank();
	}
	
	public static void runPageRank() {
		// TODO: Run page rank on the users in userMap
		throw new UnsupportedOperationException();
	}
	
	public static void loadUserMap() {
		long startTime = System.currentTimeMillis();
		
		Iterator<User> userIterator = new LoadIterator();
		userMap = new HashMap<>();
		
		while(userIterator.hasNext()) {
			User user = userIterator.next();
			userMap.put(user.id, user);
		}
		
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println("Load time in seconds: " + timeElapsed / 1000);
		System.out.println("Size of graph: " + userMap.size());
	}
}
