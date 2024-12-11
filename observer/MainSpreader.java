package observer;

import java.util.HashMap;
import java.util.HashSet;
import java.time.LocalDateTime;

/**
*  TODO: Complete the implementation. Do not change class name and existing method signatures. You may add base class and other interfaces. Make sure that the class works with the default constructor.
* 
*/
public class MainSpreader implements NewsSpreader {
	HashMap<String, String> sourceMap;
	//HashMap<String, HashSet<Observer>> broadcast;
	HashMap<String, Boolean> blockWords;

	HashSet<Observer> obervers;

	// Default constructor must be present, you may add implementation if needed
	public MainSpreader(){
		sourceMap = new HashMap<>();
		//broadcast = new HashMap<>();
		blockWords = new HashMap<>();

		obervers = new HashSet<>();
	}
	
	@Override
	public boolean registerTrustedSource(String source, String pwd){
		if (source == null || pwd == null || pwd.isEmpty() || sourceMap.containsKey(source)) {
			return false;
		}
		else {
			sourceMap.put(source, pwd);
			return true;
		}
	}
	
	@Override
	public String spreadNews(String news, String source, String pwd) throws UntrustedSourceException, AuthenticationException, BlockedContentException {
		if (news == null || source == null || pwd == null) {
			throw new java.lang.IllegalArgumentException("One of arguments is null!");
		}
		else if (!sourceMap.containsKey(source)) {
			throw new UntrustedSourceException("Source that you provided is not registered!");
		}
		else if (sourceMap.get(source) != pwd) {
			throw new AuthenticationException("Wrong password!");
		}
		news = redactText(news);
		if(news.equals("Can not be spread!")) {
			throw new BlockedContentException("Your news has blocked content");
		}
		LocalDateTime timestamp = LocalDateTime.now();
		notify(news, timestamp, source);
		return news;
	}

	@Override
	public boolean blockWord(String contents, boolean redact) {
		if (contents == null) {
			return false;
		}
		else{
			for (int i = 0; i < contents.length(); i++) {
				if (!Character.isAlphabetic(contents.charAt(i))) {
					return false;
				}
			}
			blockWords.put(contents, redact);
			return true;
		}
	}

	@Override
	public boolean unblockWord(String contents) {
		if (contents == null) {
			return false;
		}
		else {
			blockWords.remove(contents);
			return true;
		}
	}

	public String redactText(String text) {
		for (String x : blockWords.keySet()) {
			String regex = "\\b" + x + "\\b";
			if (text.matches(".*\\b" + x + "\\b.*") && blockWords.get(x)) {
				text = text.replaceAll(regex, "#");
			}
			else if (text.matches(".*\\b" + x + "\\b.*") && !blockWords.get(x)){
				return "Can not be spread!";
			}
		}
		return text;
	}

	public void addObserver(Observer observer) {
		obervers.add(observer);
	}

	public void removeObserver(Observer observer) {
		obervers.remove(observer);
	}

	public void notify(String news, LocalDateTime time, String source) {
		for (Observer observer : obervers) {
			observer.gotNews(news, time, source);
		}
	}
}
