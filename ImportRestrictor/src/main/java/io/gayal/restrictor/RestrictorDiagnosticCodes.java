package io.gayal.restrictor;

import io.ballerina.tools.diagnostics.DiagnosticSeverity;

public enum RestrictorDiagnosticCodes {
    IMPORT_101(
            "IMPORT_101",
            "Only Ballerina standard library modules are allowed to be imported",
            DiagnosticSeverity.ERROR);


    private final String code;
    private final String message;
    private final DiagnosticSeverity severity;

    RestrictorDiagnosticCodes(String code, String message, DiagnosticSeverity severity) {
        this.code = code;
        this.message = message;
        this.severity = severity;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DiagnosticSeverity getSeverity() {
        return severity;
    }
}
