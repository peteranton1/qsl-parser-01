Feature: Parse Various Compute expressions

  # TEST 01 : (5) ---------------------------------------------------
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

  # TEST 02 : (6 + 7) ---------------------------------------------------
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

  # TEST 03 : (7 * 3) ---------------------------------------------------
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

  # TEST 04 : (6 + 7 + 8) ---------------------------------------------------
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

  # TEST 05 : (7 * 3 * 2) ---------------------------------------------------
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

  # TEST 04 : (6 + 7 * 8) ---------------------------------------------------
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

  # TEST 05 : (7 * 3 + 2) ---------------------------------------------------
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
