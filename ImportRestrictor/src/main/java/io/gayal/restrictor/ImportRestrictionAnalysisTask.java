package io.gayal.restrictor;

import io.ballerina.compiler.syntax.tree.ImportDeclarationNode;
import io.ballerina.compiler.syntax.tree.ImportOrgNameNode;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;
import io.ballerina.tools.diagnostics.DiagnosticFactory;
import io.ballerina.tools.diagnostics.DiagnosticInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Analysis task for import restriction.
 */
public class ImportRestrictionAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {
    private static final Set<String> ALLOWED_ORGS = new HashSet<>(Arrays.asList(
            "ballerina", "ballerinax", "ballerinai"));
    private static final String THIS_PKG_ORG = "gayaldassanayake";
    private static final String THIS_PKG_NAME = "import_restrictor";

    @Override
    public void perform(SyntaxNodeAnalysisContext context) {
        // TODO:
        //  make this a built-in plugin
        //  check for ballerina* instead of ballerina, x, i
        Optional<ImportOrgNameNode> importOrgNameOpt = ((ImportDeclarationNode)context.node()).orgName();
        String importModuleName = ((ImportDeclarationNode)context.node()).moduleName().get(0).text();

        String packageOrgName = context.currentPackage().packageOrg().value();
        String packageName = context.currentPackage().packageName().value();

        String importOrgName = importOrgNameOpt.map(node -> node.orgName().text()).orElse(packageOrgName);

        // Do not warn if package org is one of ballerina/ballerinax/ballerinai
        boolean isBallerinaImport = ALLOWED_ORGS.contains(importOrgName);
        // Do not warn if the import is the import restrictor compiler plugin
        boolean isCompPluginImport = importOrgName.equals(THIS_PKG_ORG) && importModuleName.equals(THIS_PKG_NAME);
        // Do not warn if the import is a module of this package
        boolean isSubModule = importOrgName.equals(packageOrgName) && importModuleName.equals(packageName);

        if (!(isBallerinaImport || isCompPluginImport || isSubModule)) {
            DiagnosticInfo diagnosticInfo = new DiagnosticInfo(
                    RestrictorDiagnosticCodes.IMPORT_VIOLATION.getCode(),
                    RestrictorDiagnosticCodes.IMPORT_VIOLATION.getMessage(),
                    RestrictorDiagnosticCodes.IMPORT_VIOLATION.getSeverity());
            context.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, context.node().location()));
        }
    }
}
