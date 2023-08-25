package io.gayal.restrictor;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;

/**
 * Compiler plugin for import restriction. This allows only Ballerina platform modules to be imported.
 */
public class ImportRestrictionPlugin extends CompilerPlugin {
    @Override
    public void init(CompilerPluginContext compilerPluginContext) {
        compilerPluginContext.addCodeAnalyzer(new ImportRestrictionAnalyzer());
    }
}
