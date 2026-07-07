QSL Definition
================

### QSL Syntax

A QSL script is a series of statements. 

Each statement starts with a keyword and then clauses. 

### Reserved Keywords

| keyword | Description                              |
|---------|------------------------------------------|
| ans     | starts an answer clause                  |
| char    | type of answer: characters               |
| comp    | computed arithmetic value                |
| else    | start of else stmt                       |
| exec    | list of vars and fns to execute          |
| if      | start of if stmt                         |
| import  | make available parts from other scripts  |
| m       | type of answer: multiple                 |
| package | optional name of this script             |
| qt      | question text to be displayed            |
| quant   | type of answer: numeric                  |
| s       | type of answer: single                   |
| script  | defines the only script                  |
| var     | defines a variable                       |
| while   | start of while loop                      |

### eBNF definition

```text

    prog            :   packageStmt?
                        importStmt*
                        stmts
                    ;

    stmts           :   stmt*
                    ;

    stmt            :   varStmt
                    |   fnStmt
                    |   scriptStmt
                    |   ifStmt
                    |   whileStmt
                    ;

    compoundID      :   IDENT ('.' IDENT)+
                    ;

    packageStmt     :   'package' compoundID SEMICOLON
                    ;

    importStmt      :   'import' compoundID SEMICOLON
                    ;

    // variable --------------------------
    
    varStmt         :   'var' IDENT varBlock 
                    ;

    varBlock        :  '{' varClause+ '}'
                    |  '=' sumExpr SEMICOLON
                    |
                    ;

    varClause       :   varSubClause SEMICOLON
                    ;

    varSubClause    :   'qt' stringExpr
                    |   'ans' ansExpr
                    |   'comp' sumExpr
                    ;

    stringExpr      :   (STRING|IDENT|NUMBER)+
                    ;

    ansExpr         :   charExpr
                    |   singleAnsExpr
                    ;
                
    charExpr        :   ('char'|'number') '(' NUMBER (',' NUMBER)* )* ')' 
                    ;

    singleAnsExpr   :   ('s'|'m') stringExpr 
                    ;

    // function ------------------------
    
    fnStmt          :   'fn' IDENT '(' ')' blockStmt
                    ;

    blockStmt       :   '{' stmts '}'
                    ;
                
    // ifStmt --------------------------
    
    ifComplex       :   ifStmt
                        elseifStmt*
                        elseStmt
                    ;
    
    ifStmt          :   'if' logicalStmt blockStmt
                    ;
    
    elseStmt        :   'else' (blockStmt | ifStmt)
                    ;
    
    // whileStmt -----------------------
    
    whileStmt       :   'while' logicalStmt blockStmt
                    
    // script --------------------------

    scriptStmt      :   'script' blockStmt
                    ;
    
    // arithmetic expression -----------
    
    sumExpr         :   productExpr ('+'|'-' productExpr)*
                    ;
                 
    productExpr     :   factorExpr ('*'|'/' factorExpr)*
                    ;
    
    factorExpr      :   IDENT
                    |   NUMBER
                    |   '(' sumExpr ')'
                    ;

    // logical expression --------------
    
    logicalStmt     :   '(' logicalExpr ')'
    
    logicalExpr     :   sumExpr compareOp sumExpr (addOp logicalExpr)*
                    ;
    
    addOp           :   '&&' | '||'
                    ;
    
    compareOp       :   '==' | '>=' | '<=' | != | '<' | '>' 
                    ;
    

```

### Example QSL script

```text

    package my.NumberChooser;

    import sys.math.random;
    import sys.char.newline;

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

    

```



