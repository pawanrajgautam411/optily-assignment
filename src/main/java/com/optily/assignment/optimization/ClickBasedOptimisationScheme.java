package com.optily.assignment.optimization;

import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;

/**
 *
 */
public class ClickBasedOptimisationScheme implements OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
    @Override
    public SchemeOutput recommendNow(SchemeInput schemeInput) {
        throw new RuntimeException("click-based-optimisation-not-implemented");
    }
}
