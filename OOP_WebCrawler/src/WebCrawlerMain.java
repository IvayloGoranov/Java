import java.util.Scanner;

public class WebCrawlerMain {

	private static final String FOLDER_PATH = "downloaded_images";

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		Crawler crawler = new Crawler();
		System.out.print("Start url: ");
		String startUrl = scanner.nextLine();
		System.out.print("Max pages to visit: ");
		int maxPagesToVisit = Integer.parseInt(scanner.nextLine());
		crawler.crawlAndGetImages(startUrl, maxPagesToVisit, FOLDER_PATH);
	}

}
