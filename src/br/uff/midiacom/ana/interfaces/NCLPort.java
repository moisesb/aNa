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
package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLPortPrototype;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLPort<T extends NCLPort, P extends NCLElement, I extends NCLElementImpl, En extends NCLNode, Ei extends NCLInterface>
        extends NCLPortPrototype<T, P, I, En, Ei> implements NCLInterface<Ei, P> {


    public NCLPort(String id) throws XMLException {
        super(id);
    }


    public NCLPort(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setComponent(En component) {
        En aux = this.component;
        super.setComponent(component);
        impl.notifyAltered(NCLElementAttributes.COMPONENT, aux, component);
    }
    
        
    @Override
    public void setInterface(Ei interfac) {
        Ei aux = this.interfac;
        super.setInterface(interfac);
        impl.notifyAltered(NCLElementAttributes.INTERFACE, aux, interfac);
    }


//    private void componentReference() {
//        //Search for a component node in its parent
//        Set<N> nodes;
//
//        if(getParent() instanceof NCLBody)
//            nodes = ((NCLBody) getParent()).getNodes();
//        else
//            nodes = ((NCLContext) getParent()).getNodes();
//
//        for(N node : nodes){
//            if(node.getId().equals(getComponent().getId())){
//                setComponent(node);
//                return;
//            }
//        }
//
//        addWarning("Could not find node with id: " + getComponent().getId());
//    }


//    private void interfaceReference() {
//        //Search for the interface inside the node
//        Set<I> ifaces;
//
//        if(getComponent() instanceof NCLMedia){
//            ifaces = ((NCLMedia) getComponent()).getAreas();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((NCLMedia) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof NCLContext){
//            ifaces = ((NCLContext) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//            ifaces = ((NCLContext) getComponent()).getProperties();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//        else if(getComponent() instanceof NCLSwitch){
//            ifaces = ((NCLSwitch) getComponent()).getPorts();
//            for(I iface : ifaces){
//                if(iface.getId().equals(getInterface().getId())){
//                    setInterface(iface);
//                    return;
//                }
//            }
//        }
//
//        addWarning("Could not find interface with id: " + getInterface().getId());
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the component (required)
        att_name = NCLElementAttributes.COMPONENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;// setComponent(); //@todo: tem que buscar component pelo id
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the interface (optional)
        att_name = NCLElementAttributes.INTERFACE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;// setInterface(); //@todo: tem que buscar interface pelo id
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
