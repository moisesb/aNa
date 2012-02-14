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
package br.uff.midiacom.ana.datatype.ncl.node;

import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMediaType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLAreaPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLMediaPrototype<T extends NCLMediaPrototype, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLAreaPrototype, Ep extends NCLPropertyPrototype, Ed extends NCLLayoutDescriptor, En extends NCLNode>
        extends NCLIdentifiableElementPrototype<En, P, I> implements NCLNode<En, P> {

    protected SrcType src;
    protected NCLMimeType type;
    protected Ed descriptor;
    protected T refer;
    protected NCLInstanceType instance;
    protected IdentifiableElementList<Ea, T> areas;
    protected IdentifiableElementList<Ep, T> properties;
    
    
    /**
     * Construtor do elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do elemento media.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da media for inválido.
     */
    public NCLMediaPrototype(String id) throws XMLException {
        super();
        setId(id);
        areas = new IdentifiableElementList<Ea, T>();
        properties = new IdentifiableElementList<Ep, T>();
    }


    public NCLMediaPrototype() throws XMLException {
        super();
        areas = new IdentifiableElementList<Ea, T>();
        properties = new IdentifiableElementList<Ep, T>();
    }
    
    
    /**
     * Atribui a URI do conteúdo da mídia.
     * 
     * @param src
     *          String contendo a URI do conteúdo da mídia.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *
     * @see java.net.URI
     */
    public void setSrc(SrcType src) {
        this.src = src;
    }
    
    
    /**
     * Retorna o valor da URI do conteúdo da mídia.
     * 
     * @return
     *          String contendo a URI do conteúdo da mídia.
     *
     * @see NCLMediaPrototype#setSrc(java.lang.String)
     * @see NCLMediaPrototype#setSrc(br.pensario.datatype.NCLUriType, java.lang.String)
     * @see NCLMediaPrototype#setSrc(br.pensario.interfaces.TimeType)
     */
    public SrcType getSrc() {
        return src;
    }
    
    
    /**
     * Determina o tipo da mídia.
     * O tipo da mídia será um dos tipos padronizados na norma.
     * 
     * @param type
     *          tipo da mídia.
     */
    public void setType(NCLMimeType type) {
        this.type = type;
    }
    
    
    /**
     * Retorna o tipo da mídia.
     * 
     * @return
     *          tipo da mídia.
     */
    public NCLMimeType getType() {
        return type;
    }
    
    
    /**
     * Determina o descritor da mídia.
     * 
     * @param descriptor
     *          elemento representando o descritor da mídia.
     */
    public void setDescriptor(Ed descriptor) {
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor da mídia.
     * 
     * @return
     *          elemento representando o descritor da mídia.
     */
    public Ed getDescriptor() {
        return descriptor;
    }


    /**
     * Atribui uma media para ser reutilizada pela media.
     *
     * @param refer
     *          elemento representando a media a ser reutilizado.
     */
    public void setRefer(T refer) {
        this.refer = refer;
    }


    /**
     * Retorna a media reutilizada pela media.
     *
     * @return
     *          elemento representando a media a ser reutilizado.
     */
    public T getRefer() {
        return refer;
    }


    /**
     * Determina o tipo de instância de outra media essa media é.
     *
     * @param instance
     *          elemento representando o tipo de instancia.
     */
    public void setInstance(NCLInstanceType instance) {
        this.instance = instance;
    }


    /**
     * Retorna o tipo de instância de outra media essa media é.
     *
     * @return
     *          elemento representando o tipo de instancia.
     */
    public NCLInstanceType getInstance() {
        return instance;
    }
    
    
    /**
     * Adiciona uma âncora a mídia.
     * 
     * @param area
     *          elemento representando a âncora a ser adicionada.
     * @return
     *          Verdadeiro se a âncora foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addArea(Ea area) throws XMLException {
        return areas.add(area, (T) this);
    }


    /**
     * Remove uma âncora a mídia.
     *
     * @param area
     *          elemento representando a âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#add
     */
    public boolean removeArea(Ea area) throws XMLException {
        return areas.remove(area);
    }
    
    
    /**
     * Remove uma âncora da mídia.
     *
     * @param id
     *          identificador da âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeArea(String id) throws XMLException {
        return areas.remove(id);
    }


    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param area
     *          elemento representando a âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(Ea area) throws XMLException {
        return areas.contains(area);
    }
    
    
    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param id
     *          identificador da âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(String id) throws XMLException {
        return areas.get(id) != null;
    }
    
    
    /**
     * Verifica se a mídia possui alguma âncora.
     * 
     * @return
     *          verdadeiro se a mídia possuir alguma âncora.
     */
    public boolean hasArea() {
        return !areas.isEmpty();
    }
    
    
    /**
     *  Retorna as âncoras da mídia.
     *
     * @return
     *          lista contendo as âncoras da mídia.
     */
    public IdentifiableElementList<Ea, T> getAreas() {
        return areas;
    }
    
    
    /**
     * Adiciona uma propriedade a mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(Ep property) throws XMLException {
        return properties.add(property, (T) this);
    }


    /**
     * Remove uma propriedade da mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(Ep property) throws XMLException {
        return properties.remove(property);
    }
    

    /**
     * Remove uma propriedade da mídia.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) throws XMLException {
        return properties.remove(name);
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(Ep property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifica se a mídia possui alguma propriedade.
     *
     * @return
     *          verdadeiro se a mídia possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Retorna as propriedades da mídia.
     *
     * @return
     *          lista contendo as propriedades da mídia.
     */
    public IdentifiableElementList<Ep, T> getProperties() {
        return properties;
    }


    /**
     * Retorna o tipo da mídia de acordo com seu tipo especificado ou sua URL.
     *
     * @return
     *          elemento representando o tipo da mídia.
     */
    public NCLMediaType getMediaType() {
        if(getType() != null){
            try{
                return NCLMediaType.getEnumType(getType());
            }
            catch(NCLParsingException e){}
        }

        if(getSrc() != null){
            try{
                return NCLMediaType.getEnumType(getSrc().getExtension());
            }
            catch(NCLParsingException e){}
        }

        return NCLMediaType.OTHER;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        content += parseAttributes();
        
        // Test if the media has content
        if(hasArea() || hasProperty()){
            content += ">\n";
            
            content += parseElements(ident + 1);
            
            content += space + "</media>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseSrc();
        content += parseType();
        content += parseDescriptor();
        content += parseRefer();
        content += parseInstance();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseAreas(ident);
        content += parseProperties(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseSrc() {
        SrcType aux = getSrc();
        if(aux != null)
            return " src='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseType() {
        NCLMimeType aux = getType();
        if(aux != null)
            return " type='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseDescriptor() {
        Ed aux = getDescriptor();
        if(aux != null)
            return " descriptor='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseRefer() {
        T aux = getRefer();
        if(aux != null)
            return " refer='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseInstance() {
        NCLInstanceType aux = getInstance();
        if(aux != null)
            return " instance='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseAreas(int ident) {
        if(!hasArea())
            return "";
        
        String content = "";
        for(Ea aux : areas)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseProperties(int ident) {
        if(!hasProperty())
            return "";
        
        String content = "";
        for(Ep aux : properties)
            content += aux.parse(ident);
        
        return content;
    }
}
