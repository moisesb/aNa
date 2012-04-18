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
package br.uff.midiacom.ana.datatype.ncl.reuse;

import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.structure.NCLDocPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents an element used to import a base defined in an external
 * document (importBase) or an element used to import a document (importNCL).
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>alias</i> - name used to refer to the document referred in the attribute
 *                     documentURI. This attribute is required.</li>
 *  <li><i>documentURI</i> - URI of the document to be imported (if it is an
 *                           importNCL element) or the document that will have
 *                           its base imported (if it is an importBase element).
 *                           This attribute is required.</li>
 *  <li><i>region</i> - region to become the parent of the regions imported by a
 *                      region base. This attribute is optional.</li>
 * </ul>
 * 
 * The alias attribute has to be unique in a document. An importing is transitive,
 * that is, if base A imports base B and it imports base C, then base A imports
 * base C. However, the alias defined to base C inside base B is not considered
 * by base A.
 * 
 * <br/>
 * 
 * When importing a base of descriptors, if the document that defines the base
 * of descriptors also defines a base of regions and a base of rules, both are
 * also imported.
 * 
 * <br/>
 * 
 * When importing a base of regions, the import element can define a region
 * attribute. Every regions defined in the base of regions imported are considered
 * as children of that region.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Er>
 * @param <Ed> 
 */
public abstract class NCLImportPrototype<T extends NCLImportPrototype,
                                         P extends NCLElement,
                                         I extends NCLElementImpl,
                                         Er extends RegionReference,
                                         Ed extends NCLDocPrototype>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P>, ReferredElement<ReferenceType> {

    protected StringType alias;
    protected SrcType documentURI;
    protected Er region;

    protected NCLImportType type;
    protected Ed importedDoc;
    protected ItemList<ReferenceType> references;


    /**
     * Import element constructor.
     * 
     * @param type
     *          type of the import element. The type can be importBase or
     *          importNCL. The type is required an can not be <i>null</i>.
     *          The possible types are defined in the enumeration <i>NCLImportType</i>.
     * @throws XMLException 
     *          if the type is null or an error occur while creating the element.
     */
    public NCLImportPrototype(NCLImportType type) throws XMLException {
        super();
        if(type == null)
            throw new XMLException("Null type");

        this.type = type;
        references = new ItemList<ReferenceType>();
    }


    /**
     * Sets the name used to refer to the document referred in the attribute
     * documentURI. This attribute is required and can not be set to <i>null</i>.
     * 
     * @param alias
     *          string representing the name used to refer to the document
     *          referred in the attribute documentURI.
     * @throws XMLException 
     *          if the string is empty or null.
     */
    public void setAlias(String alias) throws XMLException {
        if(alias == null)
            throw new XMLException("Null alias");
        
        StringType aux = this.alias;
        this.alias = new StringType(alias);
        impl.notifyAltered(NCLElementAttributes.ALIAS, aux, alias);
    }


    /**
     * Returns the name used to refer to the document referred in the attribute
     * documentURI or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          string representing the name used to refer to the document
     *          referred in the attribute documentURI or <i>null</i> if the
     *          attribute is not defined.
     */
    public String getAlias() {
        if(alias != null)
            return alias.getValue();
        else
            return null;
    }


    /**
     * Sets the URI of the document to be imported (if it is an importNCL element)
     * or the document that will have its base imported (if it is an importBase
     * element). This attribute is required and can not be set to <i>null</i>.
     * 
     * @param documentURI
     *          element representing a URI.
     * @throws XMLException 
     *          if the URI is null.
     */
    public void setDocumentURI(SrcType documentURI) throws XMLException {
        if(documentURI == null)
            throw new XMLException("Null documentURI.");
        
        SrcType aux = this.documentURI;
        this.documentURI = documentURI;
        impl.notifyAltered(NCLElementAttributes.DOCUMENTURI, aux, documentURI);
    }


    /**
     * Returns the URI of the document to be imported (if it is an importNCL element)
     * or the document that will have its base imported (if it is an importBase
     * element) or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          element representing a URI or <i>null</i> if the attribute is not
     *          defined.
     */
    public SrcType getDocumentURI() {
        return documentURI;
    }


    /**
     * Set the region that will have the imported regions as children. This
     * attribute is optional an is only used by an importBase element that
     * imports a base of regions. The region must refer to a region defined in
     * the base of regions parent of the importBase element. Set the region to
     * <i>null</i> to erase the region already defined.
     * 
     * @param region
     *          element representing the referred region or <i>null</i> to erase
     *          a region already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the region.
     */
    public void setRegion(Er region) throws XMLException {
        Er aux = this.region;
        // Set the new region
        this.region = region;
        // If the region is not null, set the reference owner
        if(this.region != null){
            this.region.setOwner((T) this);
            this.region.setOwnerAtt(NCLElementAttributes.REGION);
        }
        
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the region that will have the imported regions as children or
     * <i>null</i> if no region is defined. This attribute is only used by an
     * importBase element that imports a base of regions. The region must refer
     * to a region defined in the base of regions parent of the importBase element.
     * 
     * @return
     *          element representing the referred region or <i>null</i> to erase
     *          a region already defined.
     */
    public Er getRegion() {
        return region;
    }
    
    
    /**
     * Sets the document imported by the import element.
     * 
     * @param importedDoc
     *          document imported.
     * @throws XMLException 
     *          if the import element does not import a document.
     */
    public void setImportedDoc(Ed importedDoc) {
        this.importedDoc = importedDoc;
    }
    
    
    /**
     * Returns the document imported by the import element.
     * 
     * @return 
     *          document imported.
     */
    public Ed getImportedDoc() {
        return importedDoc;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }


    @Override
    public boolean compare(T other) {
        return getAlias().equals(other.getAlias());
    }
}
