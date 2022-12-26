package controller;

/**
 * Represents Flexible Portfolios.
 */
public interface FlexiblePortfolioController {

  /**
   * Common Method to call multiple operations using Command Design Pattern.
   *
   * @param menuOption selected operation
   * @param fileName   portfolio name
   */
  void executeFlexibleMenuOptions(int menuOption, String fileName);
}
