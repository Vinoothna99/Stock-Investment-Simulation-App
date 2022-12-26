


Design Changes for ASSIGNMENT - 6 :

1. A new Graphical view Interface is created, which is implemented by GraphicalView class. And
view frames are created to ensure proper user-interactivity.

2. InvestmentStrategy interface is created to support the new investment and dollar-cost averaging
operations on flexible portfolio. InvestmentStrategyImpl implements InvestmentStrategy and
extends the perevious modal, so that it can support all the required operations on flexible portfolios.

3. A new Features interface is created which is implemented by the GraphicalControllerImpl.
On event trigger, the GraphicalView call the operations of the features interface, which recieve the input
from the use and perform the necessary operation.





Design Changes for ASSIGNMENT - 5 :

1. A new APIStockdata class implements SetUpStockData and extends SetupStockDataImpl. The new implementation
enables to transition of the application from dummy data to fetching data from AlphaVantage API,
without modifying existing class.

2. A new FlexiblePortfolioView interface is created. FlexiblePortfolioViewImpl class implements the
FlexiblePortfolioView interface and extends StockViewImpl. FlexiblePortfolioViewImpl class
facilitates the current application with additional VIEW methods without modifying existing VIEW class.

3. New FlexiblePortfolioModal Interface is created. Abstract class FlexiblePortfolioModalImpl
implements the interface. Class FlexibleStock extends the abstract class. FlexibleStock class uses
delegation to call methods of existing Modal using its object.

4. A new FlexiblePortfolioControllerInterface is designed. It has only one method which is implemented in
 the Adapter class using Command Design Patten. The Adapter class implements both old and new
 Controller Interfaces. So the Adapter class has functionalities of both Flexible and Inflexible
 portfolios.





