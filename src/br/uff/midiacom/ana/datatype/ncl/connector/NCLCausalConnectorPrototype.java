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

import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public abstract class NCLCausalConnectorPrototype<T extends NCLCausalConnectorPrototype,
                                                  P extends NCLElement,
                                                  I extends NCLElementImpl,
                                                  Ec extends NCLCondition,
                                                  Ea extends NCLAction,
                                                  Ep extends NCLConnectorParamPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P> {

    protected Ec condition;
    protected Ea action;
    protected IdentifiableElementList<Ep, T> conn_params;


    /**
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do conector.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          caso o identificador seja inválido.
     */    
    public NCLCausalConnectorPrototype(String id) throws XMLException {
        super();
        setId(id);
        conn_params = new IdentifiableElementList<Ep, T>();
    }
    
    
    public NCLCausalConnectorPrototype() throws XMLException {
        super();
        conn_params = new IdentifiableElementList<Ep, T>();
    }
    
    
    /**
     * Atribui uma condição ao conector causal.
     * 
     * @param condition
     *          elemento representando uma condição do conector.
     */    
    public void setCondition(Ec condition) {
        //Retira o parentesco do condition atual
        if(this.condition != null){
            this.condition.setParent(null);
            impl.notifyRemoved(NCLElementSets.CONDITIONS, this.condition);
        }

        this.condition = condition;
        //Se condition existe, atribui este como seu parente
        if(this.condition != null){
            this.condition.setParent(this);
            impl.notifyInserted(NCLElementSets.CONDITIONS, this.condition);
        }
    }
    
    
    /**
     * Retorna a condição atribuida ao conector causal.
     * 
     * @return
     *          elemento representando uma condição do conector.
     */    
    public Ec getCondition() {
        return condition;
    }


    /**
     * Atribui uma ação ao conector causal.
     * 
     * @param action
     *          elemento representando uma ação do conector.
     */    
    public void setAction(Ea action) {
        //Retira o parentesco do action atual
        if(this.action != null){
            this.action.setParent(null);
            impl.notifyRemoved(NCLElementSets.ACTIONS, this.action);
        }

        this.action = action;
        //Se action existe, atribui este como seu parente
        if(this.action != null){
            this.action.setParent(this);
            impl.notifyInserted(NCLElementSets.ACTIONS, this.action);
        }
    }
    
    
    /**
     * Retorna a ação atribuida ao conector causal.
     *
     * @return
     *          elemento representando uma ação do conector.
     */
    public Ea getAction() {
        return action;
    }

    
    /**
     * Adiciona um parâmetro ao conector causal NCL.     
     * 
     * @param param
     *          parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(Ep param) throws XMLException {
        if(conn_params.add(param, (T) this)){
            impl.notifyInserted(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
    }


    /**
     * Remove um parâmetro do conector causal.
     *
     * @param param
     *          parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */
    public boolean removeConnectorParam(Ep param) throws XMLException {
        if(conn_params.remove(param)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
    }

    
    /**
     * Remove um parâmetro do conector causal.
     * 
     * @param name
     *          nome do parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */    
    public boolean removeConnectorParam(String name) throws XMLException {
        if(conn_params.remove(name)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, name);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o conector possui pelo menos um parâmetro.
     *
     * @return
     *          verdadeiro se o conector possuir pelo menos um parâmetro.
     */
    public boolean hasConnectorParam() {
        return !conn_params.isEmpty();
    }


    /**
     * Verifica se o conector possui um parâmetro.
     *
     * @param name
     *          nome do parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasConnectorParam(String name) throws XMLException {
        return conn_params.get(name) != null;
    }


    /**
     * Retorna os parâmetros do conector.
     *
     * @return
     *          lista contendo os parâmetros do conector.
     */
    public IdentifiableElementList<Ep, T> getConnectorParams() {
        return conn_params;
    }
}
