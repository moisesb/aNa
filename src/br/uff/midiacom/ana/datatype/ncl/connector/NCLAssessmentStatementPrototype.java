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

import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


/**
 * Class that represents a assessment statement element. This element is used
 * to compares to values. The values can be an attribute of a node or event or
 * a value.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>comparator</i> - indicates the comparison made. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>attributeAssessment</i> - element that represents the attribute whose
 *                                   value will be compared. The assessment
 *                                   statement can have one or two attribute
 *                                   assessment elements.</li>
 *  <li><i>valueAssessment</i> - element representing value to be compared. The
 *                               assessment statement can have none or one value
 *                               assessment element.</li>
 * </ul>
 * 
 * Note that the assessment statement will compare either two attributes or an
 * attribute with a value.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Ev>
 * @param <Es> 
 */
public abstract class NCLAssessmentStatementPrototype<T extends NCLAssessmentStatementPrototype,
                                                      P extends NCLElement,
                                                      I extends NCLElementImpl,
                                                      Ea extends NCLAttributeAssessmentPrototype,
                                                      Ev extends NCLValueAssessmentPrototype,
                                                      Es extends NCLStatement>
        extends NCLElementPrototype<Es, P, I>
        implements NCLStatement<Es, P> {

    protected NCLComparator comparator;
    protected Ev valueAssessment;
    protected ElementList<Ea, T> attributeAssessments;
    

    /**
     * Assessment statement element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLAssessmentStatementPrototype() throws XMLException {
        super();
        attributeAssessments = new ElementList<Ea, T>();
    }


    /**
     * Sets the type of comparison to be made. This attribute is required an can
     * not be set to <i>null</i>. The possible comparison values are defined in
     * the enumeration <i>NCLComparator</i>.
     * 
     * @param comparator
     *          element representing the type of comparison from the enumeration
     *          <i>NCLComparator</i>.
     * @throws XMLException 
     *          if the element is null.
     */
    public void setComparator(NCLComparator comparator) throws XMLException {
        if(comparator == null)
            throw new XMLException("Null comparison.");
        
        NCLComparator aux = this.comparator;
        this.comparator = comparator;
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }
    
    
    /**
     * Returns the type of comparison to be made or <i>null</i> if the attribute
     * is not defined. The possible comparison values are defined in the
     * enumeration <i>NCLComparator</i>.
     * 
     * @return
     *          element representing the type of comparison from the enumeration
     *          <i>NCLComparator</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Sets the element representing value to be compared. The assessment
     * statement can have none or one value assessment element. Set the value
     * assessment to <i>null</i> to erase a value assessment already defined.
     * 
     * @param value
     *          element representing a value assessment or <i>null</i> to erase
     *          a value already defined.
     */
    public void setValueAssessment(Ev value) {
        //Removes the parent of the actual value
        if(this.valueAssessment != null){
            this.valueAssessment.setParent(null);
            impl.notifyRemoved(NCLElementSets.VALUEASSESSMENT, this.valueAssessment);
        }

        this.valueAssessment = value;
        //Sets this as the parent of the new value
        if(this.valueAssessment != null){
            this.valueAssessment.setParent(this);
            impl.notifyInserted(NCLElementSets.VALUEASSESSMENT, this.valueAssessment);
        }
    }
    
    
    /**
     * Returns the element representing value to be compared or <i>null</i> if
     * the value is not defined. The assessment statement can have none or one
     * value assessment element.
     * 
     * @return
     *          element representing a value assessment or <i>null</i> if the
     *          value is not defined.
     */
    public Ev getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Adds an element that represents the attribute whose value will be compared.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the element representing an attribute assessment was added.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null or
     *          the assessment statement already have two attribute assessments.
     */
    public boolean addAttributeAssessment(Ea attribute) throws XMLException {
        if(attributeAssessments.size() == 2)
            throw new XMLException("can't have more than two attributes");
        
        if(attributeAssessments.add(attribute, (T) this)){
            impl.notifyInserted(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes an element that represents the attribute whose value will be compared.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the element representing an attribute assessment was removed.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null.
     */
    public boolean removeAttributeAssessment(Ea attribute) throws XMLException {
        if(attributeAssessments.remove(attribute)){
            impl.notifyRemoved(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the assessment statement has a specific element that represents
     * the attribute whose value will be compared. The assessment statement can
     * have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the assessment statement has the element representing an
     *          attribute assessment.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null.
     */
    public boolean hasAttributeAssessment(Ea attribute) throws XMLException {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifies if the assessment statement has at least one element that represents
     * the attribute whose value will be compared. The assessment statement can
     * have one or two attribute assessment elements.
     * 
     * @return
     *          true if the assessment statement has at least one attribute
     *          assessment.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }


    /**
     * Returns the list of attribute assessments that an assessment statement have.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @return 
     *          element list with all attribute assessments.
     */
    public ElementList<Ea, T> getAttributeAssessments() {
        return attributeAssessments;
    }
    
    
    @Override
    public boolean compare(Es other) {
        boolean comp = true;

        String this_stat, other_stat;
        NCLAssessmentStatementPrototype other_asses;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLAssessmentStatementPrototype))
            return false;

        other_asses = (NCLAssessmentStatementPrototype) other;
        
        // Compara pelo comparador
        if(getComparator() == null) this_stat = ""; else this_stat = getComparator().toString();
        if(other_asses.getComparator() == null) other_stat = ""; else other_stat = other_asses.getComparator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara o número de attributeAssessment
        comp &= attributeAssessments.size() == other_asses.getAttributeAssessments().size();

        // Compara os attributeAssessment
        Iterator it = other_asses.getAttributeAssessments().iterator();
        for(NCLAttributeAssessmentPrototype att : attributeAssessments){
            if(!it.hasNext())
                continue;
            NCLAttributeAssessmentPrototype other_att = (NCLAttributeAssessmentPrototype) it.next();
            comp &= att.compare(other_att);
            if(comp)
                break;
        }

        // Compara os valueAssessment
        if(getValueAssessment() != null && other_asses.getValueAssessment() != null)
            comp &= getValueAssessment().compare(other_asses.getValueAssessment());


        return comp;
    }
}
