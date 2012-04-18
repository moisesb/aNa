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
package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPortPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPropertyPrototype;
import br.uff.midiacom.ana.datatype.ncl.link.NCLLinkPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetaPrototype;
import br.uff.midiacom.ana.datatype.ncl.meta.NCLMetadataPrototype;
import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;


/**
 * Class that represents an NCL composite node.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the composite node element. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>port</i> - element representing a composite node interface point. The
 *                    composite node can have none or several port elements.</li>
 *  <li><i>property</i> - element representing a property. The composite node can
 *                        have none or several property elements.</li>
 *  <li><i>media</i> - element representing a media object. The composite node
 *                     can have none or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The composite node
 *                       can have none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      composite node can have none or several switch elements.</li>
 *  <li><i>link</i> - element representing a link among medias or compositions.
 *                    The composite node can have none or several link elements.</li>
 *  <li><i>meta</i> - elements defining meta data. The composite node can have
 *                    none or several meta elements.</li>
 *  <li><i>metadata</i> - elements defining a RDF tree. The composite node can
 *                        have none or several metadata elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ept>
 * @param <Epp>
 * @param <En>
 * @param <El>
 * @param <Em>
 * @param <Emt> 
 */
public abstract class NCLCompositeNodeElement<T extends NCLIdentifiableElement,
                                              P extends NCLElement,
                                              I extends NCLElementImpl,
                                              Ept extends NCLPortPrototype,
                                              Epp extends NCLPropertyPrototype,
                                              En extends NCLNode,
                                              El extends NCLLinkPrototype,
                                              Em extends NCLMetaPrototype,
                                              Emt extends NCLMetadataPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement<ReferenceType> {

    protected IdentifiableElementList<Ept, T> ports;
    protected IdentifiableElementList<Epp, T> properties;
    protected IdentifiableElementList<En, T> nodes;
    protected IdentifiableElementList<El, T> links;
    protected ElementList<Em, T> metas;
    protected ElementList<Emt, T> metadatas;
    
    protected ItemList<ReferenceType> references;


    /**
     * Composite node constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLCompositeNodeElement() throws XMLException {
        super();
        ports = new IdentifiableElementList<Ept, T>();
        properties = new IdentifiableElementList<Epp, T>();
        nodes = new IdentifiableElementList<En, T>();
        links = new IdentifiableElementList<El, T>();
        metas = new ElementList<Em, T>();
        metadatas = new ElementList<Emt, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Adds an element representing a composite node interface point to the
     * composite node. The composite node can have none or several port elements.
     *
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean addPort(Ept port) throws XMLException {
        if(ports.add(port, (T) this)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a composite node interface point of the
     * composite node. The composite node can have none or several port elements.
     *
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean removePort(Ept port) throws XMLException {
        if(ports.remove(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a composite node interface point of the
     * composite node. The composite node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          composite node interface point.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removePort(String id) throws XMLException {
        if(ports.remove(id)){
            impl.notifyRemoved(NCLElementSets.PORTS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a composite node interface point of the composite node. The composite node
     * can have none or several port elements.
     * 
     * @param port
     *          element representing a composite node interface point.
     * @return
     *          true if the composite node element has the port element.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean hasPort(Ept port) throws XMLException {
        return ports.contains(port);
    }


    /**
     * Verifies if the composite node element has a interface point with a
     * specific id. The composite node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          composite node interface point.
     * @return
     *          true if the composite node element has the port element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasPort(String id) throws XMLException {
        return ports.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one interface point.
     * The composite node can have none or several port elements.
     * 
     * @return 
     *          true if the composite node has at least one interface point.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Returns the list of interface points that a composite node element have.
     * The composite node can have none or several port elements.
     * 
     * @return 
     *          element list with all interface points.
     */
    public IdentifiableElementList<Ept, T> getPorts() {
        return ports;
    }


    /**
     * Adds an element representing a property to the composite node. The
     * composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean addProperty(Epp property) throws XMLException {
        if(properties.add(property, (T) this)){
            impl.notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a property of the composite node. The
     * composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean removeProperty(Epp property) throws XMLException {
        if(properties.remove(property)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a property of the composite node. The
     * composite node can have none or several property elements.
     *
     * @param name
     *          string representing the name of the property.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeProperty(String name) throws XMLException {
        if(properties.remove(name)){
            impl.notifyRemoved(NCLElementSets.PROPERTIES, name);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a property. The composite node can have none or several property elements.
     *
     * @param property
     *          element representing a property.
     * @return
     *          true if the composite node element has the property.
     * @throws XMLException 
     *          if the element representing the property is null.
     */
    public boolean hasProperty(Epp property) throws XMLException {
        return properties.contains(property);
    }


    /**
     * Verifies if the composite node element has a property with a specific
     * name. The composite node can have none or several property elements.
     * 
     * @param name
     *          string representing the name of the property.
     * @return
     *          true if the composite node element has the property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasProperty(String name) throws XMLException {
        return properties.get(name) != null;
    }


    /**
     * Verifies if the composite node element has at least one property. The
     * composite node can have none or several property elements.
     * 
     * @return 
     *          true if the composite node has at least one property.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Returns the list of properties that a composite node element have. The
     * composite node can have none or several property elements.
     * 
     * @return 
     *          element list with all properties.
     */
    public IdentifiableElementList<Epp, T> getProperties() {
        return properties;
    }


    /**
     * Adds an element representing a node to the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean addNode(En node) throws XMLException {
        if(nodes.add(node, (T) this)){
            impl.notifyInserted(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean removeNode(En node) throws XMLException {
        if(nodes.remove(node)){
            impl.notifyRemoved(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the composite node element. The
     * node can be a media, a context or a switch element. The composite node
     * can have none or several node elements.
     *
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeNode(String id) throws XMLException {
        if(nodes.remove(id)){
            impl.notifyRemoved(NCLElementSets.NODES, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a node. The node can be a media, a context or a switch element. The
     * composite node can have none or several node elements.
     *
     * @param node
     *          element representing a node.
     * @return
     *          true if the composite node element has the node.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean hasNode(En node) throws XMLException {
        return nodes.contains(node);
    }


    /**
     * Verifies if the composite node element has a node with a specific
     * id. The node can be a media, a context or a switch element. The composite
     * node can have none or several node elements.
     * 
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the composite node element has the node.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasNode(String id) throws XMLException {
        return nodes.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one node. The node 
     * can be a media, a context or a switch element. The composite node can
     * have none or several node elements.
     * 
     * @return 
     *          true if the composite node has at least one node.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Returns the list of nodes that a composite node element have. The node
     * can be a media, a context or a switch element. The composite node can 
     * have none or several node elements.
     * 
     * @return 
     *          element list with all nodes.
     */
    public IdentifiableElementList<En, T> getNodes() {
        return nodes;
    }


    /**
     * Adds an element representing a link among nodes to the composite node.
     * The composite node can have none or several link elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean addLink(El link) throws XMLException {
        if(links.add(link, (T) this)){
            impl.notifyInserted(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a link among nodes of the composite node.
     * The composite node can have none or several link elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean removeLink(El link) throws XMLException {
        if(links.remove(link)){
            impl.notifyRemoved(NCLElementSets.LINKS, link);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a link among nodes of the composite node.
     * The composite node can have none or several link elements. Note that it
     * is possible that a link element does not have an id.
     *
     * @param id
     *          string representing the id of the link.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeLink(String id) throws XMLException {
        if(links.remove(id)){
            impl.notifyRemoved(NCLElementSets.LINKS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific element representing
     * a link among nodes. The composite node can have none or several link
     * elements.
     *
     * @param link
     *          element representing a link among nodes.
     * @return
     *          true if the composite node element has the link.
     * @throws XMLException 
     *          if the element representing the link is null.
     */
    public boolean hasLink(El link) throws XMLException {
        return links.contains(link);
    }


    /**
     * Verifies if the composite node element has a link among nodes with a
     * specific id. The composite node can have none or several link elements.
     * Note that it is possible that a link element does not have an id.
     * 
     * @param id
     *          string representing the id of the link.
     * @return
     *          true if the composite node element has the link.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasLink(String id) throws XMLException {
        return links.get(id) != null;
    }


    /**
     * Verifies if the composite node element has at least one link among nodes.
     * The composite node can have none or several link elements.
     * 
     * @return 
     *          true if the composite node has at least one link.
     */
    public boolean hasLink() {
        return !links.isEmpty();
    }


    /**
     * Returns the list of links among nodes that a composite node element have.
     * The composite node can have none or several link elements.
     * 
     * @return 
     *          element list with all links.
     */
    public IdentifiableElementList<El, T> getLinks() {
        return links;
    }


    /**
     * Adds an element defining meta data to the composite node element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was added.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean addMeta(Em meta) throws XMLException {
        if(metas.add(meta, (T) this)){
            impl.notifyInserted(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining meta data of the composite node element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the meta element was removed.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean removeMeta(Em meta) throws XMLException {
        if(metas.remove(meta)){
            impl.notifyRemoved(NCLElementSets.METAS, meta);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific meta element. The
     * composite node can have none or several meta elements.
     * 
     * @param meta
     *          element defining meta data.
     * @return
     *          true if the composite node element has the meta element.
     * @throws XMLException 
     *          if the meta element is null.
     */
    public boolean hasMeta(Em meta) throws XMLException {
        return metas.contains(meta);
    }


    /**
     * Verifies if the composite node element has at least one meta element. The
     * composite node can have none or several meta elements.
     * 
     * @return 
     *          true if the composite node has at least one meta element.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Returns the list of meta elements that a composite node element have. The
     * composite node can have none or several meta elements.
     * 
     * @return 
     *          element list with all meta elements.
     */
    public ElementList<Em, T> getMetas() {
        return metas;
    }


    /**
     * Adds an element defining an RDF tree to the composite node element. The
     * composite node can have none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was added.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean addMetadata(Emt metadata) throws XMLException {
        if(metadatas.add(metadata, (T) this)){
            impl.notifyInserted(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    /**
     * Removes an element defining an RDF tree of the composite node element. The
     * composite node can have none or several metadata elements.
     * 
     * @param metadata
     *          element defining an RDF tree.
     * @return
     *          true if the metadata element was removed.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean removeMetadata(Emt metadata) throws XMLException {
        if(metadatas.remove(metadata)){
            impl.notifyRemoved(NCLElementSets.METADATAS, metadata);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the composite node element has a specific metadata element.
     * The composite node can have none or several metadata elements.
     * 
     * @param meta
     *          element defining an RDF tree.
     * @return
     *          true if the composite node element has the metadata element.
     * @throws XMLException 
     *          if the metadata element is null.
     */
    public boolean hasMetadata(Emt metadata) throws XMLException {
        return metadatas.contains(metadata);
    }


    /**
     * Verifies if the composite node element has at least one metadata element.
     * The composite node can have none or several metadata elements.
     * 
     * @return 
     *          true if the composite node has at least one metadata element.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Returns the list of metadata elements that a composite node element have.
     * The composite node can have none or several metadata elements.
     * 
     * @return 
     *          element list with all metadata elements.
     */
    public ElementList<Emt, T> getMetadatas() {
        return metadatas;
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
}
