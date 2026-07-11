Feature: Parse Example inputs

  Scenario: Parse Example entity input
    Given I parse the following content:
    """
      var salary {
        qt "Please enter your annual salary";
        ans char(12);
      }

      var taxCalc {
        comp salary * 0.40 / 0.01 + 125.34 - 17;
      }

      script main {
        exec salary, taxCalc;
      }
    """
    When I submit the content to the parser
    Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:salary])
       Agn([QT:qt])
        Ter([STRING:"Please enter your annual salary"]).
       Agn([ANS:ans])
        Id([CHAR:char])
         Ter([NUMBER:12]).
      Agn([IDENT:taxCalc])
       Agn([COMP:comp])
        InF([SUM_PLUS:+])
         InF([SUM_MULT:*])
          Id([IDENT:salary])
          InF([SUM_DIV:/])
           Ter([NUMBER:0.40]).
           Ter([NUMBER:0.01]).
         InF([SUM_MINUS:-])
          Ter([NUMBER:125.34]).
          Ter([NUMBER:17]).
      Scr([IDENT:main])
       Exe([EXEC:exec])
        Exe([EXEC:exec])
         Id([IDENT:salary])
         Id([IDENT:taxCalc])
    """

