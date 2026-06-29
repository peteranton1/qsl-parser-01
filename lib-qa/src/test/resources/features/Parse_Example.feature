Feature: Parse Example inputs

  Scenario: Parse Example entity input
    Given I parse the following content:
    """
      var salary {
        qt "Please enter your annual salary"
        ans char(12)
      }

      var taxCalc {
        comp (salary * 0.40)
      }

      exec (salary, taxCalc)
    """
    When I submit the content to the parser
    Then I should see compiles to the following:
    """
     [EOF:\[EOF\]]
      [IDENT:salary]
       [QT:qt]
        [STRING:"Please enter your annual salary"]
       [CHAR:char]
        [NUMBER:12]
      [IDENT:taxCalc]
       [COMP:comp]
        [IDENT:salary]
        [MULT:*]
        [NUMBER:0.40]
      [EXEC:exec]
       [IDENT:exec0001]
       [IDENT:salary]
       [IDENT:taxCalc]

    """

