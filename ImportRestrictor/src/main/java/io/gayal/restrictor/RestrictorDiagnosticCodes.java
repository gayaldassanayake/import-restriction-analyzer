package io.gayal.restrictor;

import io.ballerina.tools.diagnostics.DiagnosticSeverity;

public enum RestrictorDiagnosticCodes {
    IMPORT_VIOLATION(
            "IMPORT_VIOLATION",
            "Only Ballerina platform modules are allowed to be imported",
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
