package com.optily.assignment.optimization;

import com.optily.assignment.api.OptimisationScheme;
import com.optily.assignment.api.SchemeInput;
import com.optily.assignment.api.SchemeOutput;

/**
 *
 */
public class ConversionBasedOptimisationScheme implements OptimisationScheme {

    /**
     * @param schemeInput
     * @return
     */
    @Override
    public SchemeOutput recommendNow(SchemeInput schemeInput) {
        throw new RuntimeException("conversion-based-optimisation-not-implemented");
    }
}
