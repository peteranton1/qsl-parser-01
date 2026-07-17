Feature: QSL Java Var 01 Example

  # ---------------------------------------------------
  Scenario: Temperature QSL Java Var 01 Example
    Given I parse the following content:
    """
    var temp_c = 17;
    var temp_f { comp 64 ; }
    var display1 {
      qt "Input Temp C: " + temp_c +
          ", Output temp F: " + convert_f(temp_c) ;
    }
    """
    When I submit the content to the parser
    And I submit the parse to the java writer
    Then I should see transpiles to java:
    """

     ['[EOF:\[EOF\]]'=[[[[IDENT:temp_c], =[[NUMBER:17]]], [[IDENT:temp_f], =[[[COMP:comp], =[[NUMBER:64]]]]], [[IDENT:display1], =[[[QT:qt], =[['[SUM_PLUS:+]'=[[STRING:"Input Temp C: "], ['[SUM_PLUS:+]'=[['[IDENT:temp_c]], ['[SUM_PLUS:+]'=[[STRING:", Output temp F: "], ['[IDENT:convert_f]'=[['[IDENT:temp_c]]]]]]]]]]]]]]]]
    """

  # ---------------------------------------------------
