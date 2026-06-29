QSL Definition
================

### QSL Syntax

A QSL script is a series of statements. 

Each statement starts with a keyword and then clauses. 

### Reserved Keywords

| keyword | Description                               |
|---------|-------------------------------------------|
| ans     | starts an answer clause                   |
| char    | defines a character response              |
| comp    | defines a computed arithmetic value       |
| exec    | defines a list of vars to execute         |
| qt      | defines a question text will be displayed |
| var     | defines a variable                        |

### eBNF definition

```text

    stmts       :   stmt*
                ;

    stmt        :   varStmt
                |   execStmt
                ;
            
    varStmt     :   'var' '{' varClause+ '}'
                ;
                
    execStmt    :   'exec' '(' IDENT (',' IDENT)* ')'
                ;
    
    varClause   :   'qt' STRING
                |   'ans' ansExpr
                |   'comp' arithExpr
                ;
    
    ansExpr     :   'char' '(' NUMBER (',' NUMBER)* ')'
                ;
                
    arithExpr   :   '(' termExpr ')'
                ;
                
    termExpr    :   '(' termExpr ')'
                |   termExpr (('+' | '-' ) termExpr)*
                |   factorExpr
                ;
                
    factorExpr  :   '(' factorExpr ')'
                |   factorExpr (('*' | '/' ) factorExpr)*
                |   NUMBER
                |   IDENT
                ;

```

### Example QSL script

```text

    var salary {
        qt "Please enter your annual salary"
        ans char(12)
    }
    
    var taxCalc {
        comp (salary * 0.40) 
    }
    
    exec (salary, taxCalc)

```



