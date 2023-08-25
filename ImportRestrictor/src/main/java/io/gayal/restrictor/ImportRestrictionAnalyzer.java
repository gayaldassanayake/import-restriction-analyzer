package io.gayal.restrictor;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

/**
 * Code analyzer for import restriction.
 */
public class ImportRestrictionAnalyzer extends CodeAnalyzer {

    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
        codeAnalysisContext.addSyntaxNodeAnalysisTask(
                new ImportRestrictionAnalysisTask(), SyntaxKind.IMPORT_DECLARATION);
    }
}
