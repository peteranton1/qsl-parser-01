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
      [IDENT:q1]
       [COMP:comp]
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
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:6]
        [PLUS:+]
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
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:7]
        [MULT:*]
        [NUMBER:3]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (6 + 7 + 8)
  Given I parse the following content:
  """
      var q1 {
        comp (6 + 7 + 8)
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
         [PLUS:+]
         [NUMBER:8]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (7 * 3 * 2)
  Given I parse the following content:
  """
      var q1 {
        comp (7 * 3 * 2)
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
         [MULT:*]
         [NUMBER:2]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (6 + 7 * 8)
  Given I parse the following content:
  """
      var q1 {
        comp (6 + 7 * 8)
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
         [MULT:*]
         [NUMBER:8]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (7 * 3 + 2)
  Given I parse the following content:
  """
      var q1 {
        comp (7 * 3 + 2)
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
         [PLUS:+]
         [NUMBER:2]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (6 + (7 * 8))
  Given I parse the following content:
  """
      var q1 {
        comp (6 + (7 * 8))
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
         [MULT:*]
         [NUMBER:8]

    """

  # ---------------------------------------------------
  Scenario: Parse comp ((7 * 3) + (2 / 2))
  Given I parse the following content:
  """
      var q1 {
        comp ((7 * 3) + (2 / 2))
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
        [PLUS:+]
         [NUMBER:2]
         [DIV:/]
         [NUMBER:2]

    """
  # ---------------------------------------------------
  Scenario: Parse comp (7 * (3 + 2) / (2 * 17))
  Given I parse the following content:
  """
      var q1 {
        comp (7 * (3 + 2) / (2 * 17))
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
          [PLUS:+]
          [NUMBER:2]
         [DIV:/]
          [NUMBER:2]
          [MULT:*]
          [NUMBER:17]

    """
