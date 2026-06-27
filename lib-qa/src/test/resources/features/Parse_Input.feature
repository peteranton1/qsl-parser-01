Feature: Parse Various inputs

  Scenario: Parse simple input
  Given I parse the following content:
  """
      Hello,
      This is the first line of my comment.
      Here is the second line of my comment.
      Thank you!
      """
  When I submit the content to the parser
  Then I should see compiles to the following:
    """
      Some output
    """


