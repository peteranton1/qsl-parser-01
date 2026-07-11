Feature: Parse Various Compute expressions

  # ---------------------------------------------------
  Scenario: Parse comp (5)
  Given I parse the following content:
  """
      var q1 {
        comp (5)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([COMP:comp])
        Ter([NUMBER:5]).
    """

  # ---------------------------------------------------
  Scenario: Parse comp (6 + 7)
  Given I parse the following content:
  """
      var q1 {
        comp (6 + 7)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([COMP:comp])
        InF([SUM_PLUS:+])
         Ter([NUMBER:6]).
         Ter([NUMBER:7]).
    """

  # ---------------------------------------------------
  Scenario: Parse comp (7 * 3)
  Given I parse the following content:
  """
      var q1 {
        comp (7 * 3)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([COMP:comp])
        InF([SUM_MULT:*])
         Ter([NUMBER:7]).
         Ter([NUMBER:3]).
    """

  # ---------------------------------------------------
  Scenario: Parse comp (6 + 7 + 8 * 9)
  Given I parse the following content:
  """
      var q1 {
        comp (6 + 7 + 8 * 9)
      }
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Agn([IDENT:q1])
       Agn([COMP:comp])
        InF([SUM_PLUS:+])
         Ter([NUMBER:6]).
         InF([SUM_PLUS:+])
          Ter([NUMBER:7]).
          InF([SUM_MULT:*])
           Ter([NUMBER:8]).
           Ter([NUMBER:9]).
    """
