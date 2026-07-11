Feature: Parse Var Questions

  Scenario: Parse var q1 entity input
  Given I parse the following content:
  """
      var q1 {
        qt "Some Text"
        ans char(50)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([QT:qt])
        Ter([STRING:"Some Text"]).
       Agn([ANS:ans])
        Id([CHAR:char])
         Ter([NUMBER:50]).
    """

  Scenario: Parse var comp entity input
  Given I parse the following content:
  """
      var q1 {
        comp (5 * 2 + 7 / 1 - 3)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([COMP:comp])
        InF([SUM_PLUS:+])
         InF([SUM_MULT:*])
          Ter([NUMBER:5]).
          Ter([NUMBER:2]).
         InF([SUM_MINUS:-])
          InF([SUM_DIV:/])
           Ter([NUMBER:7]).
           Ter([NUMBER:1]).
          Ter([NUMBER:3]).
    """


