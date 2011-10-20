/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.midiacom.ana.datatype.enums;


/**
 * Nomes dos atributos dos elementos NCL
 */
public enum NCLElementAttributes {

    ACTION("action"),
    ACTIONTYPE("actionType"),
    ALIAS("alias"),
    ATTRIBUTETYPE("attributeType"),
    AREA("area"),
    BEGIN("begin"),
    BIND("bind"),
    BINDRULE("bindRule"),
    BINDPARAM("bindParam"),
    BODY("body"),
    BORDERCOLOR("borderColor"),
    BORDERWIDTH("borderWidth"),
    BOTTOM("bottom"),
    BY("by"),
    COMPARATOR("comparator"),
    COMPONENT("component"),
    COMPOSITERULE("compositeRule"),
    CONDITION("condition"),
    CONNECTORBASE("connectorBase"),
    CONSTITUENT("constituent"),
    CONTENT("content"),
    CONTEXT("context"),
    COORDS("coords"),
    DEFAULTCOMPONENT("defaultComponent"),
    DELAY("delay"),
    DESCRIPTOR("descriptor"),
    DESCRIPTORBASE("descriptorBase"),
    DEVICE("device"),
    DIRECTION("direction"),
    DOCUMENTURI("documentURI"),
    DUR("dur"),
    DURATION("duration"),
    END("end"),
    ENDPROGRESS("endProgress"),
    EVENTTYPE("eventType"),
    EXPLICITDUR("explicitDur"),
    FADECOLOR("fadeColor"),
    FIRST("first"),
    FOCUSBORDERCOLOR("focusBorderColor"),
    FOCUSBORDERTRANSPARENCY("focusBorderTransparency"),
    FOCUSBORDERWIDTH("focusBorderWidth"),
    FOCUSINDEX("focusIndex"),
    FOCUSSELSRC("focusSelSrc"),
    FOCUSSRC("focusSrc"),
    FREEZE("freeze"),
    HEAD("head"),
    HEIGHT("height"),
    HORREPEAT("horRepeat"),
    ID("id"),
    IMPORTBASE("importBase"),
    IMPORTNCL("importNCL"),
    IMPORTEDDOCUMENTBASE("importedDocumentBase"),
    INSTANCE("instance"),
    INTERFACE("interface"),
    ISNEGATED("isNegated"),
    KEY("key"),
    LABEL("label"),
    LAST("last"),
    LEFT("left"),
    LINK("link"),
    LINKPARAM("linkParam"),
    MAX("max"),
    MAPPING("mapping"),
    MEDIA("media"),
    META("meta"),
    METADATA("metadata"),
    MIN("min"),
    MOVEDOWN("moveDown"),
    MOVELEFT("moveLeft"),
    MOVERIGHT("moveRight"),
    MOVEUP("moveUp"),
    NAME("name"),
    OFFSET("offset"),
    OPERATOR("operator"),
    PARENT("parent"),
    PLAYER("player"),
    POSITION("position"),
    PORT("port"),
    PROPERTY("property"),
    QUALIFIER("qualifier"),
    RDFTREE("rdfTree"),
    REFER("refer"),
    REGION("region"),
    REGIONBASE("regionBase"),
    RELATIVEBOTTOM("relativeBottom"),
    RELATIVEHEIGHT("relativeHeight"),
    RELATIVELEFT("relativeLeft"),
    RELATIVERIGHT("relativeRight"),
    RELATIVETOP("relativeTop"),
    RELATIVEWIDTH("relativeWidth"),
    REPEAT("repeat"),
    REPEATDELAY("repeatDelay"),
    RIGHT("right"),
    ROLE("role"),
    RULE("rule"),
    RULEBASE("ruleBase"),
    SRC("src"),
    STARTPROGRESS("startProgress"),
    SUBTYPE("subtype"),
    SWITCH("switch"),
    SWITCHPORT("switchPort"),
    TEXT("text"),
    TITLE("title"),
    TOP("top"),
    TRANSIN("transIn"),
    TRANSITION("transition"),
    TRANSITIONBASE("transitionBase"),
    TRANSOUT("transOut"),
    TYPE("type"),
    VALUE("value"),
    VALUEASSESSMENT("valueAssessment"),
    VAR("var"),
    VERTREPEAT("vertRepeat"),
    WIDTH("width"),
    XCONNECTOR("xconnector"),
    XMLNS("xmlns"),
    ZINDEX("zIndex");

    private String name;
    private NCLElementAttributes(String name) { this.name = name;}
    @Override
    public String toString() { return name;}
}
