package setup.classesabstract;

import setup.interfaces.SetUpStockData;

/**
 * Abstract class SetUpStockDataImpl implements SetUpStockData.
 */

public abstract class SetUpStockDataImpl implements SetUpStockData {

  String absolutePath = System.getProperty("user.dir");
  String separator = System.getProperty("file.separator");
  private final String fileName = absolutePath + separator + "data" + separator
      + "listed_companies.csv";


  @Override
  public void updatePricesToday() {
    boolean upToDate = readCsvFile(fileName);
    if (!upToDate) {
      writeToCsvFile(fileName);
    }

  }

  protected abstract boolean readCsvFile(String fileName);

  protected abstract void writeToCsvFile(String fileName);

  protected abstract String getTodayDate();

  @Override
  public void getEnlistedCompanies() {
    String filePath = absolutePath + separator + "data" + separator + "listed_companies-copy.csv";
    writeToCsvFile(filePath);
  }


}
