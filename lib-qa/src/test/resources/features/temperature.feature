Feature: QSL Definition Example

  # ---------------------------------------------------
  Scenario: Temperature QSL Lexer Example
    Given I parse the following content:
    """
    var temp_c = 32.34 ;

    fn convert_f(temp) {
      return temp * (9/5) + 32 ;
    }

    var display1 {
      qt "Input Temp C: " + temp_c +
          ", Output temp F: " convert_f(temp_c) ;
    }

    script {
        exec
            display1;
    }
    """
    When I submit the content to the lexer
    Then I should see compiles to the following:
    """
    [VAR:var] [IDENT:temp_c] [ASSIGN:=] [NUMBER:32.34] [SEMICOLON:;] [FN:fn] [IDENT:convert_f] [LPAREN:(] [IDENT:temp] [RPAREN:)] [LBRACE:{] [RETURN:return] [IDENT:temp] [SUM_MULT:*] [LPAREN:(] [NUMBER:9] [SUM_DIV:/] [NUMBER:5] [RPAREN:)] [SUM_PLUS:+] [NUMBER:32] [SEMICOLON:;] [RBRACE:}] [VAR:var] [IDENT:display1] [LBRACE:{] [QT:qt] [STRING:"Input Temp C: "] [SUM_PLUS:+] [IDENT:temp_c] [SUM_PLUS:+] [STRING:", Output temp F: "] [IDENT:convert_f] [LPAREN:(] [IDENT:temp_c] [RPAREN:)] [SEMICOLON:;] [RBRACE:}] [SCRIPT:script] [LBRACE:{] [EXEC:exec] [IDENT:display1] [SEMICOLON:;] [RBRACE:}] [EOF:\[EOF\]]
    """

  # ---------------------------------------------------
  Scenario: Temperature QSL Parser Example
    Given I parse the following content:
    """
    var temp_c1 = 11.11 ;
    var temp_f1 = temp_c2 * (9/5) + 32 ;
    """
    When I submit the content to the parser
    Then I should see compiles to the following:
    """
     [EOF:\[EOF\]]
      VAR [IDENT:temp_c1]
          [NUMBER:11.11]

      VAR [IDENT:temp_f1]
           [IDENT:temp_c2]
        [SUM_MULT:*]
         [NUMBER:9]
         [SUM_DIV:/]
         [NUMBER:5]
       [SUM_PLUS:+]
       [NUMBER:32]


    """
