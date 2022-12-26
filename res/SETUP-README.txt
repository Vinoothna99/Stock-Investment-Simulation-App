--Swing library is used to produce Graphical User Interface.



Steps to run the Jar file:

1.  Please copy and paste the data folder from res folder to "c:/users/<username>" folder in your PC.
    And place the jar file in the same location as the data folder.
	  For example, in my PC, the folder is "c:/users/vinoothna"

2. Once, the data folder and the jar file is available in above mentioned folder,
 Please open command prompt.

3. In command prompt, type the command java -jar <location of Jar file> and press enter to run the Jar file.

4. Once you run successfully, user will see a menu with 2 operations.
   Choose either a CLI or a GUI for further operations.

5. If you choose option 2, A GUI frame will be displayed with following menu options:

       1. Create Portfolio
       2. Buy Shares on a Specific Date
       3. Sell Shares on a Specific Date
       4. View Composition of a Portfolio
       5. Value of a Current Portfolio
       6. Value of a Portfolio on a Specific Date
       7. Cost Basis of a Portfolio
       8. Get a Copy of Current Portfolio
       9. Invest Fixed Amount of Existing Portfolio
       10. Dollar-Cost Averaging
       11. Quit

Below are the steps of how input and output looks like to be able to successfully perform the above
operations:


To Create Portfolio

    1. Click on Create Portfolio Button.
    2. Enter portfolio name (Only alphabets) in the input box and press OK.
    3. Enter the
        i.)  date in YYYY-MM-DD format
        ii.) tickers in the format AA,IBM,MSFT (Comma separated and no spaces. Enter valid ticker values)
        iii.)Shares in the corresponding order of the tickers entered above. 30,20,50.
        iv.) Commission Fee (integer value greater than zero.)
    4. Click save button.

To Invest in a Portfolio

    1. Click on Invest Fixed Amount of Existing Portfolio Button.
    2. Enter portfolio name (Only alphabets) in the input box and press OK.
    3. Enter the
        i.)   Investment Amount (Positive value)
        ii.)  Commission Fee (integer value greater than zero.)
        iii.) date in YYYY-MM-DD format
        iv.) Percentage of amount to be invested in the corresponding order of the tickets shown in the label
          in the format 30,20,50. The percentages should add up to 100.
    4. Click save button.


To Create a Portfolio and Perform Dollar-Cost Averaging:
    1. Click on Dollar-Cost Averaging Button.
    2. Enter portfolio name (Only alphabets) in the input box and press OK.
    3. Enter the
        i.)   Investment Amount (Positive value)
        ii.)  Commission Fee (integer value greater than zero.)
        iii.) From date in YYYY-MM-DD format
        iv.)  To date in YYYY-MM-DD format
        v.)   Recurring period for investments(integer value greater than zero).
        vi.)  Tickers in the format AA,IBM,MSFT (Comma separated and no spaces. Enter valid ticker values)
        vii.) Percentage of amount to be invested in the corresponding order of the tickets entered above
              in the format 30,20,50. The percentages should add up to 100.
        viii.) Commission Fee (integer value greater than zero.)
    4. Click save button.

