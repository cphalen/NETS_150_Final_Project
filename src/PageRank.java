
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PageRank {
	
	static Map<Long, User> userMap;
	
	
	public static void main(String[] args) {
		loadUserMap();
		runPageRank(20);
	}
	
	public static void runPageRank(int iterations) {
	    //initialize all users to page rank of 1/map.size
	    for (Entry<Long, User> user : userMap.entrySet()) {
	        user.getValue().setPageRank((float) 1/userMap.size());
	    }
	    
	    for (int i = 1; i <= iterations; i++) {
	        System.out.println("Iteration Step " + i + " for " + userMap.entrySet().size() + " users");
	        //iterate over all users in the HashMap
	        for (Entry<Long, User> user : userMap.entrySet()) {
	            if(user.getValue().pageRank == 0) {
	                user.getValue().setPageRank((float) 1/userMap.size());
	            }
	            //assign rank to all of the people user is following
	            for (Long id : user.getValue().friends) {
	                User follower = userMap.get(id);
	                if (follower != null) {
	                    follower.pageRank += (float) user.getValue().getPageRank() * ((float) 1/user.getValue().followerCount);
	                }
	                
	            }
	        }
	    }
	}
	
	public static void loadUserMap() {
		long startTime = System.currentTimeMillis();
		
		Iterator<User> userIterator = new LoadIterator();
		userMap = new HashMap<>();
		while(userIterator.hasNext()) {
		   
			User user = userIterator.next();
			if (user != null) {
			    userMap.put(user.id, user);
            } 
			
		}
		HashSet<Long> nullFriends = new HashSet<Long>();
		int usersRemoved = 0;
		for (Entry<Long, User> userEntry : userMap.entrySet()) {
		    Set<Long> nulls = new HashSet<Long>();
		    int count = 0;
		    User user = userEntry.getValue();
		    if (user.friends != null) {
		        for(Long friendID : user.friends) {
	                User friendInMap = userMap.get(friendID);
	                if (friendInMap == null) {
	                    nulls.add(friendID);
	                    nullFriends.add(friendID);
	                    usersRemoved++;
	                }
	                count++;
	            }
    		    for (Long id : nulls) {
    		        user.friends.remove(id);
    		    }
		    }
		}
		
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println("Friends removed: " + nullFriends.size());
		System.out.println("Load time in seconds: " + timeElapsed / 1000);
		System.out.println("Size of graph: " + userMap.size());
	}

}
