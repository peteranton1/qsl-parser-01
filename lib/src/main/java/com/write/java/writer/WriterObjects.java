package com.write.java.writer;

import lombok.Getter;

@Getter
public class WriterObjects {

    private final Indenter indenter;
    private final ProgWriter prog;
    private final TerminalWriter terminal;
    private final ScriptWriter script;
    private final MultiWriter multi;
    private final InfixWriter infix;
    private final IdentWriter ident;
    private final ExecWriter exec;
    private final ComputeWriter compute;
    private final BlockWriter block;
    private final AssignWriter assign;

    public WriterObjects(ProgWriter prog) {
        this.indenter = new Indenter();
        this.prog = prog;
        this.terminal = new TerminalWriter(this);
        this.script = new ScriptWriter(this);
        this.multi = new MultiWriter(this);
        this.infix = new InfixWriter(this);
        this.ident = new IdentWriter(this);
        this.exec = new ExecWriter(this);
        this.compute = new ComputeWriter(this);
        this.block = new BlockWriter(this);
        this.assign = new AssignWriter(this);
    }
}
