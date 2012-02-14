/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


public class NCLConnectorParamPrototype<T extends NCLConnectorParamPrototype, P extends NCLElement, I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {
    
    protected StringType type;
    
    
    /**
     * Construtor do elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     *         java.lang.IllegalArgumentException
     *          se a String do tipo for vazia.
     */
    public NCLConnectorParamPrototype(String name) throws XMLException {
        super();
        setName(name);
    }

    public NCLConnectorParamPrototype() throws XMLException {
        super();
    }

    /**
     * Atribui um nome ao parâmetro
     *
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     */
    public void setName(String name) throws XMLException {
        setId(name);
    }
    
    
    /**
     * Retorna o nome do parâmetro
     * 
     * @return
     *          String contendo o nome atribuido ao parâmetro.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um tipo ao parâmetro
     *
     * @param type
     *      String contendo o tipo do parâmetro.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setType(String type) throws XMLException {
        this.type = new StringType(type);
    }


    /**
     * Retorna o tipo do parâmetro
     * 
     * @return
     *      String contendo o tipo do parâmetro.
     */
    public String getType() {
        if(type == null)
            return null;
        else
            return type.getValue();
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<connectorParam";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseType();
        
        return content;
    }
    
    
    protected String parseName() {
        String aux = getName();
        if(aux != null)
            return " name='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseType() {
        String aux = getType();
        if(aux != null)
            return " type='" + aux + "'";
        else
            return "";
    }
}
