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
      VAR [IDENT:q1]
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
      VAR [IDENT:q1]
          [IDENT:q1]
        COMP [COMP:comp]
               [NUMBER:5]
          [SUM_MULT:*]
          [NUMBER:2]
         [SUM_PLUS:+]
           [NUMBER:7]
           [SUM_DIV:/]
           [NUMBER:1]
          [SUM_MINUS:-]
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


