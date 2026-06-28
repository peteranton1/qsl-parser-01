Feature: Parse Various inputs

  Scenario: Parse simple input
  Given I parse the following content:
  """
      Hello,
      This exec VAR %
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """
    [IDENT:Hello] [COMMA:,] [IDENT:This] [EXEC:exec] [VAR:VAR] [UNKNOWN:%] [EOF:\[EOF\]]
    """


