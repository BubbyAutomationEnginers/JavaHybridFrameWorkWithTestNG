package GenericUtilies.BrowserFactory;

public class BrowserFactory {

    public static Browser setUpBrowser(String browser){

       return switch (browser.toLowerCase()){
            case "chrome" -> new ChromeBrowser();
            case "edge" -> new EdgeBrowser();
            case "firefox" -> new FireFoxBrowser();
            case "safari" -> new SafariBrowser();

            default -> throw  new IllegalStateException("Unexpeted error"+browser.toLowerCase());
        };
    }
}
