Feature: Parse Various Compute expressions

  # (5) ---------------------------------------------------
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
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:5]

    """

  # (6 + 7) ---------------------------------------------------
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
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:6]
        [PLUS:+]
        [NUMBER:7]

    """

  # (7 * 3) ---------------------------------------------------
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
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:7]
        [MULT:*]
        [NUMBER:3]

    """
