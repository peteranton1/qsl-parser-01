Feature: Parse Various inputs

  Scenario: Parse simple input
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


