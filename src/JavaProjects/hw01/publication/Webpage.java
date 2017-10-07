package JavaProjects.hw01.publication;

/**
 * Represents bibliographic information for webpages.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a Webpage in terms of its title, url, and date retrieved.
   *
   * @param title the title of the webpage
   * @param url the url of the webpage
   * @param retrieved the date the article was retrieved
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  /**
   * Formats a citation in APA style for a webpage.
   *
   * @return the formatted citation
   */
  @Override
  public String citeApa() {
    return title + ". Retrieved " + retrieved + ", from " + url + ".";
  }

  /**
   * Formats a citation in MLA style for a webpage.
   *
   * @return the formatted citation
   */
  @Override
  public String citeMla() {
    return "\"" + title + ".\" " + "Web. " + retrieved + " <" + url + ">.";
  }
}
