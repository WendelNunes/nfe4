//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2020.10.09 às 09:55:15 AM BRT 
//


package br.com.inloc.nfe4.eventogenerico;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Tpais.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="Tpais">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;whiteSpace value="preserve"/>
 *     &lt;enumeration value="132"/>
 *     &lt;enumeration value="175"/>
 *     &lt;enumeration value="230"/>
 *     &lt;enumeration value="310"/>
 *     &lt;enumeration value="370"/>
 *     &lt;enumeration value="400"/>
 *     &lt;enumeration value="418"/>
 *     &lt;enumeration value="434"/>
 *     &lt;enumeration value="477"/>
 *     &lt;enumeration value="531"/>
 *     &lt;enumeration value="590"/>
 *     &lt;enumeration value="639"/>
 *     &lt;enumeration value="647"/>
 *     &lt;enumeration value="655"/>
 *     &lt;enumeration value="698"/>
 *     &lt;enumeration value="728"/>
 *     &lt;enumeration value="736"/>
 *     &lt;enumeration value="779"/>
 *     &lt;enumeration value="809"/>
 *     &lt;enumeration value="817"/>
 *     &lt;enumeration value="833"/>
 *     &lt;enumeration value="850"/>
 *     &lt;enumeration value="876"/>
 *     &lt;enumeration value="884"/>
 *     &lt;enumeration value="906"/>
 *     &lt;enumeration value="930"/>
 *     &lt;enumeration value="973"/>
 *     &lt;enumeration value="981"/>
 *     &lt;enumeration value="0132"/>
 *     &lt;enumeration value="0175"/>
 *     &lt;enumeration value="0230"/>
 *     &lt;enumeration value="0310"/>
 *     &lt;enumeration value="0370"/>
 *     &lt;enumeration value="0400"/>
 *     &lt;enumeration value="0418"/>
 *     &lt;enumeration value="0434"/>
 *     &lt;enumeration value="0477"/>
 *     &lt;enumeration value="0531"/>
 *     &lt;enumeration value="0590"/>
 *     &lt;enumeration value="0639"/>
 *     &lt;enumeration value="0647"/>
 *     &lt;enumeration value="0655"/>
 *     &lt;enumeration value="0698"/>
 *     &lt;enumeration value="0728"/>
 *     &lt;enumeration value="0736"/>
 *     &lt;enumeration value="0779"/>
 *     &lt;enumeration value="0809"/>
 *     &lt;enumeration value="0817"/>
 *     &lt;enumeration value="0833"/>
 *     &lt;enumeration value="0850"/>
 *     &lt;enumeration value="0876"/>
 *     &lt;enumeration value="0884"/>
 *     &lt;enumeration value="0906"/>
 *     &lt;enumeration value="0930"/>
 *     &lt;enumeration value="0973"/>
 *     &lt;enumeration value="0981"/>
 *     &lt;enumeration value="1015"/>
 *     &lt;enumeration value="1058"/>
 *     &lt;enumeration value="1082"/>
 *     &lt;enumeration value="1112"/>
 *     &lt;enumeration value="1155"/>
 *     &lt;enumeration value="1198"/>
 *     &lt;enumeration value="1279"/>
 *     &lt;enumeration value="1376"/>
 *     &lt;enumeration value="1414"/>
 *     &lt;enumeration value="1457"/>
 *     &lt;enumeration value="1490"/>
 *     &lt;enumeration value="1504"/>
 *     &lt;enumeration value="1508"/>
 *     &lt;enumeration value="1511"/>
 *     &lt;enumeration value="1538"/>
 *     &lt;enumeration value="1546"/>
 *     &lt;enumeration value="1589"/>
 *     &lt;enumeration value="1600"/>
 *     &lt;enumeration value="1619"/>
 *     &lt;enumeration value="1635"/>
 *     &lt;enumeration value="1651"/>
 *     &lt;enumeration value="1694"/>
 *     &lt;enumeration value="1732"/>
 *     &lt;enumeration value="1775"/>
 *     &lt;enumeration value="1830"/>
 *     &lt;enumeration value="1872"/>
 *     &lt;enumeration value="1902"/>
 *     &lt;enumeration value="1937"/>
 *     &lt;enumeration value="1953"/>
 *     &lt;enumeration value="1961"/>
 *     &lt;enumeration value="1988"/>
 *     &lt;enumeration value="1996"/>
 *     &lt;enumeration value="2291"/>
 *     &lt;enumeration value="2321"/>
 *     &lt;enumeration value="2356"/>
 *     &lt;enumeration value="2399"/>
 *     &lt;enumeration value="2402"/>
 *     &lt;enumeration value="2437"/>
 *     &lt;enumeration value="2445"/>
 *     &lt;enumeration value="2453"/>
 *     &lt;enumeration value="2461"/>
 *     &lt;enumeration value="2470"/>
 *     &lt;enumeration value="2496"/>
 *     &lt;enumeration value="2518"/>
 *     &lt;enumeration value="2534"/>
 *     &lt;enumeration value="2550"/>
 *     &lt;enumeration value="2593"/>
 *     &lt;enumeration value="2674"/>
 *     &lt;enumeration value="2712"/>
 *     &lt;enumeration value="2755"/>
 *     &lt;enumeration value="2810"/>
 *     &lt;enumeration value="2852"/>
 *     &lt;enumeration value="2895"/>
 *     &lt;enumeration value="2917"/>
 *     &lt;enumeration value="2933"/>
 *     &lt;enumeration value="2976"/>
 *     &lt;enumeration value="3018"/>
 *     &lt;enumeration value="3050"/>
 *     &lt;enumeration value="3093"/>
 *     &lt;enumeration value="3131"/>
 *     &lt;enumeration value="3174"/>
 *     &lt;enumeration value="3255"/>
 *     &lt;enumeration value="3298"/>
 *     &lt;enumeration value="3310"/>
 *     &lt;enumeration value="3344"/>
 *     &lt;enumeration value="3379"/>
 *     &lt;enumeration value="3417"/>
 *     &lt;enumeration value="3450"/>
 *     &lt;enumeration value="3514"/>
 *     &lt;enumeration value="3557"/>
 *     &lt;enumeration value="3573"/>
 *     &lt;enumeration value="3595"/>
 *     &lt;enumeration value="3611"/>
 *     &lt;enumeration value="3654"/>
 *     &lt;enumeration value="3697"/>
 *     &lt;enumeration value="3727"/>
 *     &lt;enumeration value="3751"/>
 *     &lt;enumeration value="3794"/>
 *     &lt;enumeration value="3832"/>
 *     &lt;enumeration value="3867"/>
 *     &lt;enumeration value="3913"/>
 *     &lt;enumeration value="3964"/>
 *     &lt;enumeration value="3999"/>
 *     &lt;enumeration value="4030"/>
 *     &lt;enumeration value="4111"/>
 *     &lt;enumeration value="4200"/>
 *     &lt;enumeration value="4235"/>
 *     &lt;enumeration value="4260"/>
 *     &lt;enumeration value="4278"/>
 *     &lt;enumeration value="4316"/>
 *     &lt;enumeration value="4340"/>
 *     &lt;enumeration value="4383"/>
 *     &lt;enumeration value="4405"/>
 *     &lt;enumeration value="4421"/>
 *     &lt;enumeration value="4456"/>
 *     &lt;enumeration value="4472"/>
 *     &lt;enumeration value="4499"/>
 *     &lt;enumeration value="4502"/>
 *     &lt;enumeration value="4525"/>
 *     &lt;enumeration value="4553"/>
 *     &lt;enumeration value="4588"/>
 *     &lt;enumeration value="4618"/>
 *     &lt;enumeration value="4642"/>
 *     &lt;enumeration value="4677"/>
 *     &lt;enumeration value="4723"/>
 *     &lt;enumeration value="4740"/>
 *     &lt;enumeration value="4766"/>
 *     &lt;enumeration value="4774"/>
 *     &lt;enumeration value="4855"/>
 *     &lt;enumeration value="4880"/>
 *     &lt;enumeration value="4885"/>
 *     &lt;enumeration value="4901"/>
 *     &lt;enumeration value="4936"/>
 *     &lt;enumeration value="4944"/>
 *     &lt;enumeration value="4952"/>
 *     &lt;enumeration value="4979"/>
 *     &lt;enumeration value="4985"/>
 *     &lt;enumeration value="4995"/>
 *     &lt;enumeration value="5010"/>
 *     &lt;enumeration value="5053"/>
 *     &lt;enumeration value="5070"/>
 *     &lt;enumeration value="5088"/>
 *     &lt;enumeration value="5118"/>
 *     &lt;enumeration value="5177"/>
 *     &lt;enumeration value="5215"/>
 *     &lt;enumeration value="5258"/>
 *     &lt;enumeration value="5282"/>
 *     &lt;enumeration value="5312"/>
 *     &lt;enumeration value="5355"/>
 *     &lt;enumeration value="5380"/>
 *     &lt;enumeration value="5428"/>
 *     &lt;enumeration value="5452"/>
 *     &lt;enumeration value="5487"/>
 *     &lt;enumeration value="5517"/>
 *     &lt;enumeration value="5568"/>
 *     &lt;enumeration value="5665"/>
 *     &lt;enumeration value="5738"/>
 *     &lt;enumeration value="5754"/>
 *     &lt;enumeration value="5762"/>
 *     &lt;enumeration value="5800"/>
 *     &lt;enumeration value="5860"/>
 *     &lt;enumeration value="5894"/>
 *     &lt;enumeration value="5932"/>
 *     &lt;enumeration value="5991"/>
 *     &lt;enumeration value="6033"/>
 *     &lt;enumeration value="6076"/>
 *     &lt;enumeration value="6114"/>
 *     &lt;enumeration value="6238"/>
 *     &lt;enumeration value="6254"/>
 *     &lt;enumeration value="6289"/>
 *     &lt;enumeration value="6408"/>
 *     &lt;enumeration value="6475"/>
 *     &lt;enumeration value="6602"/>
 *     &lt;enumeration value="6653"/>
 *     &lt;enumeration value="6700"/>
 *     &lt;enumeration value="6750"/>
 *     &lt;enumeration value="6769"/>
 *     &lt;enumeration value="6777"/>
 *     &lt;enumeration value="6781"/>
 *     &lt;enumeration value="6858"/>
 *     &lt;enumeration value="6874"/>
 *     &lt;enumeration value="6904"/>
 *     &lt;enumeration value="6912"/>
 *     &lt;enumeration value="6955"/>
 *     &lt;enumeration value="6971"/>
 *     &lt;enumeration value="7005"/>
 *     &lt;enumeration value="7056"/>
 *     &lt;enumeration value="7102"/>
 *     &lt;enumeration value="7153"/>
 *     &lt;enumeration value="7200"/>
 *     &lt;enumeration value="7285"/>
 *     &lt;enumeration value="7315"/>
 *     &lt;enumeration value="7358"/>
 *     &lt;enumeration value="7370"/>
 *     &lt;enumeration value="7412"/>
 *     &lt;enumeration value="7447"/>
 *     &lt;enumeration value="7480"/>
 *     &lt;enumeration value="7501"/>
 *     &lt;enumeration value="7544"/>
 *     &lt;enumeration value="7560"/>
 *     &lt;enumeration value="7595"/>
 *     &lt;enumeration value="7641"/>
 *     &lt;enumeration value="7676"/>
 *     &lt;enumeration value="7706"/>
 *     &lt;enumeration value="7722"/>
 *     &lt;enumeration value="7765"/>
 *     &lt;enumeration value="7803"/>
 *     &lt;enumeration value="7820"/>
 *     &lt;enumeration value="7838"/>
 *     &lt;enumeration value="7889"/>
 *     &lt;enumeration value="7919"/>
 *     &lt;enumeration value="7951"/>
 *     &lt;enumeration value="8001"/>
 *     &lt;enumeration value="8052"/>
 *     &lt;enumeration value="8109"/>
 *     &lt;enumeration value="8150"/>
 *     &lt;enumeration value="8206"/>
 *     &lt;enumeration value="8230"/>
 *     &lt;enumeration value="8249"/>
 *     &lt;enumeration value="8273"/>
 *     &lt;enumeration value="8281"/>
 *     &lt;enumeration value="8311"/>
 *     &lt;enumeration value="8338"/>
 *     &lt;enumeration value="8451"/>
 *     &lt;enumeration value="8478"/>
 *     &lt;enumeration value="8486"/>
 *     &lt;enumeration value="8508"/>
 *     &lt;enumeration value="8583"/>
 *     &lt;enumeration value="8630"/>
 *     &lt;enumeration value="8664"/>
 *     &lt;enumeration value="8702"/>
 *     &lt;enumeration value="8737"/>
 *     &lt;enumeration value="8885"/>
 *     &lt;enumeration value="8907"/>
 *     &lt;enumeration value="8958"/>
 *     &lt;enumeration value="9903"/>
 *     &lt;enumeration value="9946"/>
 *     &lt;enumeration value="9950"/>
 *     &lt;enumeration value="9970"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Tpais", namespace = "http://www.portalfiscal.inf.br/nfe")
@XmlEnum
public enum Tpais {

    @XmlEnumValue("132")
    VALUE_1("132"),
    @XmlEnumValue("175")
    VALUE_2("175"),
    @XmlEnumValue("230")
    VALUE_3("230"),
    @XmlEnumValue("310")
    VALUE_4("310"),
    @XmlEnumValue("370")
    VALUE_5("370"),
    @XmlEnumValue("400")
    VALUE_6("400"),
    @XmlEnumValue("418")
    VALUE_7("418"),
    @XmlEnumValue("434")
    VALUE_8("434"),
    @XmlEnumValue("477")
    VALUE_9("477"),
    @XmlEnumValue("531")
    VALUE_10("531"),
    @XmlEnumValue("590")
    VALUE_11("590"),
    @XmlEnumValue("639")
    VALUE_12("639"),
    @XmlEnumValue("647")
    VALUE_13("647"),
    @XmlEnumValue("655")
    VALUE_14("655"),
    @XmlEnumValue("698")
    VALUE_15("698"),
    @XmlEnumValue("728")
    VALUE_16("728"),
    @XmlEnumValue("736")
    VALUE_17("736"),
    @XmlEnumValue("779")
    VALUE_18("779"),
    @XmlEnumValue("809")
    VALUE_19("809"),
    @XmlEnumValue("817")
    VALUE_20("817"),
    @XmlEnumValue("833")
    VALUE_21("833"),
    @XmlEnumValue("850")
    VALUE_22("850"),
    @XmlEnumValue("876")
    VALUE_23("876"),
    @XmlEnumValue("884")
    VALUE_24("884"),
    @XmlEnumValue("906")
    VALUE_25("906"),
    @XmlEnumValue("930")
    VALUE_26("930"),
    @XmlEnumValue("973")
    VALUE_27("973"),
    @XmlEnumValue("981")
    VALUE_28("981"),
    @XmlEnumValue("0132")
    VALUE_29("0132"),
    @XmlEnumValue("0175")
    VALUE_30("0175"),
    @XmlEnumValue("0230")
    VALUE_31("0230"),
    @XmlEnumValue("0310")
    VALUE_32("0310"),
    @XmlEnumValue("0370")
    VALUE_33("0370"),
    @XmlEnumValue("0400")
    VALUE_34("0400"),
    @XmlEnumValue("0418")
    VALUE_35("0418"),
    @XmlEnumValue("0434")
    VALUE_36("0434"),
    @XmlEnumValue("0477")
    VALUE_37("0477"),
    @XmlEnumValue("0531")
    VALUE_38("0531"),
    @XmlEnumValue("0590")
    VALUE_39("0590"),
    @XmlEnumValue("0639")
    VALUE_40("0639"),
    @XmlEnumValue("0647")
    VALUE_41("0647"),
    @XmlEnumValue("0655")
    VALUE_42("0655"),
    @XmlEnumValue("0698")
    VALUE_43("0698"),
    @XmlEnumValue("0728")
    VALUE_44("0728"),
    @XmlEnumValue("0736")
    VALUE_45("0736"),
    @XmlEnumValue("0779")
    VALUE_46("0779"),
    @XmlEnumValue("0809")
    VALUE_47("0809"),
    @XmlEnumValue("0817")
    VALUE_48("0817"),
    @XmlEnumValue("0833")
    VALUE_49("0833"),
    @XmlEnumValue("0850")
    VALUE_50("0850"),
    @XmlEnumValue("0876")
    VALUE_51("0876"),
    @XmlEnumValue("0884")
    VALUE_52("0884"),
    @XmlEnumValue("0906")
    VALUE_53("0906"),
    @XmlEnumValue("0930")
    VALUE_54("0930"),
    @XmlEnumValue("0973")
    VALUE_55("0973"),
    @XmlEnumValue("0981")
    VALUE_56("0981"),
    @XmlEnumValue("1015")
    VALUE_57("1015"),
    @XmlEnumValue("1058")
    VALUE_58("1058"),
    @XmlEnumValue("1082")
    VALUE_59("1082"),
    @XmlEnumValue("1112")
    VALUE_60("1112"),
    @XmlEnumValue("1155")
    VALUE_61("1155"),
    @XmlEnumValue("1198")
    VALUE_62("1198"),
    @XmlEnumValue("1279")
    VALUE_63("1279"),
    @XmlEnumValue("1376")
    VALUE_64("1376"),
    @XmlEnumValue("1414")
    VALUE_65("1414"),
    @XmlEnumValue("1457")
    VALUE_66("1457"),
    @XmlEnumValue("1490")
    VALUE_67("1490"),
    @XmlEnumValue("1504")
    VALUE_68("1504"),
    @XmlEnumValue("1508")
    VALUE_69("1508"),
    @XmlEnumValue("1511")
    VALUE_70("1511"),
    @XmlEnumValue("1538")
    VALUE_71("1538"),
    @XmlEnumValue("1546")
    VALUE_72("1546"),
    @XmlEnumValue("1589")
    VALUE_73("1589"),
    @XmlEnumValue("1600")
    VALUE_74("1600"),
    @XmlEnumValue("1619")
    VALUE_75("1619"),
    @XmlEnumValue("1635")
    VALUE_76("1635"),
    @XmlEnumValue("1651")
    VALUE_77("1651"),
    @XmlEnumValue("1694")
    VALUE_78("1694"),
    @XmlEnumValue("1732")
    VALUE_79("1732"),
    @XmlEnumValue("1775")
    VALUE_80("1775"),
    @XmlEnumValue("1830")
    VALUE_81("1830"),
    @XmlEnumValue("1872")
    VALUE_82("1872"),
    @XmlEnumValue("1902")
    VALUE_83("1902"),
    @XmlEnumValue("1937")
    VALUE_84("1937"),
    @XmlEnumValue("1953")
    VALUE_85("1953"),
    @XmlEnumValue("1961")
    VALUE_86("1961"),
    @XmlEnumValue("1988")
    VALUE_87("1988"),
    @XmlEnumValue("1996")
    VALUE_88("1996"),
    @XmlEnumValue("2291")
    VALUE_89("2291"),
    @XmlEnumValue("2321")
    VALUE_90("2321"),
    @XmlEnumValue("2356")
    VALUE_91("2356"),
    @XmlEnumValue("2399")
    VALUE_92("2399"),
    @XmlEnumValue("2402")
    VALUE_93("2402"),
    @XmlEnumValue("2437")
    VALUE_94("2437"),
    @XmlEnumValue("2445")
    VALUE_95("2445"),
    @XmlEnumValue("2453")
    VALUE_96("2453"),
    @XmlEnumValue("2461")
    VALUE_97("2461"),
    @XmlEnumValue("2470")
    VALUE_98("2470"),
    @XmlEnumValue("2496")
    VALUE_99("2496"),
    @XmlEnumValue("2518")
    VALUE_100("2518"),
    @XmlEnumValue("2534")
    VALUE_101("2534"),
    @XmlEnumValue("2550")
    VALUE_102("2550"),
    @XmlEnumValue("2593")
    VALUE_103("2593"),
    @XmlEnumValue("2674")
    VALUE_104("2674"),
    @XmlEnumValue("2712")
    VALUE_105("2712"),
    @XmlEnumValue("2755")
    VALUE_106("2755"),
    @XmlEnumValue("2810")
    VALUE_107("2810"),
    @XmlEnumValue("2852")
    VALUE_108("2852"),
    @XmlEnumValue("2895")
    VALUE_109("2895"),
    @XmlEnumValue("2917")
    VALUE_110("2917"),
    @XmlEnumValue("2933")
    VALUE_111("2933"),
    @XmlEnumValue("2976")
    VALUE_112("2976"),
    @XmlEnumValue("3018")
    VALUE_113("3018"),
    @XmlEnumValue("3050")
    VALUE_114("3050"),
    @XmlEnumValue("3093")
    VALUE_115("3093"),
    @XmlEnumValue("3131")
    VALUE_116("3131"),
    @XmlEnumValue("3174")
    VALUE_117("3174"),
    @XmlEnumValue("3255")
    VALUE_118("3255"),
    @XmlEnumValue("3298")
    VALUE_119("3298"),
    @XmlEnumValue("3310")
    VALUE_120("3310"),
    @XmlEnumValue("3344")
    VALUE_121("3344"),
    @XmlEnumValue("3379")
    VALUE_122("3379"),
    @XmlEnumValue("3417")
    VALUE_123("3417"),
    @XmlEnumValue("3450")
    VALUE_124("3450"),
    @XmlEnumValue("3514")
    VALUE_125("3514"),
    @XmlEnumValue("3557")
    VALUE_126("3557"),
    @XmlEnumValue("3573")
    VALUE_127("3573"),
    @XmlEnumValue("3595")
    VALUE_128("3595"),
    @XmlEnumValue("3611")
    VALUE_129("3611"),
    @XmlEnumValue("3654")
    VALUE_130("3654"),
    @XmlEnumValue("3697")
    VALUE_131("3697"),
    @XmlEnumValue("3727")
    VALUE_132("3727"),
    @XmlEnumValue("3751")
    VALUE_133("3751"),
    @XmlEnumValue("3794")
    VALUE_134("3794"),
    @XmlEnumValue("3832")
    VALUE_135("3832"),
    @XmlEnumValue("3867")
    VALUE_136("3867"),
    @XmlEnumValue("3913")
    VALUE_137("3913"),
    @XmlEnumValue("3964")
    VALUE_138("3964"),
    @XmlEnumValue("3999")
    VALUE_139("3999"),
    @XmlEnumValue("4030")
    VALUE_140("4030"),
    @XmlEnumValue("4111")
    VALUE_141("4111"),
    @XmlEnumValue("4200")
    VALUE_142("4200"),
    @XmlEnumValue("4235")
    VALUE_143("4235"),
    @XmlEnumValue("4260")
    VALUE_144("4260"),
    @XmlEnumValue("4278")
    VALUE_145("4278"),
    @XmlEnumValue("4316")
    VALUE_146("4316"),
    @XmlEnumValue("4340")
    VALUE_147("4340"),
    @XmlEnumValue("4383")
    VALUE_148("4383"),
    @XmlEnumValue("4405")
    VALUE_149("4405"),
    @XmlEnumValue("4421")
    VALUE_150("4421"),
    @XmlEnumValue("4456")
    VALUE_151("4456"),
    @XmlEnumValue("4472")
    VALUE_152("4472"),
    @XmlEnumValue("4499")
    VALUE_153("4499"),
    @XmlEnumValue("4502")
    VALUE_154("4502"),
    @XmlEnumValue("4525")
    VALUE_155("4525"),
    @XmlEnumValue("4553")
    VALUE_156("4553"),
    @XmlEnumValue("4588")
    VALUE_157("4588"),
    @XmlEnumValue("4618")
    VALUE_158("4618"),
    @XmlEnumValue("4642")
    VALUE_159("4642"),
    @XmlEnumValue("4677")
    VALUE_160("4677"),
    @XmlEnumValue("4723")
    VALUE_161("4723"),
    @XmlEnumValue("4740")
    VALUE_162("4740"),
    @XmlEnumValue("4766")
    VALUE_163("4766"),
    @XmlEnumValue("4774")
    VALUE_164("4774"),
    @XmlEnumValue("4855")
    VALUE_165("4855"),
    @XmlEnumValue("4880")
    VALUE_166("4880"),
    @XmlEnumValue("4885")
    VALUE_167("4885"),
    @XmlEnumValue("4901")
    VALUE_168("4901"),
    @XmlEnumValue("4936")
    VALUE_169("4936"),
    @XmlEnumValue("4944")
    VALUE_170("4944"),
    @XmlEnumValue("4952")
    VALUE_171("4952"),
    @XmlEnumValue("4979")
    VALUE_172("4979"),
    @XmlEnumValue("4985")
    VALUE_173("4985"),
    @XmlEnumValue("4995")
    VALUE_174("4995"),
    @XmlEnumValue("5010")
    VALUE_175("5010"),
    @XmlEnumValue("5053")
    VALUE_176("5053"),
    @XmlEnumValue("5070")
    VALUE_177("5070"),
    @XmlEnumValue("5088")
    VALUE_178("5088"),
    @XmlEnumValue("5118")
    VALUE_179("5118"),
    @XmlEnumValue("5177")
    VALUE_180("5177"),
    @XmlEnumValue("5215")
    VALUE_181("5215"),
    @XmlEnumValue("5258")
    VALUE_182("5258"),
    @XmlEnumValue("5282")
    VALUE_183("5282"),
    @XmlEnumValue("5312")
    VALUE_184("5312"),
    @XmlEnumValue("5355")
    VALUE_185("5355"),
    @XmlEnumValue("5380")
    VALUE_186("5380"),
    @XmlEnumValue("5428")
    VALUE_187("5428"),
    @XmlEnumValue("5452")
    VALUE_188("5452"),
    @XmlEnumValue("5487")
    VALUE_189("5487"),
    @XmlEnumValue("5517")
    VALUE_190("5517"),
    @XmlEnumValue("5568")
    VALUE_191("5568"),
    @XmlEnumValue("5665")
    VALUE_192("5665"),
    @XmlEnumValue("5738")
    VALUE_193("5738"),
    @XmlEnumValue("5754")
    VALUE_194("5754"),
    @XmlEnumValue("5762")
    VALUE_195("5762"),
    @XmlEnumValue("5800")
    VALUE_196("5800"),
    @XmlEnumValue("5860")
    VALUE_197("5860"),
    @XmlEnumValue("5894")
    VALUE_198("5894"),
    @XmlEnumValue("5932")
    VALUE_199("5932"),
    @XmlEnumValue("5991")
    VALUE_200("5991"),
    @XmlEnumValue("6033")
    VALUE_201("6033"),
    @XmlEnumValue("6076")
    VALUE_202("6076"),
    @XmlEnumValue("6114")
    VALUE_203("6114"),
    @XmlEnumValue("6238")
    VALUE_204("6238"),
    @XmlEnumValue("6254")
    VALUE_205("6254"),
    @XmlEnumValue("6289")
    VALUE_206("6289"),
    @XmlEnumValue("6408")
    VALUE_207("6408"),
    @XmlEnumValue("6475")
    VALUE_208("6475"),
    @XmlEnumValue("6602")
    VALUE_209("6602"),
    @XmlEnumValue("6653")
    VALUE_210("6653"),
    @XmlEnumValue("6700")
    VALUE_211("6700"),
    @XmlEnumValue("6750")
    VALUE_212("6750"),
    @XmlEnumValue("6769")
    VALUE_213("6769"),
    @XmlEnumValue("6777")
    VALUE_214("6777"),
    @XmlEnumValue("6781")
    VALUE_215("6781"),
    @XmlEnumValue("6858")
    VALUE_216("6858"),
    @XmlEnumValue("6874")
    VALUE_217("6874"),
    @XmlEnumValue("6904")
    VALUE_218("6904"),
    @XmlEnumValue("6912")
    VALUE_219("6912"),
    @XmlEnumValue("6955")
    VALUE_220("6955"),
    @XmlEnumValue("6971")
    VALUE_221("6971"),
    @XmlEnumValue("7005")
    VALUE_222("7005"),
    @XmlEnumValue("7056")
    VALUE_223("7056"),
    @XmlEnumValue("7102")
    VALUE_224("7102"),
    @XmlEnumValue("7153")
    VALUE_225("7153"),
    @XmlEnumValue("7200")
    VALUE_226("7200"),
    @XmlEnumValue("7285")
    VALUE_227("7285"),
    @XmlEnumValue("7315")
    VALUE_228("7315"),
    @XmlEnumValue("7358")
    VALUE_229("7358"),
    @XmlEnumValue("7370")
    VALUE_230("7370"),
    @XmlEnumValue("7412")
    VALUE_231("7412"),
    @XmlEnumValue("7447")
    VALUE_232("7447"),
    @XmlEnumValue("7480")
    VALUE_233("7480"),
    @XmlEnumValue("7501")
    VALUE_234("7501"),
    @XmlEnumValue("7544")
    VALUE_235("7544"),
    @XmlEnumValue("7560")
    VALUE_236("7560"),
    @XmlEnumValue("7595")
    VALUE_237("7595"),
    @XmlEnumValue("7641")
    VALUE_238("7641"),
    @XmlEnumValue("7676")
    VALUE_239("7676"),
    @XmlEnumValue("7706")
    VALUE_240("7706"),
    @XmlEnumValue("7722")
    VALUE_241("7722"),
    @XmlEnumValue("7765")
    VALUE_242("7765"),
    @XmlEnumValue("7803")
    VALUE_243("7803"),
    @XmlEnumValue("7820")
    VALUE_244("7820"),
    @XmlEnumValue("7838")
    VALUE_245("7838"),
    @XmlEnumValue("7889")
    VALUE_246("7889"),
    @XmlEnumValue("7919")
    VALUE_247("7919"),
    @XmlEnumValue("7951")
    VALUE_248("7951"),
    @XmlEnumValue("8001")
    VALUE_249("8001"),
    @XmlEnumValue("8052")
    VALUE_250("8052"),
    @XmlEnumValue("8109")
    VALUE_251("8109"),
    @XmlEnumValue("8150")
    VALUE_252("8150"),
    @XmlEnumValue("8206")
    VALUE_253("8206"),
    @XmlEnumValue("8230")
    VALUE_254("8230"),
    @XmlEnumValue("8249")
    VALUE_255("8249"),
    @XmlEnumValue("8273")
    VALUE_256("8273"),
    @XmlEnumValue("8281")
    VALUE_257("8281"),
    @XmlEnumValue("8311")
    VALUE_258("8311"),
    @XmlEnumValue("8338")
    VALUE_259("8338"),
    @XmlEnumValue("8451")
    VALUE_260("8451"),
    @XmlEnumValue("8478")
    VALUE_261("8478"),
    @XmlEnumValue("8486")
    VALUE_262("8486"),
    @XmlEnumValue("8508")
    VALUE_263("8508"),
    @XmlEnumValue("8583")
    VALUE_264("8583"),
    @XmlEnumValue("8630")
    VALUE_265("8630"),
    @XmlEnumValue("8664")
    VALUE_266("8664"),
    @XmlEnumValue("8702")
    VALUE_267("8702"),
    @XmlEnumValue("8737")
    VALUE_268("8737"),
    @XmlEnumValue("8885")
    VALUE_269("8885"),
    @XmlEnumValue("8907")
    VALUE_270("8907"),
    @XmlEnumValue("8958")
    VALUE_271("8958"),
    @XmlEnumValue("9903")
    VALUE_272("9903"),
    @XmlEnumValue("9946")
    VALUE_273("9946"),
    @XmlEnumValue("9950")
    VALUE_274("9950"),
    @XmlEnumValue("9970")
    VALUE_275("9970");
    private final String value;

    Tpais(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Tpais fromValue(String v) {
        for (Tpais c: Tpais.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
