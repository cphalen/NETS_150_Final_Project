import java.util.HashSet;
import java.util.Map;

public class Runner {
	public static void main(String[] args) {
		PageRank.loadUserMap();
		PageRank.runPageRank();
		Map<Long, User> userMap = PageRank.userMap;
		
		//find the 100 users with highest page rank
		HashSet<Long> highPR = new HashSet<>();
		for (Long pr: userMap.keySet()) {
			if (highPR.size() < 100) {
				highPR.add(pr);
			} else {
				for (Long val: highPR) {
					if (pr > val) {
						highPR.remove(val);
						highPR.add(pr);
						break;
					}
				}
			}
		}
		
		//find the percentage of verified users in those 100 users
		int count = 0;
		for (Long pr: highPR) {
			if (TwitterQuery.isUserVerified(userMap.get(pr))) {
				count ++;
			}
		}
		System.out.println("Percentage of Verified Users in top 100: " + (double)count/100);
	}
}
