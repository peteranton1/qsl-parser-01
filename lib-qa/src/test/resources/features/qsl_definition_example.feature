Feature: QSL Definition Example

  # ---------------------------------------------------
  Scenario: Lex QSL Example
    Given I parse the following content:
  """
    //package my.NumberChooser;

    //import sys.math.random;
    //import sys.char.newline;

    // Comments
    var name {
        qt "Hi there. What shall I call you?" ;
        ans char(50) ;
    }

    var chooseNumber {
        qt "Hi " name newline
            "Please enter a number between 1 and 100" ;
        ans quant(3) ;
    }

    fn luckyCalc() {
        var cn = chooseNumber;
        if(cn > 10) {
            return cn * random(5);
        } else {
            return cn * random(10);
        }
    }

    var display1 {
        qt "Welcome " name newline
            "Your lucky number is " luckyCalc ;
    }

    script {
        exec
            name,
            chooseNumber,
            display1;
    }
    """
    When I submit the content to the lexer
    Then I should see compiles to the following:
    """
     [EOF:\[EOF\]]
      [IDENT:q1]
       [COMP:comp]
        [NUMBER:5]

    """

  # ---------------------------------------------------
  Scenario: Parse comp (5)
    Given I parse the following content:
  """
    //package my.NumberChooser;

    //import sys.math.random;
    //import sys.char.newline;

    // Comments
    var name {
        qt "Hi there. What shall I call you?" ;
        ans char(50) ;
    }

    var chooseNumber {
        qt "Hi " name newline
            "Please enter a number between 1 and 100" ;
        ans quant(3) ;
    }

    fn luckyCalc() {
        var cn = chooseNumber;
        if(cn > 10) {
            return cn * random(5);
        } else {
            return cn * random(10);
        }
    }

    var display1 {
        qt "Welcome " name newline
            "Your lucky number is " luckyCalc ;
    }

    script {
        exec
            name,
            chooseNumber,
            display1;
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
