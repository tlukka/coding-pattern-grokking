package systemdesign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerImplementation {

    public static void main(String[] args) {
        WebCrawlerImplementation web = new WebCrawlerImplementation();
        web.discoverBFS("https://www.bbc.com");
    }
    Queue<String> queue;
    List<String> discoveredWebsites;

    public WebCrawlerImplementation() {
        this.discoveredWebsites = new ArrayList<>();
        this.queue = new LinkedList<>();
    }

    public void discoverBFS(String root) {
        this.discoveredWebsites.add(root); // adding to list so we don't need to visit agian
        queue.add(root);

        while (!queue.isEmpty()) {
            String currentWeb = queue.remove();
            String rawHtml = readUrl(currentWeb);
            // valid regex for websites
            String regex = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(rawHtml);
            while (matcher.find()) {
                String connectedWebsites = matcher.group();
                if (!discoveredWebsites.contains(connectedWebsites)) {
                    System.out.println(connectedWebsites);
                    discoveredWebsites.add(connectedWebsites);
                    queue.add(connectedWebsites);
                }
            }
        }
    }

    String readUrl(String website) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(website);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Problem while crawling website " + website);
        }

        return sb.toString();
    }
}
