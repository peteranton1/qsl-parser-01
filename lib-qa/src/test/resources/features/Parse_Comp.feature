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
     [EOF:\[EOF\]]
      VAR [IDENT:q1]
          [IDENT:q1]
        COMP [COMP:comp]
              [NUMBER:5]



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
     [EOF:\[EOF\]]
      VAR [IDENT:q1]
          [IDENT:q1]
        COMP [COMP:comp]
              [NUMBER:6]
         [SUM_PLUS:+]
         [NUMBER:7]



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
     [EOF:\[EOF\]]
      VAR [IDENT:q1]
          [IDENT:q1]
        COMP [COMP:comp]
              [NUMBER:7]
         [SUM_MULT:*]
         [NUMBER:3]



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
     [EOF:\[EOF\]]
      VAR [IDENT:q1]
          [IDENT:q1]
        COMP [COMP:comp]
              [NUMBER:6]
         [SUM_PLUS:+]
          [NUMBER:7]
          [SUM_PLUS:+]
           [NUMBER:8]
           [SUM_MULT:*]
           [NUMBER:9]



    """
