package controller.operationsportfolio;

/**
 * The common interface to enable command design pattern.
 */
public interface Operations {

  /**
   * The common method signature to call all operations.
   *
   * @param fileName name of portfolio.
   */
  void executeOperation(String fileName);
}
