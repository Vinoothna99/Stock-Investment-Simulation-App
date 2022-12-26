package controller.guioperationportfolio;

import modal.InvestmentStrategyImpl;

/**
 * Interface represents the GUI operation for the portfolio.
 */
public interface GuiOperations {

  String executeOperation(String fileName, InvestmentStrategyImpl model);
}
