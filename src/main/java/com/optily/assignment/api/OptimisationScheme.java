package com.optily.assignment.api;

/**
 *
 */
public interface OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
     SchemeOutput recommendNow(SchemeInput schemeInput);
}
