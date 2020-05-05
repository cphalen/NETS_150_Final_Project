import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadIterator implements Iterator<User> {

	String regex;
	Pattern pattern;
	BufferedReader reader;
	Matcher matcher;
	boolean hasNext;
	
	public LoadIterator() {
		regex = "\\\"([^,]*)\\\",\\\"([^,]*)\\\",([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),\\[(.*)\\]";
		pattern = Pattern.compile(regex);
		hasNext = true;
		
		try {
			reader = new BufferedReader(new FileReader("data.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Skip over the first line
		advance();
		advance();
	}
	
	private void advance() {
		try {
			String nextLine = reader.readLine();
			if (nextLine != null && !nextLine.equals("")) {
				matcher = pattern.matcher(nextLine);
			} else {
				hasNext = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public User next() {
		if (matcher.matches()) {
			long id = Long.parseLong(matcher.group(1));
			String screenName = matcher.group(2);
			int followersCount = Integer.parseInt(matcher.group(5));
			int friendsCount = Integer.parseInt(matcher.group(6));
			List<Long> friends = new ArrayList<>();
			
			for (String idString : matcher.group(10).split(",")) {
				if (idString != null && !idString.equals("")) {
					friends.add(Long.parseLong(idString.replaceAll("\\\"", "").trim()));
				}
			}
		
			advance();
			return new User(id, screenName, followersCount, friendsCount, friends);
			
		} else {
			advance();
			return next();
		}
	}

}
