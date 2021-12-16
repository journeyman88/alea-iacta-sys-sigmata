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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class StigmataResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.stigmata.RpgSystemBundle";
    
    private final List<SingleResult<Integer>> results;
    private int successes = 0;
    private StigmataResults prev;
    
    public StigmataResults(List<SingleResult<Integer>> results)
    {
        List<SingleResult<Integer>> tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
    }
    
    public void addSuccess()
    {
        successes++;
    }
    
    public void addFailure()
    {
        successes--;
    }

    public int getSuccesses()
    {
        return successes;
    }

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }

    public StigmataResults getPrev()
    {
        return prev;
    }

    public void setPrev(StigmataResults prev)
    {
        this.prev = prev;
    }
    
    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        messageBuilder.append(indent).append(translate("stigmata.results.successes", getSuccesses())).appendNewLine();
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            messageBuilder.append(indent).append(translate("stigmata.results.diceResults")).append(" [ ");
            for (SingleResult<Integer> t : getResults())
            {
                messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                messageBuilder.append(t.getValue()).append(") ");
            }
            messageBuilder.append("]\n");
            if (prev != null)
            {
                messageBuilder.append(translate("stigmata.results.prevResults"));
                messageBuilder.append("{\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append("}\n");
            }
        }
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }

}
