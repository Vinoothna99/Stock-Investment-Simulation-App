package view;

import controller.Features;

/**
 * Interface represents the graphical view.
 */
public interface GraphicalInterface {

  /**
   * Method represents all the action listeners in the graphical view.
   *
   * @param f features interface object to call the controller methods
   */
  void addFeatures(Features f);
}
