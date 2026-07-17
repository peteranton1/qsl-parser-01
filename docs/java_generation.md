Java code generation
======================


### Java Syntax

A QSL script generates a java output as described below

### Qsl vs Java syntax

#### VAR QSL example

```text
var q1 {
    qt "Some Text" q0 ;
    ans 1 "Yes";
    ans 2 "No";
    and 9 "DK/NA";
}
```

#### VAR Java example 

```java
class q1 extends VarBase {

    QuestionText qt() {
        return new QuestionText("Some Text",q0);  
    } 
    
    List<Answer> answers() {
        return List.of(
            new Answer(1, "Yes"),  
            new Answer(2, "No"),  
            new Answer(9, "DK/NA")  
        );
    }
}
```


#### EXEC QSL example

```text
script main {
    exec q1, q2, q3;
}
```

#### VAR Java example 

```java
class Main extends ScriptBase {

    ExecRunner exec$q1() {
        return new ExecRunner(q1);  
    } 

    ExecRunner exec$q2() {
        return new ExecRunner(q2);  
    } 

    ExecRunner exec$q3() {
        return new ExecRunner(q3);  
    } 
}
```

