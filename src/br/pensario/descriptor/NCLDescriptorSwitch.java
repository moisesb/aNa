package br.pensario.descriptor;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um switch de descritor em um documento NCL.<br>
 *
 *
 *@see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLDescriptorSwitch<D extends NCLDescriptor, B extends NCLBindRule, L extends NCLLayoutDescriptor> extends NCLIdentifiableElement implements NCLLayoutDescriptor<L> {

    private Set<D> descriptors = new TreeSet<D>();
    private Set<B> binds = new TreeSet<B>();
    private D defaultDescriptor;


    /**
     * Construtor do elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch de descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch de descritor não for válido.
     */
    public NCLDescriptorSwitch(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLDescriptorSwitch(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona um descritor ao switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptor(D descriptor) {
        if(descriptors.add(descriptor)){
            //Se descriptor existe, atribui este como seu parente
            if(descriptor != null)
                descriptor.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param id
     *          identificador do descritor a ser removido.
     * @return
     *          Verdadeiro se o descritor foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(String id) {
        for(D descriptor : descriptors){
            if(descriptor.getId().equals(id))
                return removeDescriptor(descriptor);
        }
        return false;
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(D descriptor) {
        if(descriptors.remove(descriptor)){
            //Se descriptor existe, retira o seu parentesco
            if(descriptor != null)
                descriptor.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch de descritor contém um descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser verificado.
     */
    public boolean hasDescriptor(D descriptor) {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifica se o switch de descritor possui algum descritor.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum descritor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Retorna os descritores do switch de descritor.
     *
     * @return
     *          objeto Iterable contendo os descritores do switch de descritor.
     */
    public Iterable<D> getDescriptors() {
        return descriptors;
    }


    /**
     * Adiciona um bind ao switch de descritor.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBind(B bind) {
        if(binds.add(bind)){
            //Se bind existe, atribui este como seu parente
            if(bind != null)
                bind.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um bind do switch de descritor.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBind(B bind) {
        if(binds.remove(bind)){
            //Se bind existe, retira o seu parentesco
            if(bind != null)
                bind.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch de descritor contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(B bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch de descritor possui algum bind.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch de descritor.
     *
     * @return
     *          objeto Iterable contendo os binds do switch de descritor.
     */
    public Iterable<B> getBinds() {
        return binds;
    }


    /**
     * Determina o descritor padrão do switch de descritor.
     *
     * @param defaultDescriptor
     *          elemento representando o descritor padrão.
     */
    public void setDefaultDescriptor(D defaultDescriptor) {
        this.defaultDescriptor = defaultDescriptor;
    }


    /**
     * Retorna o descritor padrão do switch de descritor.
     *
     * @return
     *          elemento representando o descritor padrão.
     */
    public D getDefaultDescriptor() {
        return defaultDescriptor;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptorSwitch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasBind()){
            for(B bind : binds)
                content += bind.parse(ident + 1);
        }

        if(getDefaultDescriptor() != null)
            content += space + "\t" + "<defaultDescriptor descriptor='" + getDefaultDescriptor().getId() + "'/>\n";

        if(hasDescriptor()){
            for(D descriptor : descriptors)
                content += descriptor.parse(ident + 1);
        }

        content += space + "</descriptorSwitch>\n";


        return content;
    }


    public int compareTo(L other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(!hasDescriptor() || !hasBind()){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir ao menos um descritor e um bindRule.");
            valid = false;
        }

        if(hasBind()){
            for(B bind : binds){
                valid &= bind.validate();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
        if(hasDescriptor()){
            for(D desc : descriptors){
                valid &= desc.validate();
                addWarning(desc.getWarnings());
                addError(desc.getErrors());
            }
        }

        if(getDefaultDescriptor() != null){
            if(!(getDefaultDescriptor().getParent() instanceof NCLDescriptorSwitch)){
                addError("Atributo descriptor do elemento defaultDescriptor deve referênciar um descritor contido no descriptorSwitch.");
                valid = false;
            }
            else if(((NCLDescriptorSwitch) getDefaultDescriptor().getParent()).compareTo(this) != 0){
                addError("Atributo descriptor do elemento defaultDescriptor deve referênciar um descritor contido no descriptorSwitch.");
                valid = false;
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("descriptorSwitch")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("bindRule")){
                B child = createBindRule();
                child.startElement(uri, localName, qName, attributes);
                addBind(child);
            }
            else if(localName.equals("descriptor")){
                D child = createDescriptor();
                child.startElement(uri, localName, qName, attributes);
                addDescriptor(child);
            }
            else if(localName.equals("defaultDescriptor")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("descriptor"))
                        setDefaultDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if(localName.equals("descriptorSwitch"))
            super.endElement(uri, localName, qName);
    }


    @Override
    public void endDocument() {
        if(getDefaultDescriptor() != null)
            defaultDescriptorReference();

        if(hasBind()){
            for(B bind : binds){
                bind.endDocument();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
        if(hasDescriptor()){
            for(D descriptor : descriptors){
                descriptor.endDocument();
                addWarning(descriptor.getWarnings());
                addError(descriptor.getErrors());
            }
        }
    }


    private void defaultDescriptorReference() {
        //Search for a component node in its parent
        for(D descriptor : descriptors){
            if(descriptor.getId().equals(getDefaultDescriptor().getId())){
                setDefaultDescriptor(descriptor);
                return;
            }
        }

        addWarning("Could not find descriptor in descriptorSwitch with id: " + getDefaultDescriptor().getId());
    }


    /**
     * Função de criação do elemento filho <i>bindRule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>bindRule</i>.
     */
    protected B createBindRule() {
        return (B) new NCLBindRule(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>descriptor</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>descriptor</i>.
     */
    protected D createDescriptor() {
        return (D) new NCLDescriptor(getReader(), this);
    }
}
