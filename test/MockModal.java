import modal.StockModal;
import setup.interfaces.SetUpStockData;

/**
 * MockModal is dummy modal used only for testing purpose only.
 */
public class MockModal implements StockModal {

  private StringBuilder log;
  private final String uniqueCode;

  public MockModal(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public boolean uploadCsv(String fileName, String path, SetUpStockData stockDataObject) {
    log.append("File Name: " + fileName + ", Path: " + path);
    return false;
  }

  @Override
  public boolean enterTickerValues(String fileName, String tickerValues,
      SetUpStockData stockDataObject) {
    log.append("File Name: " + fileName + ", Ticker Values: " + tickerValues);
    return false;
  }

  @Override
  public String compositionOfPortfolio(String fileName, SetUpStockData stockDataObject) {
    log.append("File Name: " + fileName);
    return uniqueCode;
  }

  @Override
  public String valueOfPortfolio(String fileName, SetUpStockData stockDataObject) {
    log.append("File Name: " + fileName);
    return uniqueCode;
  }

  @Override
  public boolean displayEnlistedCompanies(SetUpStockData stockDataObject) {
    return false;
  }

  @Override
  public boolean getPortfolio(String fileName, SetUpStockData stockDataObject) {
    log.append("File Name: " + fileName);
    return false;
  }

  @Override
  public boolean checkFileName(String fileName) {
    log.append("File Name: " + fileName);
    return false;
  }
}
