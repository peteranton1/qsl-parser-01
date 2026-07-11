Feature: Script Definition Example

  # ---------------------------------------------------
  Scenario: Script Lexer Example
    Given I parse the following content:
    """
    script a1 {
        exec q1, q2, q3;
        exec q4, q5;
        exec q6;
    }
    script a2 {
        exec something_very_long;
    }
    script main {
        exec display1;
    }
    """
    When I submit the content to the lexer
    Then I should see compiles to the following:
    """
     [SCRIPT:script] [IDENT:a1] [LBRACE:{]
     [EXEC:exec] [IDENT:q1] [COMMA:,]
     [IDENT:q2] [COMMA:,] [IDENT:q3]
     [SEMICOLON:;] [EXEC:exec] [IDENT:q4]
     [COMMA:,] [IDENT:q5] [SEMICOLON:;]
     [EXEC:exec] [IDENT:q6] [SEMICOLON:;]
     [RBRACE:}] [SCRIPT:script] [IDENT:a2]
     [LBRACE:{] [EXEC:exec] [IDENT:something_very_long]
     [SEMICOLON:;] [RBRACE:}] [SCRIPT:script]
     [IDENT:main] [LBRACE:{] [EXEC:exec]
     [IDENT:display1] [SEMICOLON:;] [RBRACE:}]
     [EOF:\[EOF\]]
    """

  # ---------------------------------------------------
  Scenario: Script Parser Example
    Given I parse the following content:
    """
    script a1 {
        exec q1, q2(1), q3("2") ;
        exec q4, q5;
        exec q6;
    }
    script a2 {
        exec something_very_long;
    }
    script main {
        exec display1;
    }
    """
    When I submit the content to the parser
    Then I should see compiles to the following:
    """

     Mul([EOF:\[EOF\]])
      Scr([IDENT:a1])
       Exe([EXEC:exec])
        Exe([EXEC:exec])
         Id([IDENT:q1])
         Id([IDENT:q2])
          Ter([NUMBER:1]).
         Id([IDENT:q3])
          Ter([STRING:"2"]).
        Exe([EXEC:exec])
         Id([IDENT:q4])
         Id([IDENT:q5])
        Exe([EXEC:exec])
         Id([IDENT:q6])
      Scr([IDENT:a2])
       Exe([EXEC:exec])
        Exe([EXEC:exec])
         Id([IDENT:something_very_long])
      Scr([IDENT:main])
       Exe([EXEC:exec])
        Exe([EXEC:exec])
         Id([IDENT:display1])
    """
