import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	private Set<String> pagesVisited;
	private Queue<String> pagesToVisit;
    
    public Crawler() {

    	this.pagesVisited = new HashSet<String>();
    	this.pagesToVisit = new LinkedList<String>();
	}
    
    public void crawlAndGetImages(String startUrl, int maxPagesToVisit, String saveFolderPath) {
    	
    	this.pagesToVisit.offer(startUrl);
    	
    	while (this.pagesVisited.size() < maxPagesToVisit) {
			
			String currentUrl = this.pagesToVisit.poll();
			this.pagesVisited.add(currentUrl);
			
			Document currentPageHtml = this.downloadHTML(currentUrl);
			
			this.getImagesFromCurrentPage(currentPageHtml, saveFolderPath);
			
			Elements linksOnPage = currentPageHtml.select("a[href]");
			for (Element link : linksOnPage) {
				
				if (!this.pagesVisited.contains(link.absUrl("href"))) {
					
					this.pagesToVisit.offer(link.absUrl("href"));
				}
				
			}
		}
    }
    
    private Document downloadHTML(String url) {
		
    	Document document = null;
    	try {
    		
    		Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
    		document = connection.get();
    	} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
    		
    	    e.printStackTrace();
    	}
    	
    	return document;
	}
    
    private void getImagesFromCurrentPage(Document currentPageHtml, String saveFolderPath) {
		
    	Elements images = currentPageHtml.getElementsByTag("img");
    	for (Element element : images) {
			
    		try {
				
    			this.saveImage(element.absUrl("src"), saveFolderPath);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}
	}

	private void saveImage(String imgUrl, String folderPath) throws MalformedURLException {
		
		String imageName = this.getImageName(imgUrl);
		
		URL url = new URL(imgUrl);
		try (BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(folderPath + "/" + imageName))) {

			int bytesRead;
            while ((bytesRead = inputStream.read()) != -1) {
            	
            	outputStream.write(bytesRead);
            }	
        }
        catch (IOException ioe) {
            
        	System.out.println(ioe.toString());
        }
	}
	
	private String getImageName(String imgUrl) {
		
		int lastIndexOfSlash = imgUrl.lastIndexOf("/");
        
        return imgUrl.substring(lastIndexOfSlash + 1);
    }
	
}
