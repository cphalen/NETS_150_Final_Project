import java.util.List;

public class User {
	final public long id;
	final public String screenName;
	final public int followerCount;
	final public int friendsCount;
	final public List<Long> friends;
	float pageRank;

	public User(long id, String screenName, int followerCount, int friendsCount, List<Long> friends) {
		this.id = id;
		this.screenName = screenName;
		this.followerCount = followerCount;
		this.friendsCount = friendsCount;
		this.friends = friends;
	}
	
	public float getPageRank() {
		return pageRank;
	}
	
	public void setPageRank(float pageRank) {
		this.pageRank = pageRank;
	}
}
