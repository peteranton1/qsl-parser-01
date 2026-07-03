Feature: Parse Various inputs

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
     [EOF:\[EOF\]]
      [IDENT:q1]
       [QT:qt]
        [STRING:"Some Text"]
       [CHAR:char]
        [NUMBER:50]

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
     [EOF:\[EOF\]]
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:5]
        [MULT:*]
         [NUMBER:2]
         [PLUS:+]
          [NUMBER:7]
          [DIV:/]
           [NUMBER:1]
           [MINUS:-]
           [NUMBER:3]

    """

  Scenario: Parse simple exec input
  Given I parse the following content:
  """
      exec(q1, q2, q3)
      exec(qMargin1)
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """
     [EOF:\[EOF\]]
      [EXEC:exec]
       [IDENT:exec0001]
       [IDENT:q1]
       [IDENT:q2]
       [IDENT:q3]
      [EXEC:exec]
       [IDENT:exec0002]
       [IDENT:qMargin1]

    """


