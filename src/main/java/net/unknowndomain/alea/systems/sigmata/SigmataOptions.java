/*
 * Copyright 2021 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.sigmata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import net.unknowndomain.alea.systems.annotations.RpgSystemData;
import net.unknowndomain.alea.systems.annotations.RpgSystemOption;


/**
 *
 * @author journeyman
 */
@RpgSystemData(bundleName = "net.unknowndomain.alea.systems.sigmata.RpgSystemBundle")
public class SigmataOptions extends RpgSystemOptions
{
    @RpgSystemOption(name = "processor", shortcode = "p", description = "sigmata.options.processor", argName = "tacticValue")
    private Integer processor;
    
    @Override
    public boolean isValid()
    {
        return !(isHelp());
    }
    
    public boolean isBasic()
    {
        return (processor != null);
    }
    
    public Integer getProcessor()
    {
        return processor;
    }

    public Collection<SigmataModifiers> getModifiers()
    {
        Set<SigmataModifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(SigmataModifiers.VERBOSE);
        }
        return mods;
    }
    
}