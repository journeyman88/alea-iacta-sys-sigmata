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
package net.unknowndomain.alea.systems.stigmata;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.unknowndomain.alea.dice.standard.D10;
import net.unknowndomain.alea.dice.standard.D6;
import net.unknowndomain.alea.pools.DicePool;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class StigmataRoll implements GenericRoll
{
    
    private final DicePool<D10> mainPool;
    private final DicePool<D6> complPool;
    private final Set<StigmataModifiers> mods;
    
    public StigmataRoll(Integer tacticValue, StigmataModifiers ... mod)
    {
        this(tacticValue, Arrays.asList(mod));
    }
    
    public StigmataRoll(Integer tacticValue, Collection<StigmataModifiers> mod)
    {
        
        this.mods = new HashSet<>();
        if (mod != null)
        {
            this.mods.addAll(mod);
        }
        int mainDice = tacticValue;
        if (mainDice < 0)
        {
            mainDice = 0;
        }
        if (mainDice > 5)
        {
            mainDice = 5;
        }
        int complDice = 5 - mainDice;
        this.mainPool = new DicePool<>(D10.INSTANCE, mainDice);
        this.complPool = new DicePool<>(D6.INSTANCE, complDice);
    }
    
    @Override
    public GenericResult getResult()
    {
        List<Integer> resultsPool = this.mainPool.getResults();
        resultsPool.addAll(this.complPool.getResults());
        StigmataResults results = new StigmataResults(resultsPool);
        for (Integer r : resultsPool)
        {
            if (r == 1)
            {
                results.addFailure();
            }
            if (r >= 6)
            {
                results.addSuccess();
            }
            
        }
        results.setVerbose(mods.contains(StigmataModifiers.VERBOSE));
        return results;
    }
    
    
}
