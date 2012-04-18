/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.aux.parameterized.DoubleParamType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLException;


/**
 * Interface that represents a condition element. The condition can be a simple
 * condition or a compound condition.
 * 
 * @param <T>
 * @param <P>
 * @param <Ep>
 * @param <R> 
 */
public interface NCLCondition<T extends NCLCondition,
                              P extends NCLElement,
                              Ep extends NCLConnectorParamPrototype,
                              R extends ConParamReference>
        extends NCLElement<T, P> {


    /**
     * Sets the delay waited by the condition. This attribute is optional. Set the
     * delay to <i>null</i> to erase a delay already defined.
     * 
     * <br/>
     * 
     * The delay can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this condition is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @param delay
     *          element representing the delay or <i>null</i> to erase a
     *          delay already defined.
     * @throws XMLException
     *          if an error occur while creating the delay value.
     */
    public void setDelay(DoubleParamType<Ep, T, R> delay) throws XMLException;


    /**
     * Returns the delay waited by the condition or <i>null</i> if the attribute
     * is not defined.
     * 
     * <br/>
     * 
     * The delay can be set as a parameter, in which case its value is defined by
     * the link that uses the connector where this condition is.
     * 
     * <br/>
     * 
     * In case the attribute value is defined by a parameter, the parameter must
     * be defined in the same connector where this attribute assessment it.
     * 
     * @return
     *          element representing the delay or <i>null</i> if the attribute
     *          is not defined.
     */
    public DoubleParamType<Ep, T, R> getDelay();
}
