# Simple Trading

##### Assumptions
  - The assumed input formats are slightly different from the specified input formats.Inputs will be of the below format,

        C 2201.00
        AAPL 200.00
        C 2202.00
        PYPL 999.00
  - The output reprsentation is as follows,

        AAPL BUY 2204.00 1000
  - The decision and storage takes into consideration of a rolling splice size of 4. Everything older are discarded. A minimum of at least 4 inputs are needed for making a decision.
  - The solution considers only `BUY` decisions
  - The quantity to be brought is always constant of 1000, in otherwords it is hard-coded.
  - `Feeder` is the entry point of the code