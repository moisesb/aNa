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
package br.uff.midiacom.ana.datatype.ncl.meta;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents the metadata element. This element is used to describe
 * an NCL document. This element defines a RDF tree that describes the document.
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLMetadataPrototype<T extends NCLMetadataPrototype,
                                           P extends NCLElement,
                                           I extends NCLElementImpl>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected StringType rdfTree;


    /**
     * Metadata element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLMetadataPrototype() throws XMLException {
        super();
    }


    /**
     * Sets the content of the metadata element. This element content is a RDF
     * tree. This content is required and can not be set to <i>null</i>.
     * 
     * @param rdfTree
     *          string representing the metadata content. This content is a RDF
     *          tree.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setRDFTree(String rdfTree) throws XMLException {
        if(rdfTree == null)
            throw new XMLException("Null metadata content.");
        
        StringType aux = this.rdfTree;
        this.rdfTree = new StringType(rdfTree);
        impl.notifyAltered(NCLElementAttributes.RDFTREE, aux, rdfTree);
    }


    /**
     * Returns the content of the metadata element or <i>null</i> if the content
     * is not defined. This element content is a RDF tree.
     * 
     * @return 
     *          string representing the metadata content. This content is a RDF
     *          tree or <i>null</i> if the content is not defined.
     */
    public String getRDFTree() {
        if(rdfTree != null)
            return rdfTree.getValue();
        else
            return null;
    }


    @Override
    public boolean compare(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
