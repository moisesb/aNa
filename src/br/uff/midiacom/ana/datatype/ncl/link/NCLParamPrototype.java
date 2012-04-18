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
package br.uff.midiacom.ana.datatype.ncl.link;

import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents a link or bind parameter element. This element is used
 * to define a value to a parameter defined in a connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - reference to the name of the parameter defined by a
 *                    connector. This attribute is required.</li>
 *  <li><i>value</i> - value of the parameter defined by a connector. This
 *                    attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ec> 
 */
public abstract class NCLParamPrototype<T extends NCLParamPrototype,
                                        P extends NCLElement,
                                        I extends NCLElementImpl,
                                        Ec extends ConParamReference>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>{

    protected Ec name;
    protected StringType value;
    protected NCLParamInstance paramType;
    
    
    /**
     * Parameter element constructor.
     * 
     * @param paramType
     *          type of the parameter from the enumeration <i>NCLParamInstance</i>.
     *          The parameter type can be <i>link</i> or <i>bind</i>.
     * @throws XMLException 
     *          if the type is null or an error occur while creating the element.
     */
    public NCLParamPrototype(NCLParamInstance paramType) throws XMLException {
        super();
        if(paramType == null)
            throw new XMLException("Null type");

        this.paramType = paramType;
    }
    
    
    /**
     * Sets the reference to the name of the parameter defined by the connector.
     * This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The connector parameter referred must be a parameter defined by the
     * connector used by the link where this parameter element is defined.
     * 
     * @param connectorParam
     *          element representing a reference to a connector parameter name.
     * @throws XMLException 
     *          if the connector parameter is null or any error occur while
     *          creating the reference to the connector parameter.
     */
    public void setName(Ec connectorParam) throws XMLException {
        if(connectorParam == null)
            throw new XMLException("Null connector parameter.");
        
        Ec aux = this.name;
        
        this.name = connectorParam;
        this.name.setOwner((T) this);
        this.name.setOwnerAtt(NCLElementAttributes.NAME);
        
        impl.notifyAltered(NCLElementAttributes.NAME, aux, connectorParam);
        if(aux != null)
            aux.clean();
    }
    
    
    /**
     * Returns the reference to the name of the parameter defined by the
     * connector or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The connector parameter referred must be a parameter defined by the
     * connector used by the link where this parameter element is defined.
     * 
     * @return 
     *          element representing a reference to a connector parameter name
     *          or <i>null</i> if the attribute is not defined.
     */
    public Ec getName() {
        return name;
    }
    
    
    /**
     * Sets the value of the parameter defined by a connector. This attribute is
     * required and can not be set to <i>null</i>.
     * 
     * @param value
     *          string representing the value to be set to the connector parameter.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setValue(String value)  throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        
        StringType aux = this.value;
        this.value = new StringType(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
    /**
     * Returns the value of the parameter defined by a connector or <i>null</i>
     * if the attribute is not defined.
     * 
     * @return
     *          string representing the value to be set to the connector parameter
     *          or <i>null</i> if the attribute is not defined.
     */
    public String getValue() {
        if(value != null)
            return value.getValue();
        else
            return null;
    }


    /**
     * Returns the type of the parameter element. The possible types are defined
     * in the enumeration <i>NCLParamInstance</i>.
     * 
     * <br/>
     * 
     * The parameter type can be <i>link</i> or <i>bind</i>.
     * 
     * @return 
     *          type of the parameter from the enumeration <i>NCLParamInstance</i>.
     */
    public NCLParamInstance getType() {
        return paramType;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null)
            return false;
        
        return getName().equals(other.getName());
    }
}
